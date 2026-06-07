package sistemperpustakaan;

import com.formdev.flatlaf.FlatDarkLaf;

public class FlatLafSetup {
    public static void setup() {
        try {
            // Mengubah tema aplikasi menjadi FlatLaf Dark (Tema Gelap)
            FlatDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

