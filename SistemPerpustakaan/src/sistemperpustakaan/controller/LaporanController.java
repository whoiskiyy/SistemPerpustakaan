package sistemperpustakaan.controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class LaporanController {

    public int totalBuku() throws Exception {
        return hitungData("SELECT COUNT(*) FROM buku");
    }

    public int totalAnggota() throws Exception {
        return hitungData("SELECT COUNT(*) FROM anggota");
    }

    public int totalPeminjaman() throws Exception {
        return hitungData("SELECT COUNT(*) FROM peminjaman");
    }

    public int totalDipinjam() throws Exception {
        return hitungData("SELECT COUNT(*) FROM peminjaman WHERE status IN ('Dipinjam', 'Terlambat')");
    }

    public int totalDikembalikan() throws Exception {
        return hitungData("SELECT COUNT(*) FROM peminjaman WHERE status='Dikembalikan'");
    }

    public DefaultTableModel tampilLaporan() throws Exception {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Anggota");
        model.addColumn("Buku");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Status");

        Connection conn = Koneksi.getConnection();
        String sql = "SELECT * FROM peminjaman ORDER BY id_pinjam DESC";

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id_pinjam"),
                    rs.getString("nama_anggota"),
                    rs.getString("judul_buku"),
                    rs.getString("tanggal_pinjam"),
                    rs.getString("tanggal_kembali"),
                    rs.getString("status")
                });
            }
        }

        return model;
    }

    private int hitungData(String sql) throws Exception {
        Connection conn = Koneksi.getConnection();

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }
}
