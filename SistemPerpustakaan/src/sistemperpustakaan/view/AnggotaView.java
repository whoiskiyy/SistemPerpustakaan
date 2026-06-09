/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemperpustakaan.view;

import javax.swing.JOptionPane;
import sistemperpustakaan.controller.AnggotaController;

/**
 *
 * @author rizky
 */
public class AnggotaView extends javax.swing.JFrame {
    String id;
    private final AnggotaController controller = new AnggotaController();

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AnggotaView.class.getName());

    /**
     * Creates new form AnggotaView
     */
    public AnggotaView() {
        initComponents();
        terapkanWarna();
        tampilData();
        setLocationRelativeTo(null);
        setTitle("Data Anggota");
    }

    public void tampilData() {
        try {
            tblAnggota.setModel(controller.tampilData());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnKembali = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAnggota = new javax.swing.JTable();

        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);
        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 32));

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, 32));

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(this::btnEditActionPerformed);
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, 32));

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(this::btnHapusActionPerformed);
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 150, 32));

        btnReset.setText("RESET");
        btnReset.addActionListener(this::btnResetActionPerformed);
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 150, 32));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nama");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 24, 80, -1));
        getContentPane().add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 250, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Alamat");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 62, 80, -1));
        getContentPane().add(txtAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 58, 250, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("No Hp");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 80, -1));
        getContentPane().add(txtNoHp, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 96, 250, -1));

        tblAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAnggotaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAnggota);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 560, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        int pilih = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (pilih == JOptionPane.YES_OPTION) {
            try {
                controller.hapus(id);
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                tampilData();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtNama.getText().isEmpty()
                || txtAlamat.getText().isEmpty()
                || txtNoHp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong");
            return;
        }

        try {
            controller.simpan(txtNama.getText(), txtAlamat.getText(), txtNoHp.getText());
            JOptionPane.showMessageDialog(this, "Data Anggota Berhasil Disimpan");
            tampilData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void tblAnggotaMouseClicked(java.awt.event.MouseEvent evt) {
        int baris = tblAnggota.getSelectedRow();

        id = tblAnggota.getValueAt(baris, 0).toString();
        txtNama.setText(tblAnggota.getValueAt(baris, 1).toString());
        txtAlamat.setText(tblAnggota.getValueAt(baris, 2).toString());
        txtNoHp.setText(tblAnggota.getValueAt(baris, 3).toString());
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            controller.edit(id, txtNama.getText(), txtAlamat.getText(), txtNoHp.getText());
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            tampilData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");
    }

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        new MenuUtama().setVisible(true);
        this.dispose();
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
        for (javax.swing.JLabel label : new javax.swing.JLabel[]{jLabel1, jLabel2, jLabel3}) {
            label.setForeground(text);
        }
        for (javax.swing.JTextField field : new javax.swing.JTextField[]{txtNama, txtAlamat, txtNoHp}) {
            field.setBackground(panel);
            field.setForeground(text);
            field.setCaretColor(text);
            field.setBorder(javax.swing.BorderFactory.createLineBorder(border));
        }
        tblAnggota.setBackground(panel);
        tblAnggota.setForeground(text);
        tblAnggota.setGridColor(border);
        tblAnggota.setSelectionBackground(new java.awt.Color(82, 107, 150));
        tblAnggota.setSelectionForeground(text);
        tblAnggota.getTableHeader().setBackground(button);
        tblAnggota.getTableHeader().setForeground(text);
        jScrollPane2.getViewport().setBackground(panel);
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(border));
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

        java.awt.EventQueue.invokeLater(() -> new AnggotaView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblAnggota;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHp;
    // End of variables declaration//GEN-END:variables
}
