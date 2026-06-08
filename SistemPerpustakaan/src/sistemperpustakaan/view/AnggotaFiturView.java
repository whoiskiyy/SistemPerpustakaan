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

    private void initComponents() {
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
        btnKembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("FITUR ANGGOTA");

        lblNomor.setText("Nomor Anggota: -");
        lblNama.setText("Nama: " + namaAnggota);
        lblAlamat.setText("Alamat: -");
        lblTelepon.setText("Telepon: -");

        txtTanggalPinjam.setText(LocalDate.now().toString());
        txtTanggalKembali.setText(LocalDate.now().plusDays(7).toString());

        btnPinjam.setText("PINJAM BUKU");
        btnPinjam.addActionListener(this::btnPinjamActionPerformed);

        tblRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Buku", "Tanggal Pinjam", "Tanggal Kembali", "Status"}
        ));
        jScrollPane1.setViewportView(tblRiwayat);

        txtUlasan.setColumns(20);
        txtUlasan.setRows(4);
        jScrollPane2.setViewportView(txtUlasan);

        btnUlasan.setText("KIRIM ULASAN");
        btnUlasan.addActionListener(this::btnUlasanActionPerformed);

        tblUlasan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Tanggal", "Ulasan"}
        ));
        jScrollPane3.setViewportView(tblUlasan);

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnKembali))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNomor, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(lblNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTelepon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbBuku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPinjam, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUlasan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblJudul)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNomor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNama)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAlamat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTelepon))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPinjam))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUlasan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

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
}
