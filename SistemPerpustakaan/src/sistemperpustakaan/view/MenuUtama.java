package sistemperpustakaan.view;

import sistemperpustakaan.controller.MenuUtamaController;

public class MenuUtama extends javax.swing.JFrame {

    private String namaPustakawan = "";
    private final MenuUtamaController controller = new MenuUtamaController();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuUtama.class.getName());

    public MenuUtama() {
        this("");
    }

    public MenuUtama(String namaPustakawan) {
        this.namaPustakawan = namaPustakawan;
        initComponents();
        tampilProfilPustakawan();
        tampilOverview();
        setLocationRelativeTo(null);
        setTitle("Menu Pustakawan");
    }

    private void tampilProfilPustakawan() {
        String[] profil = controller.cariProfilPustakawan(namaPustakawan);
        lblNama.setText("Nama: " + nilai(profil[0]));
        lblNip.setText("NIP: " + nilai(profil[1]));
        lblShiftKerja.setText("Shift Kerja: " + nilai(profil[2]));
    }

    private void tampilOverview() {
        String[] overview = controller.getOverview();
        lblTotalBuku.setText(overview[0]);
        lblTotalAnggota.setText(overview[1]);
        lblPeminjamanAktif.setText(overview[2]);
        lblTotalPeminjaman.setText(overview[3]);
    }

    private String nilai(String teks) {
        return teks == null || teks.isEmpty() ? "-" : teks;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTambahBuku = new javax.swing.JButton();
        btnPeminjamanPengembalian = new javax.swing.JButton();
        btnPetugasShift = new javax.swing.JButton();
        btnAnggota = new javax.swing.JButton();
        btnLaporan = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        panelOverview = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblNip = new javax.swing.JLabel();
        lblShiftKerja = new javax.swing.JLabel();
        panelTotalBuku = new javax.swing.JPanel();
        lblJudulTotalBuku = new javax.swing.JLabel();
        lblTotalBuku = new javax.swing.JLabel();
        panelTotalAnggota = new javax.swing.JPanel();
        lblJudulTotalAnggota = new javax.swing.JLabel();
        lblTotalAnggota = new javax.swing.JLabel();
        panelPeminjamanAktif = new javax.swing.JPanel();
        lblJudulPeminjamanAktif = new javax.swing.JLabel();
        lblPeminjamanAktif = new javax.swing.JLabel();
        panelTotalPeminjaman = new javax.swing.JPanel();
        lblJudulTotalPeminjaman = new javax.swing.JLabel();
        lblTotalPeminjaman = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(55, 57, 62));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTambahBuku.setBackground(new java.awt.Color(88, 92, 100));
        btnTambahBuku.setForeground(new java.awt.Color(245, 245, 245));
        btnTambahBuku.setText("TAMBAH BUKU");
        btnTambahBuku.setFocusPainted(false);
        btnTambahBuku.addActionListener(this::btnTambahBukuActionPerformed);
        getContentPane().add(btnTambahBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 180, 32));

        btnPeminjamanPengembalian.setBackground(new java.awt.Color(88, 92, 100));
        btnPeminjamanPengembalian.setForeground(new java.awt.Color(245, 245, 245));
        btnPeminjamanPengembalian.setText("PEMINJAMAN");
        btnPeminjamanPengembalian.setFocusPainted(false);
        btnPeminjamanPengembalian.addActionListener(this::btnPeminjamanPengembalianActionPerformed);
        getContentPane().add(btnPeminjamanPengembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 54, 180, 32));

        btnPetugasShift.setBackground(new java.awt.Color(88, 92, 100));
        btnPetugasShift.setForeground(new java.awt.Color(245, 245, 245));
        btnPetugasShift.setText("PETUGAS SHIFT");
        btnPetugasShift.setFocusPainted(false);
        btnPetugasShift.addActionListener(this::btnPetugasShiftActionPerformed);
        getContentPane().add(btnPetugasShift, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 94, 180, 32));

        btnAnggota.setBackground(new java.awt.Color(88, 92, 100));
        btnAnggota.setForeground(new java.awt.Color(245, 245, 245));
        btnAnggota.setText("DATA ANGGOTA");
        btnAnggota.setFocusPainted(false);
        btnAnggota.addActionListener(this::btnAnggotaActionPerformed);
        getContentPane().add(btnAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 134, 180, 32));

        btnLaporan.setBackground(new java.awt.Color(88, 92, 100));
        btnLaporan.setForeground(new java.awt.Color(245, 245, 245));
        btnLaporan.setText("LIHAT LAPORAN");
        btnLaporan.setFocusPainted(false);
        btnLaporan.addActionListener(this::btnLaporanActionPerformed);
        getContentPane().add(btnLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 174, 180, 32));

        btnLogout.setBackground(new java.awt.Color(88, 92, 100));
        btnLogout.setForeground(new java.awt.Color(245, 245, 245));
        btnLogout.setText("LOGOUT");
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 214, 180, 32));

        panelOverview.setBackground(new java.awt.Color(68, 71, 78));
        panelOverview.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(105, 109, 118)));
        panelOverview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(245, 245, 245));
        lblJudul.setText("OVERVIEW PUSTAKAWAN");
        panelOverview.add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 18, 330, -1));

        lblNama.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNama.setForeground(new java.awt.Color(222, 222, 222));
        lblNama.setText("Nama: -");
        panelOverview.add(lblNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 47, 330, -1));

        lblNip.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNip.setForeground(new java.awt.Color(222, 222, 222));
        lblNip.setText("NIP: -");
        panelOverview.add(lblNip, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 68, 330, -1));

        lblShiftKerja.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblShiftKerja.setForeground(new java.awt.Color(222, 222, 222));
        lblShiftKerja.setText("Shift Kerja: -");
        panelOverview.add(lblShiftKerja, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 89, 330, -1));

        panelTotalBuku.setBackground(new java.awt.Color(78, 82, 90));
        panelTotalBuku.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(105, 109, 118)));
        panelTotalBuku.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudulTotalBuku.setForeground(new java.awt.Color(222, 222, 222));
        lblJudulTotalBuku.setText("Total buku");
        panelTotalBuku.add(lblJudulTotalBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, 170, -1));

        lblTotalBuku.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalBuku.setForeground(new java.awt.Color(245, 245, 245));
        lblTotalBuku.setText("0");
        panelTotalBuku.add(lblTotalBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 36, 170, -1));

        panelOverview.add(panelTotalBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 125, 188, 82));

        panelTotalAnggota.setBackground(new java.awt.Color(78, 82, 90));
        panelTotalAnggota.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(105, 109, 118)));
        panelTotalAnggota.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudulTotalAnggota.setForeground(new java.awt.Color(222, 222, 222));
        lblJudulTotalAnggota.setText("Total anggota");
        panelTotalAnggota.add(lblJudulTotalAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, 170, -1));

        lblTotalAnggota.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalAnggota.setForeground(new java.awt.Color(245, 245, 245));
        lblTotalAnggota.setText("0");
        panelTotalAnggota.add(lblTotalAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 36, 170, -1));

        panelOverview.add(panelTotalAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 125, 188, 82));

        panelPeminjamanAktif.setBackground(new java.awt.Color(78, 82, 90));
        panelPeminjamanAktif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(105, 109, 118)));
        panelPeminjamanAktif.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudulPeminjamanAktif.setForeground(new java.awt.Color(222, 222, 222));
        lblJudulPeminjamanAktif.setText("Sedang dipinjam");
        panelPeminjamanAktif.add(lblJudulPeminjamanAktif, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, 170, -1));

        lblPeminjamanAktif.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPeminjamanAktif.setForeground(new java.awt.Color(245, 245, 245));
        lblPeminjamanAktif.setText("0");
        panelPeminjamanAktif.add(lblPeminjamanAktif, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 36, 170, -1));

        panelOverview.add(panelPeminjamanAktif, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 219, 188, 82));

        panelTotalPeminjaman.setBackground(new java.awt.Color(78, 82, 90));
        panelTotalPeminjaman.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(105, 109, 118)));
        panelTotalPeminjaman.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudulTotalPeminjaman.setForeground(new java.awt.Color(222, 222, 222));
        lblJudulTotalPeminjaman.setText("Total peminjaman");
        panelTotalPeminjaman.add(lblJudulTotalPeminjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, 170, -1));

        lblTotalPeminjaman.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalPeminjaman.setForeground(new java.awt.Color(245, 245, 245));
        lblTotalPeminjaman.setText("0");
        panelTotalPeminjaman.add(lblTotalPeminjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 36, 170, -1));

        panelOverview.add(panelTotalPeminjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 219, 188, 82));

        getContentPane().add(panelOverview, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 14, 432, 330));

        pack();
        setSize(new java.awt.Dimension(670, 385));
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahBukuActionPerformed(java.awt.event.ActionEvent evt) {
        new BukuView().setVisible(true);
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
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPeminjamanPengembalian;
    private javax.swing.JButton btnPetugasShift;
    private javax.swing.JButton btnTambahBuku;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblJudulPeminjamanAktif;
    private javax.swing.JLabel lblJudulTotalAnggota;
    private javax.swing.JLabel lblJudulTotalBuku;
    private javax.swing.JLabel lblJudulTotalPeminjaman;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNip;
    private javax.swing.JLabel lblPeminjamanAktif;
    private javax.swing.JLabel lblShiftKerja;
    private javax.swing.JLabel lblTotalAnggota;
    private javax.swing.JLabel lblTotalBuku;
    private javax.swing.JLabel lblTotalPeminjaman;
    private javax.swing.JPanel panelOverview;
    private javax.swing.JPanel panelPeminjamanAktif;
    private javax.swing.JPanel panelTotalAnggota;
    private javax.swing.JPanel panelTotalBuku;
    private javax.swing.JPanel panelTotalPeminjaman;
    // End of variables declaration//GEN-END:variables
}
