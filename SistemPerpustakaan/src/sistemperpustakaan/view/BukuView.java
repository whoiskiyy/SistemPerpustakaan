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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().add(txtIdBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 40, 154, -1));
        txtJudul.addActionListener(this::txtJudulActionPerformed);
        getContentPane().add(txtJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 68, 154, -1));
        getContentPane().add(txtPenulis, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 96, 154, -1));

        cmbGenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ilustrasi", "Fiksi", "NonFiksi" }));
        getContentPane().add(cmbGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 124, 154, -1));
        getContentPane().add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 152, 154, -1));

        btnSimpan.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 227, -1, -1));

        btnEdit.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnEdit.setText("EDIT");
        btnEdit.addActionListener(this::btnEditActionPerformed);
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 227, -1, -1));

        btnHapus.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(this::btnHapusActionPerformed);
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 227, -1, -1));

        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 12));
        btnReset.setText("RESET");
        btnReset.addActionListener(this::btnResetActionPerformed);
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 227, -1, -1));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 262, 430, 275));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel1.setText("ID Buku");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 43, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel2.setText("Judul");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 71, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel3.setText("Penulis");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 99, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel4.setText("Genre");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 127, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel5.setText("Stok");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 155, -1, -1));

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);
        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel6.setText("Cari Buku");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 180, -1, -1));

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });
        getContentPane().add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 180, 154, -1));

        pack();
    }

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
}
