/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemperpustakaan.view;

import config.Koneksi;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rizky
 */
public class BukuView extends javax.swing.JFrame {
    String id;
    private boolean modeAnggota = false;
    private String namaAnggota = "";

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BukuView.class.getName());

    /**
     * Creates new form BukuView
     */
    public BukuView() {
        initComponents();
        pastikanKolomBuku();
        tampilData();
        setLocationRelativeTo(null);
        setTitle("Data Buku");
    }

    public BukuView(boolean modeAnggota, String namaAnggota) {
        this();
        this.modeAnggota = modeAnggota;
        this.namaAnggota = namaAnggota;

        if (modeAnggota) {
            btnSimpan.setVisible(false);
            btnEdit.setVisible(false);
            btnHapus.setVisible(false);
            btnReset.setVisible(false);
            setTitle("Daftar Buku");
        }
    }

    public void tampilData() {
        DefaultTableModel model = buatModelTabel();

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT id_buku, judul, penulis, genre, stok FROM buku";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("penulis"),
                    rs.getString("genre"),
                    rs.getString("stok")
                });
            }

            tblBuku.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void cariData() {
        DefaultTableModel model = buatModelTabel();

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT id_buku, judul, penulis, genre, stok FROM buku "
                    + "WHERE judul LIKE ? OR penulis LIKE ? OR genre LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            String kataKunci = "%" + txtCari.getText().trim() + "%";
            pst.setString(1, kataKunci);
            pst.setString(2, kataKunci);
            pst.setString(3, kataKunci);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("penulis"),
                    rs.getString("genre"),
                    rs.getString("stok")
                });
            }

            tblBuku.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private DefaultTableModel buatModelTabel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Buku");
        model.addColumn("Judul");
        model.addColumn("Penulis");
        model.addColumn("Genre");
        model.addColumn("Stok");
        return model;
    }

    private void pastikanKolomBuku() {
        try {
            Connection conn = Koneksi.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getColumns(null, null, "buku", "genre");

            if (!rs.next()) {
                String sql = "ALTER TABLE buku ADD COLUMN genre VARCHAR(50)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private boolean inputValid() {
        if (txtIdBuku.getText().trim().isEmpty()
                || txtJudul.getText().trim().isEmpty()
                || txtPenulis.getText().trim().isEmpty()
                || cmbGenre.getSelectedItem() == null
                || txtStok.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong");
            return false;
        }

        try {
            int stok = Integer.parseInt(txtStok.getText().trim());
            if (stok < 0) {
                JOptionPane.showMessageDialog(this, "Stok tidak boleh kurang dari 0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok harus berupa angka");
            return false;
        }

        return true;
    }

    private void resetForm() {
        txtIdBuku.setText("");
        txtJudul.setText("");
        txtPenulis.setText("");
        cmbGenre.setSelectedIndex(0);
        txtStok.setText("");
        id = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIdBuku = new javax.swing.JTextField();
        txtJudul = new javax.swing.JTextField();
        txtPenulis = new javax.swing.JTextField();
        cmbGenre = new javax.swing.JComboBox<>();
        txtStok = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBuku = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtJudul.addActionListener(this::txtJudulActionPerformed);

        cmbGenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ilustrasi", "Fiksi", "NonFiksi" }));
        cmbGenre.addActionListener(this::cmbGenreActionPerformed);

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(this::btnHapusActionPerformed);

        btnReset.setText("RESET");
        btnReset.addActionListener(this::btnResetActionPerformed);

        tblBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Buku", "Judul", "Penulis", "Genre", "Stok"
            }
        ));
        tblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBuku);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID Buku");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Judul");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Penulis");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Genre");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Stok");

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Cari Buku");

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(btnKembali))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel4)
                .addGap(33, 33, 33)
                .addComponent(cmbGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel5)
                .addGap(41, 41, 41)
                .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel6)
                .addGap(11, 11, 11)
                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnSimpan)
                .addGap(35, 35, 35)
                .addComponent(btnEdit)
                .addGap(30, 30, 30)
                .addComponent(btnHapus)
                .addGap(36, 36, 36)
                .addComponent(btnReset))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnKembali)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(cmbGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSimpan)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus)
                    .addComponent(btnReset))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbGenreActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus");
            return;
        }

        int pilih = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (pilih == JOptionPane.YES_OPTION) {
            try {
                Connection conn = Koneksi.getConnection();
                String sql = "DELETE FROM buku WHERE id_buku=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, id);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                resetForm();
                tampilData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        resetForm();
    }

    private void txtJudulActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        if (!inputValid()) {
            return;
        }

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO buku(id_buku, judul, penulis, genre, stok) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtIdBuku.getText().trim());
            pst.setString(2, txtJudul.getText().trim());
            pst.setString(3, txtPenulis.getText().trim());
            pst.setString(4, cmbGenre.getSelectedItem().toString());
            pst.setInt(5, Integer.parseInt(txtStok.getText().trim()));
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data Buku Berhasil Disimpan");
            resetForm();
            tampilData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {
        int baris = tblBuku.getSelectedRow();
        id = tblBuku.getValueAt(baris, 0).toString();

        txtIdBuku.setText(tblBuku.getValueAt(baris, 0).toString());
        txtJudul.setText(tblBuku.getValueAt(baris, 1).toString());
        txtPenulis.setText(tblBuku.getValueAt(baris, 2).toString());
        cmbGenre.setSelectedItem(tblBuku.getValueAt(baris, 3).toString());
        txtStok.setText(tblBuku.getValueAt(baris, 4).toString());
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diedit");
            return;
        }

        if (!inputValid()) {
            return;
        }

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "UPDATE buku SET id_buku=?, judul=?, penulis=?, genre=?, stok=? WHERE id_buku=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtIdBuku.getText().trim());
            pst.setString(2, txtJudul.getText().trim());
            pst.setString(3, txtPenulis.getText().trim());
            pst.setString(4, cmbGenre.getSelectedItem().toString());
            pst.setInt(5, Integer.parseInt(txtStok.getText().trim()));
            pst.setString(6, id);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            resetForm();
            tampilData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        if (modeAnggota) {
            new MenuAnggota(namaAnggota).setVisible(true);
        } else {
            new MenuUtama().setVisible(true);
        }

        this.dispose();
    }

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {
        cariData();
    }

    /**
     * @param args the command line arguments
     */
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

        java.awt.EventQueue.invokeLater(() -> new BukuView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbGenre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBuku;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIdBuku;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtPenulis;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
