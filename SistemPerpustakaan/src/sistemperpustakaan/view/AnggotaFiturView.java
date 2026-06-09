package sistemperpustakaan.view;

import javax.swing.JOptionPane;
import sistemperpustakaan.controller.AnggotaFiturController;

public class AnggotaFiturView extends javax.swing.JFrame {

    private final String namaAnggota;
    private final AnggotaFiturController controller = new AnggotaFiturController();

    public AnggotaFiturView(String namaAnggota) {
        this.namaAnggota = namaAnggota;
        initComponents();
        terapkanWarna();
        setLocationRelativeTo(null);
        setTitle("Fitur Anggota");
        pastikanTabelUlasan();
        tampilProfil();
        loadBuku();
        tampilRiwayat();
        tampilUlasan();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnKembali = new javax.swing.JButton();
        lblJudul = new javax.swing.JLabel();
        lblNomor = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblTelepon = new javax.swing.JLabel();
        cmbBuku = new javax.swing.JComboBox<>();
        txtTanggalPinjam = new javax.swing.JTextField();
        txtTanggalKembali = new javax.swing.JTextField();
        btnPinjam = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRiwayat = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtUlasan = new javax.swing.JTextArea();
        btnUlasan = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUlasan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("PEMINJAMAN");

        lblNomor.setText("Nomor Anggota: -");

        lblNama.setText("Nama: -");

        lblAlamat.setText("Alamat: -");

        lblTelepon.setText("Telepon: -");

        btnPinjam.setText("PINJAM BUKU");
        btnPinjam.addActionListener(this::btnPinjamActionPerformed);

        jScrollPane1.setViewportView(tblRiwayat);

        txtUlasan.setColumns(20);
        txtUlasan.setRows(4);
        jScrollPane2.setViewportView(txtUlasan);

        btnUlasan.setText("KIRIM ULASAN");
        btnUlasan.addActionListener(this::btnUlasanActionPerformed);

        jScrollPane3.setViewportView(tblUlasan);

        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 32));
        getContentPane().add(btnPinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, 32));
        getContentPane().add(btnUlasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, 44));
        getContentPane().add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 18, 620, 28));
        getContentPane().add(lblNomor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 58, 260, -1));
        getContentPane().add(lblNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 84, 260, -1));
        getContentPane().add(lblAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 260, -1));
        getContentPane().add(lblTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 136, 260, -1));
        jLabel1.setText("Pilih Buku");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 52, 120, -1));
        getContentPane().add(cmbBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 75, 340, -1));
        jLabel2.setText("Tanggal Pinjam (yyyy-MM-dd)");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 170, -1));
        getContentPane().add(txtTanggalPinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 133, 160, -1));
        jLabel3.setText("Tanggal Kembali (yyyy-MM-dd)");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 180, -1));
        getContentPane().add(txtTanggalKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 133, 160, -1));
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 175, 660, 150));
        jLabel4.setText("Tulis Ulasan");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 326, 120, -1));
        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 345, 660, 70));
        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 435, 660, 130));

        pack();
        setSize(new java.awt.Dimension(900, 640));
    }// </editor-fold>//GEN-END:initComponents

    private void tampilProfil() {
        try {
            String[] profil = controller.tampilProfil(namaAnggota);
            lblNomor.setText("Nomor Anggota: " + profil[0]);
            lblNama.setText("Nama: " + profil[1]);
            lblAlamat.setText("Alamat: " + profil[2]);
            lblTelepon.setText("Telepon: " + profil[3]);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void loadBuku() {
        try {
            cmbBuku.setModel(controller.loadBukuTersedia());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void tampilRiwayat() {
        try {
            tblRiwayat.setModel(controller.tampilRiwayat(namaAnggota));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void tampilUlasan() {
        try {
            tblUlasan.setModel(controller.tampilUlasan(namaAnggota));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnPinjamActionPerformed(java.awt.event.ActionEvent evt) {
        if (cmbBuku.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Pilih buku terlebih dahulu");
            return;
        }

        try {
            String judulBuku = cmbBuku.getSelectedItem().toString();
            controller.pinjamBuku(namaAnggota, judulBuku,
                    txtTanggalPinjam.getText().trim(),
                    txtTanggalKembali.getText().trim());
            JOptionPane.showMessageDialog(this, "Buku berhasil dipinjam");
            loadBuku();
            tampilRiwayat();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnUlasanActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtUlasan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ulasan tidak boleh kosong");
            return;
        }

        try {
            controller.simpanUlasan(namaAnggota, txtUlasan.getText().trim());
            JOptionPane.showMessageDialog(this, "Ulasan berhasil dikirim");
            txtUlasan.setText("");
            tampilUlasan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        new MenuAnggota(namaAnggota).setVisible(true);
        this.dispose();
    }

    private void pastikanTabelUlasan() {
        try {
            controller.pastikanTabelUlasan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void terapkanWarna() {
        java.awt.Color background = new java.awt.Color(55, 57, 62);
        java.awt.Color panel = new java.awt.Color(68, 71, 78);
        java.awt.Color button = new java.awt.Color(88, 92, 100);
        java.awt.Color border = new java.awt.Color(105, 109, 118);
        java.awt.Color text = new java.awt.Color(245, 245, 245);

        getContentPane().setBackground(background);
        for (javax.swing.JButton tombol : new javax.swing.JButton[]{btnKembali, btnPinjam, btnUlasan}) {
            tombol.setBackground(button);
            tombol.setForeground(text);
            tombol.setFocusPainted(false);
        }
        for (javax.swing.JLabel label : new javax.swing.JLabel[]{lblJudul, lblNomor, lblNama, lblAlamat, lblTelepon, jLabel1, jLabel2, jLabel3, jLabel4}) {
            label.setForeground(text);
        }
        cmbBuku.setBackground(panel);
        cmbBuku.setForeground(text);
        for (javax.swing.JTextField field : new javax.swing.JTextField[]{txtTanggalPinjam, txtTanggalKembali}) {
            field.setBackground(panel);
            field.setForeground(text);
            field.setCaretColor(text);
            field.setBorder(javax.swing.BorderFactory.createLineBorder(border));
        }
        txtUlasan.setBackground(panel);
        txtUlasan.setForeground(text);
        txtUlasan.setCaretColor(text);
        for (javax.swing.JTable tabel : new javax.swing.JTable[]{tblRiwayat, tblUlasan}) {
            tabel.setBackground(panel);
            tabel.setForeground(text);
            tabel.setGridColor(border);
            tabel.setSelectionBackground(new java.awt.Color(82, 107, 150));
            tabel.setSelectionForeground(text);
            tabel.getTableHeader().setBackground(button);
            tabel.getTableHeader().setForeground(text);
        }
        for (javax.swing.JScrollPane scroll : new javax.swing.JScrollPane[]{jScrollPane1, jScrollPane2, jScrollPane3}) {
            scroll.getViewport().setBackground(panel);
            scroll.setBorder(javax.swing.BorderFactory.createLineBorder(border));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnPinjam;
    private javax.swing.JButton btnUlasan;
    private javax.swing.JComboBox<String> cmbBuku;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNomor;
    private javax.swing.JLabel lblTelepon;
    private javax.swing.JTable tblRiwayat;
    private javax.swing.JTable tblUlasan;
    private javax.swing.JTextField txtTanggalKembali;
    private javax.swing.JTextField txtTanggalPinjam;
    private javax.swing.JTextArea txtUlasan;
    // End of variables declaration//GEN-END:variables
}
