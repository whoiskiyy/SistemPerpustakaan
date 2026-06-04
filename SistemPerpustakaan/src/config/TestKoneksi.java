package config;

import java.sql.Connection;

public class TestKoneksi {

    public static void main(String[] args) {

        Connection conn = Koneksi.getConnection();

        if (conn != null) {
            System.out.println("Database berhasil terhubung!");
        } else {
            System.out.println("Database gagal terhubung!");
        }
    }
}