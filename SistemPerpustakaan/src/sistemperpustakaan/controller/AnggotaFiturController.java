package sistemperpustakaan.controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class AnggotaFiturController {

    public String[] tampilProfil(String namaAnggota) throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "SELECT * FROM anggota WHERE nama=?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, namaAnggota);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nomor = punyaKolom(conn, "anggota", "nomor_anggota")
                        ? rs.getString("nomor_anggota")
                        : rs.getString("id_anggota");

                return new String[] {
                    nilai(nomor),
                    nilai(rs.getString("nama")),
                    nilai(rs.getString("alamat")),
                    nilai(rs.getString("no_hp"))
                };
            }
        }

        return new String[] {"-", "-", "-", "-"};
    }

    public DefaultComboBoxModel<String> loadBukuTersedia() throws Exception {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Pilih Buku");

        Connection conn = Koneksi.getConnection();
        String sql = "SELECT judul FROM buku WHERE stok > 0";

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                model.addElement(rs.getString("judul"));
            }
        }

        return model;
    }

    public DefaultTableModel tampilRiwayat(String namaAnggota) throws Exception {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Buku");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Status");

        Connection conn = Koneksi.getConnection();
        String sql = "SELECT * FROM peminjaman WHERE nama_anggota=? ORDER BY id_pinjam DESC";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, namaAnggota);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id_pinjam"),
                    rs.getString("judul_buku"),
                    rs.getString("tanggal_pinjam"),
                    rs.getString("tanggal_kembali"),
                    rs.getString("status")
                });
            }
        }

        return model;
    }

    public DefaultTableModel tampilUlasan(String namaAnggota) throws Exception {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tanggal");
        model.addColumn("Ulasan");

        Connection conn = Koneksi.getConnection();
        String sql = "SELECT tanggal, ulasan FROM ulasan WHERE nama_anggota=? ORDER BY id_ulasan DESC";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, namaAnggota);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("tanggal"),
                    rs.getString("ulasan")
                });
            }
        }

        return model;
    }

    public void pinjamBuku(String namaAnggota, String judulBuku, String tanggalPinjam,
            String tanggalKembali) throws Exception {
        LocalDate.parse(tanggalPinjam.trim());
        LocalDate.parse(tanggalKembali.trim());
        Connection conn = Koneksi.getConnection();

        try {
            conn.setAutoCommit(false);

            String sqlStok = "UPDATE buku SET stok = stok - 1 WHERE judul=? AND stok > 0";
            try (PreparedStatement pstStok = conn.prepareStatement(sqlStok)) {
                pstStok.setString(1, judulBuku);

                if (pstStok.executeUpdate() == 0) {
                    conn.rollback();
                    throw new Exception("Stok buku habis");
                }
            }

            String sql = "INSERT INTO peminjaman(nama_anggota, judul_buku, tanggal_pinjam, tanggal_kembali, status) "
                    + "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, namaAnggota);
                pst.setString(2, judulBuku);
                pst.setString(3, tanggalPinjam.trim());
                pst.setString(4, tanggalKembali.trim());
                pst.setString(5, "Dipinjam");
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

    public void simpanUlasan(String namaAnggota, String ulasan) throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "INSERT INTO ulasan(nama_anggota, ulasan, tanggal) VALUES (?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, namaAnggota);
            pst.setString(2, ulasan.trim());
            pst.setString(3, LocalDate.now().toString());
            pst.executeUpdate();
        }
    }

    public void pastikanTabelUlasan() throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS ulasan ("
                + "id_ulasan INT AUTO_INCREMENT PRIMARY KEY,"
                + "nama_anggota VARCHAR(100) NOT NULL,"
                + "ulasan TEXT NOT NULL,"
                + "tanggal DATE NOT NULL"
                + ")";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.executeUpdate();
        }
    }

    private boolean punyaKolom(Connection conn, String tabel, String kolom) {
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getColumns(null, null, tabel, kolom);
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    private String nilai(String teks) {
        return teks == null || teks.isEmpty() ? "-" : teks;
    }
}
