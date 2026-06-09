/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemperpustakaan.view;
import java.awt.Color;
import java.awt.Component;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import sistemperpustakaan.controller.PeminjamanController;
import sistemperpustakaan.view.MenuUtama;
/**
 *
 * @author rizky
 */
public class PeminjamanView extends javax.swing.JFrame {
    String id;
    private final PeminjamanController controller = new PeminjamanController();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PeminjamanView.class.getName());

    /**
     * Creates new form PeminjamanView
     */
public PeminjamanView() {
    initComponents();
    terapkanWarna();
    warnaStatusTerlambat();
    tampilData();
    loadAnggota();
    loadBuku();
    setLocationRelativeTo(null);
    setTitle("Data Peminjaman");
}

private void warnaStatusTerlambat() {
    tblPeminjaman.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            Component component = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            if (table.getColumnCount() <= 5) {
                return component;
            }

            Object statusValue = table.getValueAt(row, 5);
            String status = statusValue == null ? "" : statusValue.toString();
            if ("Terlambat".equalsIgnoreCase(status)) {
                component.setBackground(new Color(95, 63, 67));
                component.setForeground(new Color(255, 205, 210));
            } else if (isSelected) {
                component.setBackground(table.getSelectionBackground());
                component.setForeground(table.getSelectionForeground());
            } else {
                component.setBackground(table.getBackground());
                component.setForeground(table.getForeground());
            }

            return component;
        }
    });
}

    public void tampilData() {
    try {
        tblPeminjaman.setModel(controller.tampilData());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                e.getMessage());
    }
}

    private void loadAnggota() {
    try {
        cmbNamaAnggota.setModel(controller.loadAnggota());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}

private void loadBuku() {
    try {
        cmbJudulBuku.setModel(controller.loadBuku());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}

private boolean inputValid() {
    if (cmbNamaAnggota.getSelectedIndex() <= 0
            || cmbJudulBuku.getSelectedIndex() <= 0
            || txtTanggalPinjam.getText().trim().isEmpty()
            || txtTanggalKembali.getText().trim().isEmpty()
            || cmbStatus.getSelectedItem() == null) {

        JOptionPane.showMessageDialog(this, "Data Tidak Boleh Kosong");
        return false;
    }

    try {
        controller.validasiTanggal(txtTanggalPinjam.getText(), txtTanggalKembali.getText());
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
        return false;
    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "Format tanggal harus yyyy-MM-dd, contoh: 2026-06-08");
        return false;
    }

    return true;
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
    for (javax.swing.JLabel label : new javax.swing.JLabel[]{jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7}) {
        label.setForeground(text);
    }
    for (javax.swing.JTextField field : new javax.swing.JTextField[]{txtTanggalPinjam, txtTanggalKembali}) {
        field.setBackground(panel);
        field.setForeground(text);
        field.setCaretColor(text);
        field.setBorder(javax.swing.BorderFactory.createLineBorder(border));
    }
    for (javax.swing.JComboBox<?> combo : new javax.swing.JComboBox<?>[]{cmbNamaAnggota, cmbJudulBuku, cmbStatus}) {
        combo.setBackground(panel);
        combo.setForeground(text);
    }
    tblPeminjaman.setBackground(panel);
    tblPeminjaman.setForeground(text);
    tblPeminjaman.setGridColor(border);
    tblPeminjaman.setSelectionBackground(new java.awt.Color(82, 107, 150));
    tblPeminjaman.setSelectionForeground(text);
    tblPeminjaman.getTableHeader().setBackground(button);
    tblPeminjaman.getTableHeader().setForeground(text);
    jScrollPane1.getViewport().setBackground(panel);
    jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(border));
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnKembali = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbNamaAnggota = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbJudulBuku = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtTanggalPinjam = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTanggalKembali = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeminjaman = new javax.swing.JTable();

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
        jLabel1.setText("Nama Anggota");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 24, 110, -1));

        cmbNamaAnggota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cmbNamaAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 210, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Judul Buku");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 24, 90, -1));

        cmbJudulBuku.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJudulBuku.addActionListener(this::cmbJudulBukuActionPerformed);
        getContentPane().add(cmbJudulBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 190, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tanggal Pinjam");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 62, 110, -1));
        getContentPane().add(txtTanggalPinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 58, 210, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tanggal Kembali");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 62, 120, -1));
        getContentPane().add(txtTanggalKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 58, 170, -1));

        jLabel6.setText("Format: yyyy-MM-dd");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 84, 250, -1));

        jLabel7.setText("Format: yyyy-MM-dd");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 84, 170, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Status");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 112, 110, -1));

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dipinjam", "Dikembalikan", "Terlambat" }));
        getContentPane().add(cmbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 108, 210, -1));

        tblPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPeminjamanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeminjaman);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 700, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
