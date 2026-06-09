package sistemperpustakaan.controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class PetugasShiftController {

    public DefaultTableModel tampilData(String shift) throws Exception {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Shift Kerja");

        Connection conn = Koneksi.getConnection();
        ResultSet rs = ambilDataPetugas(conn, shift);
        while (rs.next()) {
            model.addRow(new Object[] {
                rs.getString("nip"),
                rs.getString("nama"),
                rs.getString("shift_kerja")
            });
        }

        return model;
    }

    public void jadikanAktif(String nip, String nama, String shift) throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "REPLACE INTO shift_aktif(id, nip, nama, shift_kerja) VALUES (1, ?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nip);
            pst.setString(2, nama);
            pst.setString(3, shift);
            pst.executeUpdate();
        }
    }

    public boolean ubahShift(String nip, String nama, String shiftBaru) throws Exception {
        Connection conn = Koneksi.getConnection();
        int jumlahData = updateShiftPetugas(conn, nip, nama, shiftBaru);

        if (jumlahData > 0) {
            updateShiftAktifJikaPetugasSedangBekerja(conn, nip, nama, shiftBaru);
            return true;
        }

        return false;
    }

    public void pastikanTabelShiftAktif() throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS shift_aktif ("
                + "id INT PRIMARY KEY,"
                + "nip VARCHAR(50),"
                + "nama VARCHAR(100),"
                + "shift_kerja VARCHAR(50)"
                + ")";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.executeUpdate();
        }
    }

    private ResultSet ambilDataPetugas(Connection conn, String shift) throws Exception {
        String[][] sqlList = {
            {"SELECT nip, nama, shift_kerja FROM pustakawan", "shift_kerja"},
            {"SELECT nip, nama, shiftKerja AS shift_kerja FROM pustakawan", "shiftKerja"},
            {"SELECT nip, username AS nama, shift_kerja FROM user", "shift_kerja"}
        };

        for (String[] query : sqlList) {
            try {
                String sql = query[0];
                if (!"Semua".equals(shift)) {
                    sql += " WHERE " + query[1] + "=?";
                }

                PreparedStatement pst = conn.prepareStatement(sql);
                if (!"Semua".equals(shift)) {
                    pst.setString(1, shift);
                }

                return pst.executeQuery();
            } catch (Exception e) {
                // Coba nama tabel/kolom berikutnya.
            }
        }

        throw new Exception("Data petugas shift belum tersedia");
    }

    private int updateShiftPetugas(Connection conn, String nip, String nama, String shiftBaru) {
        String[] sqlList = {
            "UPDATE pustakawan SET shift_kerja=? WHERE nip=? OR nama=?",
            "UPDATE pustakawan SET shiftKerja=? WHERE nip=? OR nama=?",
            "UPDATE user SET shift_kerja=? WHERE nip=? OR username=?"
        };

        for (String sql : sqlList) {
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, shiftBaru);
                pst.setString(2, nip);
                pst.setString(3, nama);

                int jumlahData = pst.executeUpdate();
                if (jumlahData > 0) {
                    return jumlahData;
                }
            } catch (Exception e) {
                // Coba struktur tabel/kolom berikutnya.
            }
        }

        return 0;
    }

    private void updateShiftAktifJikaPetugasSedangBekerja(Connection conn, String nip, String nama, String shiftBaru) {
        try {
            String sql = "UPDATE shift_aktif SET shift_kerja=? WHERE nip=? OR nama=?";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, shiftBaru);
                pst.setString(2, nip);
                pst.setString(3, nama);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            // Abaikan jika belum ada petugas aktif.
        }
    }
}
