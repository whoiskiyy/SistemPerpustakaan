/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemperpustakaan.view;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author rizky
 */
public class MenuUtama extends javax.swing.JFrame {

    private String namaPustakawan = "";

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuUtama.class.getName());

    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        this("");
    }

    public MenuUtama(String namaPustakawan) {
        this.namaPustakawan = namaPustakawan;
        initComponents();
        tampilProfilPustakawan();
        setLocationRelativeTo(null);
        setTitle("Menu Pustakawan");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblJudul = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblNip = new javax.swing.JLabel();
        lblShiftKerja = new javax.swing.JLabel();
        btnTambahBuku = new javax.swing.JButton();
        btnKelolaStok = new javax.swing.JButton();
        btnPeminjamanPengembalian = new javax.swing.JButton();
        btnPetugasShift = new javax.swing.JButton();
        btnAnggota = new javax.swing.JButton();
        btnLaporan = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("MENU PUSTAKAWAN");

        lblNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNama.setText("Nama: -");

        lblNip.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNip.setText("NIP: -");

        lblShiftKerja.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblShiftKerja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShiftKerja.setText("Shift Kerja: -");

        btnTambahBuku.setText("TAMBAH BUKU");
        btnTambahBuku.addActionListener(this::btnTambahBukuActionPerformed);

        btnKelolaStok.setText("KELOLA STOK");
        btnKelolaStok.addActionListener(this::btnKelolaStokActionPerformed);

        btnPeminjamanPengembalian.setText("PEMINJAMAN & PENGEMBALIAN");
        btnPeminjamanPengembalian.addActionListener(this::btnPeminjamanPengembalianActionPerformed);

        btnPetugasShift.setText("PETUGAS SHIFT");
        btnPetugasShift.addActionListener(this::btnPetugasShiftActionPerformed);

        btnAnggota.setText("DATA ANGGOTA");
        btnAnggota.addActionListener(this::btnAnggotaActionPerformed);

        btnLaporan.setText("LIHAT LAPORAN");
        btnLaporan.addActionListener(this::btnLaporanActionPerformed);

        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNip, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblShiftKerja, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambahBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKelolaStok, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPeminjamanPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPetugasShift, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lblNama)
                .addGap(6, 6, 6)
                .addComponent(lblNip)
                .addGap(6, 6, 6)
                .addComponent(lblShiftKerja)
                .addGap(18, 18, 18)
                .addComponent(btnTambahBuku)
                .addGap(12, 12, 12)
                .addComponent(btnKelolaStok)
                .addGap(12, 12, 12)
                .addComponent(btnPeminjamanPengembalian)
                .addGap(12, 12, 12)
                .addComponent(btnPetugasShift)
                .addGap(12, 12, 12)
                .addComponent(btnAnggota)
                .addGap(12, 12, 12)
                .addComponent(btnLaporan)
                .addGap(12, 12, 12)
                .addComponent(btnLogout)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tampilProfilPustakawan() {
        String[] profil = cariProfilPustakawan();
        lblNama.setText("Nama: " + nilai(profil[0]));
        lblNip.setText("NIP: " + nilai(profil[1]));
        lblShiftKerja.setText("Shift Kerja: " + nilai(profil[2]));
    }

    private String[] cariProfilPustakawan() {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT nama, nip, shift_kerja FROM shift_aktif WHERE id=1";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new String[] {
                    rs.getString("nama"),
                    rs.getString("nip"),
                    rs.getString("shift_kerja")
                };
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
                PreparedStatement pst = conn.prepareStatement(query[0]);
                pst.setString(1, namaPustakawan);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    return new String[] {
                        namaPustakawan,
                        rs.getString(query[1]),
                        rs.getString(query[2])
                    };
                }
            } catch (Exception e) {
                // Coba struktur tabel berikutnya jika kolom belum ada.
            }
        }

        return new String[] {namaPustakawan, "-", "-"};
    }

    private String nilai(String teks) {
        return teks == null || teks.isEmpty() ? "-" : teks;
    }

    private void btnTambahBukuActionPerformed(java.awt.event.ActionEvent evt) {
        new BukuView().setVisible(true);
        this.dispose();
    }

    private void btnKelolaStokActionPerformed(java.awt.event.ActionEvent evt) {
        new KelolaStokView().setVisible(true);
        this.dispose();
    }

    private void btnPeminjamanPengembalianActionPerformed(java.awt.event.ActionEvent evt) {
        new PeminjamanView().setVisible(true);
        this.dispose();
    }

    private void btnPetugasShiftActionPerformed(java.awt.event.ActionEvent evt) {
        new PetugasShiftView(namaPustakawan).setVisible(true);
        this.dispose();
    }

    private void btnAnggotaActionPerformed(java.awt.event.ActionEvent evt) {
        new AnggotaView().setVisible(true);
        this.dispose();
    }

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {
        new LaporanView().setVisible(true);
        this.dispose();
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        new LoginView().setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new MenuUtama().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnggota;
    private javax.swing.JButton btnKelolaStok;
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPeminjamanPengembalian;
    private javax.swing.JButton btnPetugasShift;
    private javax.swing.JButton btnTambahBuku;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNip;
    private javax.swing.JLabel lblShiftKerja;
    // End of variables declaration//GEN-END:variables
}