if (id != null && !id.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Data sudah dipilih. Gunakan tombol EDIT untuk mengubah data, atau RESET untuk input baru.");
    return;
}

if (!inputValid()) {
    return;
}
        try {
    String judulBuku = cmbJudulBuku.getSelectedItem().toString();
    String status = cmbStatus.getSelectedItem().toString();

    controller.simpan((String) cmbNamaAnggota.getSelectedItem(),
            judulBuku,
            txtTanggalPinjam.getText().trim(),
            txtTanggalKembali.getText().trim(),
            status);

    JOptionPane.showMessageDialog(this,
            "Data Peminjaman Berhasil Disimpan");

    resetForm();
    tampilData();
    loadBuku();

} catch (Exception e) {

    JOptionPane.showMessageDialog(this,
            e.getMessage());

}        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tblPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeminjamanMouseClicked
int baris = tblPeminjaman.getSelectedRow();
if (baris < 0) {
    return;
}

id = tblPeminjaman.getValueAt(baris, 0).toString();

cmbNamaAnggota.setSelectedItem(tblPeminjaman.getValueAt(baris, 1).toString());
cmbJudulBuku.setSelectedItem(tblPeminjaman.getValueAt(baris, 2).toString());
txtTanggalPinjam.setText(tblPeminjaman.getValueAt(baris, 3).toString());
txtTanggalKembali.setText(tblPeminjaman.getValueAt(baris, 4).toString());
cmbStatus.setSelectedItem(tblPeminjaman.getValueAt(baris, 5).toString());        // TODO add your handling code here:
    }//GEN-LAST:event_tblPeminjamanMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
if (id == null || id.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Pilih data yang akan diedit");
    return;
}

if (!inputValid()) {
    return;
}

try {
    String judulBaru = cmbJudulBuku.getSelectedItem().toString();
    String statusBaru = cmbStatus.getSelectedItem().toString();

    controller.edit(id,
            (String) cmbNamaAnggota.getSelectedItem(),
            judulBaru,
            txtTanggalPinjam.getText().trim(),
            txtTanggalKembali.getText().trim(),
            statusBaru);

    JOptionPane.showMessageDialog(this,
            "Data Berhasil Diubah");

    resetForm();
    tampilData();
    loadBuku();

} catch (Exception e) {

    JOptionPane.showMessageDialog(this,
            e.getMessage());

}        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
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

    JOptionPane.showMessageDialog(this,
            "Data Berhasil Dihapus");

    resetForm();
    tampilData();
    loadBuku();

} catch (Exception e) {

    JOptionPane.showMessageDialog(this,
            e.getMessage());

}        // TODO add your handling code here:
    }//GEN-LAST:event_btnHapusActionPerformed
    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
resetForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void resetForm() {
cmbNamaAnggota.setSelectedIndex(0);
cmbJudulBuku.setSelectedIndex(0);
txtTanggalPinjam.setText("");
txtTanggalKembali.setText("");
cmbStatus.setSelectedIndex(0);
id = null;
tblPeminjaman.clearSelection();
    }

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
new MenuUtama().setVisible(true);

this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void cmbJudulBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJudulBukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJudulBukuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new PeminjamanView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbJudulBuku;
    private javax.swing.JComboBox<String> cmbNamaAnggota;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPeminjaman;
    private javax.swing.JTextField txtTanggalKembali;
    private javax.swing.JTextField txtTanggalPinjam;
    // End of variables declaration//GEN-END:variables
}
