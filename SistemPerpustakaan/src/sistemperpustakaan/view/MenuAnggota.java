package sistemperpustakaan.view;

import sistemperpustakaan.controller.MenuAnggotaController;

public class MenuAnggota extends javax.swing.JFrame {

    private final String namaAnggota;
    private final MenuAnggotaController controller = new MenuAnggotaController();

    public MenuAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
        initComponents();
        tampilOverview();
        setLocationRelativeTo(null);
        setTitle("Menu Anggota");
    }

    private void tampilOverview() {
        String[] overview = controller.getOverview(namaAnggota);
        lblNamaAnggota.setText("Selamat datang, " + nilai(namaAnggota));
        lblBukuTersedia.setText(overview[0]);
        lblPeminjamanAktif.setText(overview[1]);
        lblTotalRiwayat.setText(overview[2]);
        lblTotalUlasan.setText(overview[3]);
    }

    private String nilai(String teks) {
        return teks == null || teks.isEmpty() ? "-" : teks;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFiturAnggota = new javax.swing.JButton();
        btnBuku = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        panelOverview = new javax.swing.JPanel();
        lblJudulOverview = new javax.swing.JLabel();
        lblNamaAnggota = new javax.swing.JLabel();
        panelBukuTersedia = new javax.swing.JPanel();
        lblJudulBukuTersedia = new javax.swing.JLabel();
        lblBukuTersedia = new javax.swing.JLabel();
        panelPeminjamanAktif = new javax.swing.JPanel();
        lblJudulPeminjamanAktif = new javax.swing.JLabel();
        lblPeminjamanAktif = new javax.swing.JLabel();
        panelTotalRiwayat = new javax.swing.JPanel();
        lblJudulTotalRiwayat = new javax.swing.JLabel();
        lblTotalRiwayat = new javax.swing.JLabel();
        panelTotalUlasan = new javax.swing.JPanel();
        lblJudulTotalUlasan = new javax.swing.JLabel();
        lblTotalUlasan = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new java.awt.Color(55, 57, 62));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFiturAnggota.setBackground(new java.awt.Color(88, 92, 100));
        btnFiturAnggota.setForeground(new java.awt.Color(245, 245, 245));
        btnFiturAnggota.setText("PINJAM BUKU");
        btnFiturAnggota.setFocusPainted(false);
        btnFiturAnggota.addActionListener(this::btnFiturAnggotaActionPerformed);
        getContentPane().add(btnFiturAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 150, 32));

        btnBuku.setBackground(new java.awt.Color(88, 92, 100));
        btnBuku.setForeground(new java.awt.Color(245, 245, 245));
        btnBuku.setText("LIHAT BUKU");
        btnBuku.setFocusPainted(false);
        btnBuku.addActionListener(this::btnBukuActionPerformed);
        getContentPane().add(btnBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 54, 150, 32));

        btnLogout.setBackground(new java.awt.Color(88, 92, 100));
        btnLogout.setForeground(new java.awt.Color(245, 245, 245));
        btnLogout.setText("LOGOUT");
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 94, 150, 32));

        panelOverview.setBackground(new java.awt.Color(68, 71, 78));
        panelOverview.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(105, 109, 118)));
        panelOverview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudulOverview.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblJudulOverview.setForeground(new java.awt.Color(245, 245, 245));
        lblJudulOverview.setText("OVERVIEW ANGGOTA");
        panelOverview.add(lblJudulOverview, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 18, 300, -1));

        lblNamaAnggota.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNamaAnggota.setForeground(new java.awt.Color(222, 222, 222));
        lblNamaAnggota.setText("Selamat datang, -");
        panelOverview.add(lblNamaAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 47, 300, -1));

        panelBukuTersedia.setBackground(new java.awt.Color(78, 82, 90));
        panelBukuTersedia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(105, 109, 118)));
        panelBukuTersedia.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudulBukuTersedia.setForeground(new java.awt.Color(222, 222, 222));
        lblJudulBukuTersedia.setText("Buku tersedia");
        panelBukuTersedia.add(lblJudulBukuTersedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, 170, -1));

        lblBukuTersedia.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblBukuTersedia.setForeground(new java.awt.Color(245, 245, 245));
        lblBukuTersedia.setText("0");
        panelBukuTersedia.add(lblBukuTersedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 36, 170, -1));

        panelOverview.add(panelBukuTersedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 82, 188, 82));

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

        panelOverview.add(panelPeminjamanAktif, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 82, 188, 82));

        panelTotalRiwayat.setBackground(new java.awt.Color(78, 82, 90));
        panelTotalRiwayat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(105, 109, 118)));
        panelTotalRiwayat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudulTotalRiwayat.setForeground(new java.awt.Color(222, 222, 222));
        lblJudulTotalRiwayat.setText("Riwayat pinjam");
        panelTotalRiwayat.add(lblJudulTotalRiwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, 170, -1));

        lblTotalRiwayat.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalRiwayat.setForeground(new java.awt.Color(245, 245, 245));
        lblTotalRiwayat.setText("0");
        panelTotalRiwayat.add(lblTotalRiwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 36, 170, -1));

        panelOverview.add(panelTotalRiwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 176, 188, 82));

        panelTotalUlasan.setBackground(new java.awt.Color(78, 82, 90));
        panelTotalUlasan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(105, 109, 118)));
        panelTotalUlasan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudulTotalUlasan.setForeground(new java.awt.Color(222, 222, 222));
        lblJudulTotalUlasan.setText("Ulasan terkirim");
        panelTotalUlasan.add(lblJudulTotalUlasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, 170, -1));

        lblTotalUlasan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalUlasan.setForeground(new java.awt.Color(245, 245, 245));
        lblTotalUlasan.setText("0");
        panelTotalUlasan.add(lblTotalUlasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 36, 170, -1));

        panelOverview.add(panelTotalUlasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 176, 188, 82));

        getContentPane().add(panelOverview, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 14, 432, 285));

        pack();
        setSize(new java.awt.Dimension(640, 340));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBukuActionPerformed(java.awt.event.ActionEvent evt) {
        new BukuView(true, namaAnggota).setVisible(true);
        this.dispose();
    }

    private void btnFiturAnggotaActionPerformed(java.awt.event.ActionEvent evt) {
        new AnggotaFiturView(namaAnggota).setVisible(true);
        this.dispose();
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        new LoginView().setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuku;
    private javax.swing.JButton btnFiturAnggota;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel lblBukuTersedia;
    private javax.swing.JLabel lblJudulBukuTersedia;
    private javax.swing.JLabel lblJudulOverview;
    private javax.swing.JLabel lblJudulPeminjamanAktif;
    private javax.swing.JLabel lblJudulTotalRiwayat;
    private javax.swing.JLabel lblJudulTotalUlasan;
    private javax.swing.JLabel lblNamaAnggota;
    private javax.swing.JLabel lblPeminjamanAktif;
    private javax.swing.JLabel lblTotalRiwayat;
    private javax.swing.JLabel lblTotalUlasan;
    private javax.swing.JPanel panelBukuTersedia;
    private javax.swing.JPanel panelOverview;
    private javax.swing.JPanel panelPeminjamanAktif;
    private javax.swing.JPanel panelTotalRiwayat;
    private javax.swing.JPanel panelTotalUlasan;
    // End of variables declaration//GEN-END:variables
}

