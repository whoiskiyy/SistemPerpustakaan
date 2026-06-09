/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemperpustakaan.view;

import javax.swing.JOptionPane;
import sistemperpustakaan.controller.BukuController;

/**
 *
 * @author rizky
 */
public class BukuView extends javax.swing.JFrame {
    String id;
    private boolean modeAnggota = false;
    private String namaAnggota = "";
    private final BukuController controller = new BukuController();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BukuView.class.getName());

    /**
     * Creates new form BukuView
     */
    public BukuView() {
        initComponents();
        terapkanWarna();
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
            aturModeAnggota();
            tampilBukuTersedia();
            setTitle("Daftar Buku");
        }
    }

    public void tampilData() {
        try {
            tblBuku.setModel(controller.tampilData());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void cariData() {
        try {
            if (modeAnggota) {
                tblBuku.setModel(controller.cariBukuTersedia(txtCari.getText()));
            } else {
                tblBuku.setModel(controller.cariData(txtCari.getText()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void tampilBukuTersedia() {
        try {
            tblBuku.setModel(controller.tampilBukuTersedia());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void pastikanKolomBuku() {
        try {
            controller.pastikanKolomBuku();
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

    private void aturModeAnggota() {
        btnSimpan.setVisible(false);
        btnEdit.setVisible(false);
        btnHapus.setVisible(false);
        btnReset.setVisible(false);

        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        txtIdBuku.setVisible(false);
        txtJudul.setVisible(false);
        txtPenulis.setVisible(false);
        cmbGenre.setVisible(false);
        txtStok.setVisible(false);

        getContentPane().remove(jLabel6);
        getContentPane().remove(txtCari);
        getContentPane().remove(jScrollPane1);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 34, 90, -1));
        getContentPane().add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 420, -1));
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 630, 380));
        revalidate();
        repaint();
    }

    private void terapkanWarna() {
        java.awt.Color background = new java.awt.Color(55, 57, 62);
        java.awt.Color panel = new java.awt.Color(68, 71, 78);
        java.awt.Color button = new java.awt.Color(88, 92, 100);
        java.awt.Color border = new java.awt.Color(105, 109, 118);
        java.awt.Color text = new java.awt.Color(245, 245, 245);

        getContentPane().setBackground(background);
        for (javax.swing.JButton tombol : new javax.swing.JButton[]{btnKembali, btnSimpan, btnEdit, btnHapus, btnReset}) {
            tombol.setBackground(button);
            tombol.setForeground(text);
            tombol.setFocusPainted(false);
        }
        for (javax.swing.JLabel label : new javax.swing.JLabel[]{jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6}) {
            label.setForeground(text);
        }
        for (javax.swing.JTextField field : new javax.swing.JTextField[]{txtIdBuku, txtJudul, txtPenulis, txtStok, txtCari}) {
            field.setBackground(panel);
            field.setForeground(text);
            field.setCaretColor(text);
            field.setBorder(javax.swing.BorderFactory.createLineBorder(border));
        }
        cmbGenre.setBackground(panel);
        cmbGenre.setForeground(text);
        tblBuku.setBackground(panel);
        tblBuku.setForeground(text);
        tblBuku.setGridColor(border);
        tblBuku.setSelectionBackground(new java.awt.Color(82, 107, 150));
        tblBuku.setSelectionForeground(text);
        tblBuku.getTableHeader().setBackground(button);
        tblBuku.getTableHeader().setForeground(text);
        jScrollPane1.getViewport().setBackground(panel);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(border));
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 32));
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, 32));
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, 32));
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 150, 32));
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 150, 32));

        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 24, 80, -1));
        getContentPane().add(txtIdBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 180, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 24, 70, -1));
        getContentPane().add(txtJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 220, -1));

        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 62, 80, -1));
        getContentPane().add(txtPenulis, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 58, 180, -1));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 62, 70, -1));
        getContentPane().add(cmbGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 58, 220, -1));

        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 80, -1));
        getContentPane().add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 96, 180, -1));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 70, -1));
        getContentPane().add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 96, 220, -1));

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 630, 320));

        pack();
        setSize(new java.awt.Dimension(860, 540));
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
                controller.hapus(id);
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
            controller.simpan(txtIdBuku.getText().trim(),
                    txtJudul.getText().trim(),
                    txtPenulis.getText().trim(),
                    cmbGenre.getSelectedItem().toString(),
                    Integer.parseInt(txtStok.getText().trim()));
            JOptionPane.showMessageDialog(this, "Data Buku Berhasil Disimpan");
            resetForm();
            tampilData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {
        if (modeAnggota) {
            return;
        }

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
            controller.edit(id,
                    txtIdBuku.getText().trim(),
                    txtJudul.getText().trim(),
                    txtPenulis.getText().trim(),
                    cmbGenre.getSelectedItem().toString(),
                    Integer.parseInt(txtStok.getText().trim()));
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
