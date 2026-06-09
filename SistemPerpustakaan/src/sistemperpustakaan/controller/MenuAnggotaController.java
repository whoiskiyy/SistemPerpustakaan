package sistemperpustakaan.controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenuAnggotaController {

    public String[] getOverview(String namaAnggota) {
        return new String[] {
            String.valueOf(hitungData("SELECT COUNT(*) FROM buku WHERE stok > 0")),
            String.valueOf(hitungData("SELECT COUNT(*) FROM peminjaman WHERE nama_anggota=? AND status IN ('Dipinjam', 'Terlambat')", namaAnggota)),
            String.valueOf(hitungData("SELECT COUNT(*) FROM peminjaman WHERE nama_anggota=?", namaAnggota)),
            String.valueOf(hitungData("SELECT COUNT(*) FROM ulasan WHERE nama_anggota=?", namaAnggota))
        };
    }

    private int hitungData(String sql, String... parameter) {
        try {
            Connection conn = Koneksi.getConnection();
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                for (int i = 0; i < parameter.length; i++) {
                    pst.setString(i + 1, parameter[i]);
                }

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            // Jika tabel/kolom belum tersedia, overview tetap tampil dengan nilai 0.
        }

        return 0;
    }
}
