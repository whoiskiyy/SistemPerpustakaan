package sistemperpustakaan.controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class BukuController {

    public DefaultTableModel tampilData() throws Exception {
        DefaultTableModel model = buatModelTabel();
        Connection conn = Koneksi.getConnection();
        String sql = "SELECT id_buku, judul, penulis, genre, stok FROM buku";

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("penulis"),
                    rs.getString("genre"),
                    rs.getInt("stok")
                });
            }
        }

        return model;
    }

    public DefaultTableModel cariData(String kataKunci) throws Exception {
        DefaultTableModel model = buatModelTabel();
        Connection conn = Koneksi.getConnection();
        String sql = "SELECT id_buku, judul, penulis, genre, stok FROM buku "
                + "WHERE judul LIKE ? OR penulis LIKE ? OR genre LIKE ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            String keyword = "%" + kataKunci.trim() + "%";
            pst.setString(1, keyword);
            pst.setString(2, keyword);
            pst.setString(3, keyword);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("penulis"),
                    rs.getString("genre"),
                    rs.getInt("stok")
                });
            }
        }

        return model;
    }

    public DefaultTableModel tampilBukuTersedia() throws Exception {
        DefaultTableModel model = buatModelTabelAnggota();
        Connection conn = Koneksi.getConnection();
        String sql = "SELECT judul, penulis, genre, stok FROM buku WHERE stok > 0 ORDER BY judul";

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("judul"),
                    rs.getString("penulis"),
                    rs.getString("genre"),
                    rs.getInt("stok")
                });
            }
        }

        return model;
    }

    public DefaultTableModel cariBukuTersedia(String kataKunci) throws Exception {
        DefaultTableModel model = buatModelTabelAnggota();
        Connection conn = Koneksi.getConnection();
        String sql = "SELECT judul, penulis, genre, stok FROM buku "
                + "WHERE stok > 0 AND (judul LIKE ? OR penulis LIKE ? OR genre LIKE ?) "
                + "ORDER BY judul";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            String keyword = "%" + kataKunci.trim() + "%";
            pst.setString(1, keyword);
            pst.setString(2, keyword);
            pst.setString(3, keyword);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("judul"),
                    rs.getString("penulis"),
                    rs.getString("genre"),
                    rs.getInt("stok")
                });
            }
        }

        return model;
    }

    public void simpan(String idBuku, String judul, String penulis, String genre, int stok) throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "INSERT INTO buku(id_buku, judul, penulis, genre, stok) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, idBuku);
            pst.setString(2, judul);
            pst.setString(3, penulis);
            pst.setString(4, genre);
            pst.setInt(5, stok);
            pst.executeUpdate();
        }
    }

    public void edit(String idLama, String idBuku, String judul, String penulis, String genre, int stok) throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "UPDATE buku SET id_buku=?, judul=?, penulis=?, genre=?, stok=? WHERE id_buku=?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, idBuku);
            pst.setString(2, judul);
            pst.setString(3, penulis);
            pst.setString(4, genre);
            pst.setInt(5, stok);
            pst.setString(6, idLama);
            pst.executeUpdate();
        }
    }

    public void hapus(String idBuku) throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "DELETE FROM buku WHERE id_buku=?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, idBuku);
            pst.executeUpdate();
        }
    }

    public void updateStok(String idBuku, int stok) throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "UPDATE buku SET stok=? WHERE id_buku=?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, stok);
            pst.setString(2, idBuku);
            pst.executeUpdate();
        }
    }

    public void pastikanKolomBuku() throws Exception {
        Connection conn = Koneksi.getConnection();
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getColumns(null, null, "buku", "genre");

        if (!rs.next()) {
            String sql = "ALTER TABLE buku ADD COLUMN genre VARCHAR(50)";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.executeUpdate();
            }
        }
    }

    private DefaultTableModel buatModelTabel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Buku");
        model.addColumn("Judul");
        model.addColumn("Penulis");
        model.addColumn("Genre");
        model.addColumn("Stok");
        return model;
    }

    private DefaultTableModel buatModelTabelAnggota() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Judul Buku");
        model.addColumn("Penulis");
        model.addColumn("Genre");
        model.addColumn("Stok Tersedia");
        return model;
    }
}
