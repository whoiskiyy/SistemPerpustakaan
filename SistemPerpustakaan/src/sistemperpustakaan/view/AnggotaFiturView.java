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
        btnPinjam = new javax.swing.JButton();
        btnUlasan = new javax.swing.JButton();
        lblJudul = new javax.swing.JLabel();
        lblNomor = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblTelepon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbBuku = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtTanggalPinjam = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTanggalKembali = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRiwayat = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtUlasan = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUlasan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        btnPinjam.setText("PINJAM BUKU");
        btnPinjam.addActionListener(this::btnPinjamActionPerformed);

        btnUlasan.setText("KIRIM ULASAN");
        btnUlasan.addActionListener(this::btnUlasanActionPerformed);

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("PEMINJAMAN");

        lblNomor.setText("Nomor Anggota: -");

        lblNama.setText("Nama: -");

        lblAlamat.setText("Alamat: -");

        lblTelepon.setText("Telepon: -");

        jLabel1.setText("Pilih Buku");

        jLabel2.setText("Tanggal Pinjam (yyyy-MM-dd)");

        jLabel3.setText("Tanggal Kembali (yyyy-MM-dd)");

        jScrollPane1.setViewportView(tblRiwayat);

        jLabel4.setText("Tulis Ulasan");

        txtUlasan.setColumns(20);
        txtUlasan.setRows(4);
        jScrollPane2.setViewportView(txtUlasan);

        jScrollPane3.setViewportView(tblUlasan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lblJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomor, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnUlasan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblNomor)
                        .addGap(10, 10, 10)
                        .addComponent(lblNama))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(7, 7, 7)
                        .addComponent(cmbBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUlasan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAlamat)
                        .addGap(10, 10, 10)
                        .addComponent(lblTelepon))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(7, 7, 7)
                        .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(7, 7, 7)
                        .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
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
