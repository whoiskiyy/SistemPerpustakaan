package sistemperpustakaan.view;

import config.Koneksi;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AnggotaFiturView extends javax.swing.JFrame {

    private final String namaAnggota;

    public AnggotaFiturView(String namaAnggota) {
        this.namaAnggota = namaAnggota;
        initComponents();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(560, 560, 560)
                .addComponent(btnKembali))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblNomor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(cmbBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNama, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnUlasan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnKembali)
                .addGap(7, 7, 7)
                .addComponent(lblJudul)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomor)
                    .addComponent(cmbBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNama)
                        .addGap(6, 6, 6)
                        .addComponent(lblAlamat))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPinjam))))
                .addGap(6, 6, 6)
                .addComponent(lblTelepon)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUlasan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tampilProfil() {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT * FROM anggota WHERE nama=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, namaAnggota);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nomor = punyaKolom(conn, "anggota", "nomor_anggota")
                        ? rs.getString("nomor_anggota")
                        : rs.getString("id_anggota");

                lblNomor.setText("Nomor Anggota: " + nilai(nomor));
                lblNama.setText("Nama: " + nilai(rs.getString("nama")));
                lblAlamat.setText("Alamat: " + nilai(rs.getString("alamat")));
                lblTelepon.setText("Telepon: " + nilai(rs.getString("no_hp")));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void loadBuku() {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT judul FROM buku WHERE stok > 0";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            cmbBuku.removeAllItems();
            cmbBuku.addItem("Pilih Buku");

            while (rs.next()) {
                cmbBuku.addItem(rs.getString("judul"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void tampilRiwayat() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Buku");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Status");

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT * FROM peminjaman WHERE nama_anggota=? ORDER BY id_pinjam DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, namaAnggota);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id_pinjam"),
                    rs.getString("judul_buku"),
                    rs.getString("tanggal_pinjam"),
                    rs.getString("tanggal_kembali"),
                    rs.getString("status")
                });
            }

            tblRiwayat.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void tampilUlasan() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tanggal");
        model.addColumn("Ulasan");

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT tanggal, ulasan FROM ulasan WHERE nama_anggota=? ORDER BY id_ulasan DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, namaAnggota);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("tanggal"),
                    rs.getString("ulasan")
                });
            }

            tblUlasan.setModel(model);
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
            LocalDate.parse(txtTanggalPinjam.getText().trim());
            LocalDate.parse(txtTanggalKembali.getText().trim());

            Connection conn = Koneksi.getConnection();
            conn.setAutoCommit(false);

            String judulBuku = cmbBuku.getSelectedItem().toString();
            String sqlStok = "UPDATE buku SET stok = stok - 1 WHERE judul=? AND stok > 0";
            PreparedStatement pstStok = conn.prepareStatement(sqlStok);
            pstStok.setString(1, judulBuku);

            if (pstStok.executeUpdate() == 0) {
                conn.rollback();
                JOptionPane.showMessageDialog(this, "Stok buku habis");
                return;
            }

            String sql = "INSERT INTO peminjaman(nama_anggota, judul_buku, tanggal_pinjam, tanggal_kembali, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, namaAnggota);
            pst.setString(2, judulBuku);
            pst.setString(3, txtTanggalPinjam.getText().trim());
            pst.setString(4, txtTanggalKembali.getText().trim());
            pst.setString(5, "Dipinjam");
            pst.executeUpdate();

            conn.commit();
            JOptionPane.showMessageDialog(this, "Buku berhasil dipinjam");
            loadBuku();
            tampilRiwayat();
        } catch (Exception e) {
            try {
                Koneksi.getConnection().rollback();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            JOptionPane.showMessageDialog(this, e.getMessage());
        } finally {
            try {
                Koneksi.getConnection().setAutoCommit(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void btnUlasanActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtUlasan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ulasan tidak boleh kosong");
            return;
        }

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO ulasan(nama_anggota, ulasan, tanggal) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, namaAnggota);
            pst.setString(2, txtUlasan.getText().trim());
            pst.setString(3, LocalDate.now().toString());
            pst.executeUpdate();

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
            Connection conn = Koneksi.getConnection();
            String sql = "CREATE TABLE IF NOT EXISTS ulasan ("
                    + "id_ulasan INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nama_anggota VARCHAR(100) NOT NULL,"
                    + "ulasan TEXT NOT NULL,"
                    + "tanggal DATE NOT NULL"
                    + ")";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private boolean punyaKolom(Connection conn, String tabel, String kolom) {
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getColumns(null, null, tabel, kolom);
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    private String nilai(String teks) {
        return teks == null || teks.isEmpty() ? "-" : teks;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnPinjam;
    private javax.swing.JButton btnUlasan;
    private javax.swing.JComboBox<String> cmbBuku;
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
