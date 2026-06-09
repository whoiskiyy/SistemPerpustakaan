package sistemperpustakaan.controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class PeminjamanController {

    private static final int DENDA_PER_HARI = 1000;
    private static final DateTimeFormatter FORMAT_TANGGAL = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DefaultTableModel tampilData() throws Exception {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Anggota");
        model.addColumn("Judul Buku");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Status");
        model.addColumn("Denda");

        Connection conn = Koneksi.getConnection();
        String sql = "SELECT * FROM peminjaman";

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id_pinjam"),
                    rs.getString("nama_anggota"),
                    rs.getString("judul_buku"),
                    rs.getString("tanggal_pinjam"),
                    rs.getString("tanggal_kembali"),
                    rs.getString("status"),
                    hitungDenda(rs.getString("tanggal_kembali"), rs.getString("status"))
                });
            }
        }

        return model;
    }

    public DefaultComboBoxModel<String> loadAnggota() throws Exception {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Pilih Anggota");

        Connection conn = Koneksi.getConnection();
        String sql = "SELECT nama FROM anggota";

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                model.addElement(rs.getString("nama"));
            }
        }

        return model;
    }

    public DefaultComboBoxModel<String> loadBuku() throws Exception {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Pilih Buku");

        Connection conn = Koneksi.getConnection();
        String sql = "SELECT judul FROM buku";

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                model.addElement(rs.getString("judul"));
            }
        }

        return model;
    }

    public void simpan(String namaAnggota, String judulBuku, String tanggalPinjam,
            String tanggalKembali, String status) throws Exception {
        Connection conn = Koneksi.getConnection();

        try {
            conn.setAutoCommit(false);

            if (statusDipinjam(status)) {
                ubahStokBuku(conn, judulBuku, -1);
            }

            String sql = "INSERT INTO peminjaman(nama_anggota, judul_buku, tanggal_pinjam, tanggal_kembali, status) "
                    + "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, namaAnggota);
                pst.setString(2, judulBuku);
                pst.setString(3, tanggalPinjam);
                pst.setString(4, tanggalKembali);
                pst.setString(5, status);
                pst.executeUpdate();
            }

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void edit(String id, String namaAnggota, String judulBaru, String tanggalPinjam,
            String tanggalKembali, String statusBaru) throws Exception {
        Connection conn = Koneksi.getConnection();

        try {
            conn.setAutoCommit(false);

            String judulLama = "";
            String statusLama = "";
            String sqlLama = "SELECT judul_buku, status FROM peminjaman WHERE id_pinjam=?";
            try (PreparedStatement pstLama = conn.prepareStatement(sqlLama)) {
                pstLama.setString(1, id);
                ResultSet rsLama = pstLama.executeQuery();
                if (rsLama.next()) {
                    judulLama = rsLama.getString("judul_buku");
                    statusLama = rsLama.getString("status");
                }
            }

            if (statusDipinjam(statusLama)) {
                ubahStokBuku(conn, judulLama, 1);
            }

            if (statusDipinjam(statusBaru)) {
                ubahStokBuku(conn, judulBaru, -1);
            }

            String sql = "UPDATE peminjaman SET nama_anggota=?, judul_buku=?, tanggal_pinjam=?, "
                    + "tanggal_kembali=?, status=? WHERE id_pinjam=?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, namaAnggota);
                pst.setString(2, judulBaru);
                pst.setString(3, tanggalPinjam);
                pst.setString(4, tanggalKembali);
                pst.setString(5, statusBaru);
                pst.setString(6, id);
                pst.executeUpdate();
            }

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void hapus(String id) throws Exception {
        Connection conn = Koneksi.getConnection();

        try {
            conn.setAutoCommit(false);

            String judulLama = "";
            String statusLama = "";
            String sqlLama = "SELECT judul_buku, status FROM peminjaman WHERE id_pinjam=?";
            try (PreparedStatement pstLama = conn.prepareStatement(sqlLama)) {
                pstLama.setString(1, id);
                ResultSet rsLama = pstLama.executeQuery();
                if (rsLama.next()) {
                    judulLama = rsLama.getString("judul_buku");
                    statusLama = rsLama.getString("status");
                }
            }

            String sql = "DELETE FROM peminjaman WHERE id_pinjam=?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, id);
                pst.executeUpdate();
            }

            if (statusDipinjam(statusLama)) {
                ubahStokBuku(conn, judulLama, 1);
            }

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public long hitungDenda(String tanggalKembali, String status) {
        if (!"Terlambat".equalsIgnoreCase(status) && !"Dipinjam".equalsIgnoreCase(status)) {
            return 0;
        }

        try {
            LocalDate batasKembali = LocalDate.parse(tanggalKembali, FORMAT_TANGGAL);
            long hariTerlambat = ChronoUnit.DAYS.between(batasKembali, LocalDate.now());
            return Math.max(0, hariTerlambat) * DENDA_PER_HARI;
        } catch (DateTimeParseException e) {
            return 0;
        }
    }

    public void validasiTanggal(String tanggalPinjam, String tanggalKembali) {
        LocalDate pinjam = LocalDate.parse(tanggalPinjam.trim(), FORMAT_TANGGAL);
        LocalDate kembali = LocalDate.parse(tanggalKembali.trim(), FORMAT_TANGGAL);

        if (kembali.isBefore(pinjam)) {
            throw new IllegalArgumentException("Tanggal kembali tidak boleh sebelum tanggal pinjam");
        }
    }

    private boolean statusDipinjam(String status) {
        return "Dipinjam".equalsIgnoreCase(status) || "Terlambat".equalsIgnoreCase(status);
    }

    private int getStokBuku(Connection conn, String judulBuku) throws Exception {
        String sql = "SELECT stok FROM buku WHERE judul = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, judulBuku);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("stok");
            }
        }

        throw new Exception("Buku tidak ditemukan");
    }

    private void ubahStokBuku(Connection conn, String judulBuku, int perubahan) throws Exception {
        if (perubahan < 0 && getStokBuku(conn, judulBuku) <= 0) {
            throw new Exception("Stok buku habis");
        }

        String sql = "UPDATE buku SET stok = stok + ? WHERE judul = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, perubahan);
            pst.setString(2, judulBuku);
            pst.executeUpdate();
        }
    }
}
