/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemperpustakaan.view;
import config.Koneksi;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import sistemperpustakaan.view.MenuUtama;
/**
 *
 * @author rizky
 */
public class PeminjamanView extends javax.swing.JFrame {
    String id;
    private static final int DENDA_PER_HARI = 1000;
    private static final DateTimeFormatter FORMAT_TANGGAL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PeminjamanView.class.getName());

    /**
     * Creates new form PeminjamanView
     */
    public PeminjamanView() {
    initComponents();
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
                component.setBackground(new Color(255, 204, 204));
                component.setForeground(Color.RED.darker());
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

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID");
    model.addColumn("Nama Anggota");
    model.addColumn("Judul Buku");
    model.addColumn("Tanggal Pinjam");
    model.addColumn("Tanggal Kembali");
    model.addColumn("Status");
    model.addColumn("Denda");

    try {

        Connection conn = Koneksi.getConnection();

        String sql = "SELECT * FROM peminjaman";

        PreparedStatement pst = conn.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[] {
                rs.getString("id_pinjam"),
                rs.getString("nama_anggota"),
                rs.getString("judul_buku"),
                rs.getString("tanggal_pinjam"),
                rs.getString("tanggal_kembali"),
                rs.getString("status"),
                hitungDenda(rs.getString("tanggal_kembali"), rs.getString("status"))
            });

        }

        tblPeminjaman.setModel(model);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                e.getMessage());

    }

}

    private void loadAnggota() {
    try {
        Connection conn = Koneksi.getConnection();
        String sql = "SELECT nama FROM anggota";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        cmbNamaAnggota.removeAllItems();
        cmbNamaAnggota.addItem("Pilih Anggota");

        while (rs.next()) {
            cmbNamaAnggota.addItem(rs.getString("nama"));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}

private void loadBuku() {
    try {
        Connection conn = Koneksi.getConnection();
        String sql = "SELECT judul FROM buku";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        cmbJudulBuku.removeAllItems();
        cmbJudulBuku.addItem("Pilih Buku");

        while (rs.next()) {
            cmbJudulBuku.addItem(rs.getString("judul"));
        }
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
        LocalDate tanggalPinjam = LocalDate.parse(txtTanggalPinjam.getText().trim(), FORMAT_TANGGAL);
        LocalDate tanggalKembali = LocalDate.parse(txtTanggalKembali.getText().trim(), FORMAT_TANGGAL);

        if (tanggalKembali.isBefore(tanggalPinjam)) {
            JOptionPane.showMessageDialog(this, "Tanggal kembali tidak boleh sebelum tanggal pinjam");
            return false;
        }
    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "Format tanggal harus yyyy-MM-dd, contoh: 2026-06-08");
        return false;
    }

    return true;
}

private long hitungDenda(String tanggalKembali, String status) {
    if (!"Terlambat".equalsIgnoreCase(status) && !"Dipinjam".equalsIgnoreCase(status)) {
        return 0;
    }

    try {
        LocalDate batasKembali = LocalDate.parse(tanggalKembali, FORMAT_TANGGAL);
        long hariTerlambat = ChronoUnit.DAYS.between(batasKembali, LocalDate.now());
        return Math.max(0, hariTerlambat) * DENDA_PER_HARI;
    } catch (DateTimeParseException e) {
        return 0;
    }
}

private boolean statusDipinjam(String status) {
    return "Dipinjam".equalsIgnoreCase(status) || "Terlambat".equalsIgnoreCase(status);
}

private int getStokBuku(Connection conn, String judulBuku) throws Exception {
    String sql = "SELECT stok FROM buku WHERE judul = ?";
    PreparedStatement pst = conn.prepareStatement(sql);
    pst.setString(1, judulBuku);
    ResultSet rs = pst.executeQuery();

    if (rs.next()) {
        return rs.getInt("stok");
    }

    throw new Exception("Buku tidak ditemukan");
}

private void ubahStokBuku(Connection conn, String judulBuku, int perubahan) throws Exception {
    if (perubahan < 0 && getStokBuku(conn, judulBuku) <= 0) {
        throw new Exception("Stok buku habis");
    }

    String sql = "UPDATE buku SET stok = stok + ? WHERE judul = ?";
    PreparedStatement pst = conn.prepareStatement(sql);
    pst.setInt(1, perubahan);
    pst.setString(2, judulBuku);
    pst.executeUpdate();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTanggalPinjam = new javax.swing.JTextField();
        txtTanggalKembali = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeminjaman = new javax.swing.JTable();
        cmbNamaAnggota = new javax.swing.JComboBox<>();
        cmbJudulBuku = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nama Anggota");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Judul Buku");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tanggal Pinjam");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tanggal Kembali");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Status");

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(this::btnHapusActionPerformed);

        btnReset.setText("RESET");
        btnReset.addActionListener(this::btnResetActionPerformed);

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        tblPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPeminjamanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeminjaman);

        cmbNamaAnggota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbJudulBuku.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJudulBuku.addActionListener(this::cmbJudulBukuActionPerformed);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dipinjam", "Dikembalikan", "Terlambat" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTanggalKembali, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbNamaAnggota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbJudulBuku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnKembali)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSimpan)
                                .addGap(26, 26, 26)
                                .addComponent(btnEdit)
                                .addGap(29, 29, 29)
                                .addComponent(btnHapus)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(btnKembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbNamaAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus)
                    .addComponent(btnReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
if (!inputValid()) {
    return;
}
        try {

    Connection conn = Koneksi.getConnection();
    String judulBuku = cmbJudulBuku.getSelectedItem().toString();
    String status = cmbStatus.getSelectedItem().toString();

    conn.setAutoCommit(false);

    if (statusDipinjam(status)) {
        ubahStokBuku(conn, judulBuku, -1);
    }

    String sql = "INSERT INTO peminjaman(nama_anggota, judul_buku, tanggal_pinjam, tanggal_kembali, status) VALUES (?, ?, ?, ?, ?)";

    PreparedStatement pst = conn.prepareStatement(sql);

    pst.setString(1, (String) cmbNamaAnggota.getSelectedItem());
    pst.setString(2, judulBuku);
    pst.setString(3, txtTanggalPinjam.getText().trim());
    pst.setString(4, txtTanggalKembali.getText().trim());
    pst.setString(5, status);

    pst.executeUpdate();
    conn.commit();

    JOptionPane.showMessageDialog(this,
            "Data Peminjaman Berhasil Disimpan");

    tampilData();
    loadBuku();

} catch (Exception e) {

    try {
        Koneksi.getConnection().rollback();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }

    JOptionPane.showMessageDialog(this,
            e.getMessage());

} finally {
    try {
        Koneksi.getConnection().setAutoCommit(true);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tblPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeminjamanMouseClicked
int baris = tblPeminjaman.getSelectedRow();

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

    Connection conn = Koneksi.getConnection();
    conn.setAutoCommit(false);

    String judulLama = "";
    String statusLama = "";
    String sqlLama = "SELECT judul_buku, status FROM peminjaman WHERE id_pinjam=?";
    PreparedStatement pstLama = conn.prepareStatement(sqlLama);
    pstLama.setString(1, id);
    ResultSet rsLama = pstLama.executeQuery();

    if (rsLama.next()) {
        judulLama = rsLama.getString("judul_buku");
        statusLama = rsLama.getString("status");
    }

    String judulBaru = cmbJudulBuku.getSelectedItem().toString();
    String statusBaru = cmbStatus.getSelectedItem().toString();

    if (statusDipinjam(statusLama)) {
        ubahStokBuku(conn, judulLama, 1);
    }

    if (statusDipinjam(statusBaru)) {
        ubahStokBuku(conn, judulBaru, -1);
    }

    String sql = "UPDATE peminjaman SET nama_anggota=?, judul_buku=?, tanggal_pinjam=?, tanggal_kembali=?, status=? WHERE id_pinjam=?";

    PreparedStatement pst = conn.prepareStatement(sql);

    pst.setString(1, (String) cmbNamaAnggota.getSelectedItem());
    pst.setString(2, judulBaru);
    pst.setString(3, txtTanggalPinjam.getText().trim());
    pst.setString(4, txtTanggalKembali.getText().trim());
    pst.setString(5, statusBaru);
    pst.setString(6, id);

    pst.executeUpdate();
    conn.commit();

    JOptionPane.showMessageDialog(this,
            "Data Berhasil Diubah");

    tampilData();
    loadBuku();

} catch (Exception e) {

    try {
        Koneksi.getConnection().rollback();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }

    JOptionPane.showMessageDialog(this,
            e.getMessage());

} finally {
    try {
        Koneksi.getConnection().setAutoCommit(true);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
int pilih = JOptionPane.showConfirmDialog(this,
        "Yakin ingin menghapus?",
        "Konfirmasi",
        JOptionPane.YES_NO_OPTION);

if (pilih == JOptionPane.YES_OPTION) {
        try {

    Connection conn = Koneksi.getConnection();
    conn.setAutoCommit(false);

    String judulLama = "";
    String statusLama = "";
    String sqlLama = "SELECT judul_buku, status FROM peminjaman WHERE id_pinjam=?";
    PreparedStatement pstLama = conn.prepareStatement(sqlLama);
    pstLama.setString(1, id);
    ResultSet rsLama = pstLama.executeQuery();

    if (rsLama.next()) {
        judulLama = rsLama.getString("judul_buku");
        statusLama = rsLama.getString("status");
    }

    String sql = "DELETE FROM peminjaman WHERE id_pinjam=?";

    PreparedStatement pst = conn.prepareStatement(sql);

    pst.setString(1, id);

    pst.executeUpdate();

    if (statusDipinjam(statusLama)) {
        ubahStokBuku(conn, judulLama, 1);
    }

    conn.commit();

    JOptionPane.showMessageDialog(this,
            "Data Berhasil Dihapus");

    tampilData();
    loadBuku();

} catch (Exception e) {

    try {
        Koneksi.getConnection().rollback();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }

    JOptionPane.showMessageDialog(this,
            e.getMessage());

} finally {
    try {
        Koneksi.getConnection().setAutoCommit(true);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}        // TODO add your handling code here:
    }//GEN-LAST:event_btnHapusActionPerformed
    }
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
cmbNamaAnggota.setSelectedIndex(0);
cmbJudulBuku.setSelectedIndex(0);
txtTanggalPinjam.setText("");
txtTanggalKembali.setText("");
cmbStatus.setSelectedIndex(0);        
    }//GEN-LAST:event_btnResetActionPerformed

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPeminjaman;
    private javax.swing.JTextField txtTanggalKembali;
    private javax.swing.JTextField txtTanggalPinjam;
    // End of variables declaration//GEN-END:variables
}
