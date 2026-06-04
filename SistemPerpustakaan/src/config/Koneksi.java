package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static Connection koneksi;

    public static Connection getConnection() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost/perpustakaan";
                String user = "root";
                String password = "";

                Class.forName("com.mysql.cj.jdbc.Driver");

                koneksi = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi Berhasil");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Koneksi Gagal: " + e.getMessage());
            }
        }
        return koneksi;
    }
}
