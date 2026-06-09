package sistemperpustakaan.controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenuUtamaController {

    public String[] getOverview() {
        return new String[] {
            String.valueOf(hitungData("SELECT COUNT(*) FROM buku")),
            String.valueOf(hitungData("SELECT COUNT(*) FROM anggota")),
            String.valueOf(hitungData("SELECT COUNT(*) FROM peminjaman WHERE status IN ('Dipinjam', 'Terlambat')")),
            String.valueOf(hitungData("SELECT COUNT(*) FROM peminjaman"))
        };
    }

    public String[] cariProfilPustakawan(String namaPustakawan) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT nama, nip, shift_kerja FROM shift_aktif WHERE id=1";
            try (PreparedStatement pst = conn.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new String[] {
                        rs.getString("nama"),
                        rs.getString("nip"),
                        rs.getString("shift_kerja")
                    };
                }
            }
        } catch (Exception e) {
            // Jika tabel shift_aktif belum ada, gunakan data login pustakawan.
        }

        String[][] queryList = {
            {"SELECT nip, shift_kerja FROM pustakawan WHERE nama=?", "nip", "shift_kerja"},
            {"SELECT nip, shiftKerja FROM pustakawan WHERE nama=?", "nip", "shiftKerja"},
            {"SELECT nip, shift_kerja FROM user WHERE username=?", "nip", "shift_kerja"}
        };

        for (String[] query : queryList) {
            try {
                Connection conn = Koneksi.getConnection();
                try (PreparedStatement pst = conn.prepareStatement(query[0])) {
                    pst.setString(1, namaPustakawan);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        return new String[] {
                            namaPustakawan,
                            rs.getString(query[1]),
                            rs.getString(query[2])
                        };
                    }
                }
            } catch (Exception e) {
                // Coba struktur tabel berikutnya jika kolom belum ada.
            }
        }

        return new String[] {namaPustakawan, "-", "-"};
    }

    private int hitungData(String sql) {
        try {
            Connection conn = Koneksi.getConnection();
            try (PreparedStatement pst = conn.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            // Jika tabel belum tersedia, overview tetap tampil dengan nilai 0.
        }

        return 0;
    }
}
