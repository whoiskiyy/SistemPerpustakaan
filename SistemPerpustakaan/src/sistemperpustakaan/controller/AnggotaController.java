package sistemperpustakaan.controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class AnggotaController {

    public DefaultTableModel tampilData() throws Exception {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("No HP");

        Connection conn = Koneksi.getConnection();
        String sql = "SELECT * FROM anggota";

        try (PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id_anggota"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("no_hp")
                });
            }
        }

        return model;
    }

    public void simpan(String nama, String alamat, String noHp) throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "INSERT INTO anggota(nama, alamat, no_hp) VALUES (?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nama);
            pst.setString(2, alamat);
            pst.setString(3, noHp);
            pst.executeUpdate();
        }
    }

    public void edit(String id, String nama, String alamat, String noHp) throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "UPDATE anggota SET nama=?, alamat=?, no_hp=? WHERE id_anggota=?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nama);
            pst.setString(2, alamat);
            pst.setString(3, noHp);
            pst.setString(4, id);
            pst.executeUpdate();
        }
    }

    public void hapus(String id) throws Exception {
        Connection conn = Koneksi.getConnection();
        String sql = "DELETE FROM anggota WHERE id_anggota=?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, id);
            pst.executeUpdate();
        }
    }
}
