package sistemperpustakaan.controller;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    public String login(String username, String password, String role) throws Exception {
        Connection conn = Koneksi.getConnection();

        if ("Pustakawan".equals(role)) {
            return loginPustakawan(conn, username, password);
        }

        return loginAnggota(conn, username, password);
    }

    private String loginPustakawan(Connection conn, String username, String password) {
        String[] sqlList = {
            "SELECT nama FROM pustakawan WHERE username=? AND password=?",
            "SELECT nama FROM pustakawan WHERE email=? AND password=?",
            "SELECT username AS nama FROM user WHERE username=? AND password=? AND role='pustakawan'",
            "SELECT username AS nama FROM user WHERE username=? AND password=?"
        };

        return cariUser(conn, sqlList, username, password);
    }

    private String loginAnggota(Connection conn, String username, String password) {
        String[] sqlList = {
            "SELECT nama FROM anggota WHERE username=? AND password=?",
            "SELECT nama FROM anggota WHERE email=? AND password=?",
            "SELECT nama FROM anggota WHERE nama=? AND no_hp=?",
            "SELECT username AS nama FROM user WHERE username=? AND password=? AND role='anggota'"
        };

        return cariUser(conn, sqlList, username, password);
    }

    private String cariUser(Connection conn, String[] sqlList, String username, String password) {
        for (String sql : sqlList) {
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getString("nama");
                }
            } catch (Exception e) {
                // Coba query berikutnya jika tabel/kolom belum tersedia di database.
            }
        }

        return null;
    }
}
