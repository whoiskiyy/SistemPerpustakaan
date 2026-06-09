package sistemperpustakaan.view;

import config.Koneksi;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KelolaStokView extends javax.swing.JFrame {

    private String idBuku;

    public KelolaStokView() {
        initComponents();
        pastikanKolomBuku();
        tampilData();
        setLocationRelativeTo(null);
        setTitle("Kelola Stok Buku");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBuku = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Stok Baru");

        btnSimpan.setText("SIMPAN STOK");
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        tblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBuku);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnSimpan)
                .addGap(117, 117, 117)
                .addComponent(btnKembali))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan)
                    .addComponent(btnKembali))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tampilData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Buku");
        model.addColumn("Judul");
        model.addColumn("Penulis");
        model.addColumn("Genre");
        model.addColumn("Stok");

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
                    rs.getInt("stok")
                });
            }

            tblBuku.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
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

    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {
        int baris = tblBuku.getSelectedRow();
        idBuku = tblBuku.getValueAt(baris, 0).toString();
        txtStok.setText(tblBuku.getValueAt(baris, 4).toString());
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        if (idBuku == null || idBuku.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih buku terlebih dahulu");
            return;
        }

        try {
            int stok = Integer.parseInt(txtStok.getText().trim());
            if (stok < 0) {
                JOptionPane.showMessageDialog(this, "Stok tidak boleh kurang dari 0");
                return;
            }

            Connection conn = Koneksi.getConnection();
            String sql = "UPDATE buku SET stok=? WHERE id_buku=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, stok);
            pst.setString(2, idBuku);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Stok buku berhasil dikelola");
            tampilData();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok harus berupa angka");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        new MenuUtama().setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBuku;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
