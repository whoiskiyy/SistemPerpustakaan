package sistemperpustakaan.view;

import javax.swing.JOptionPane;
import sistemperpustakaan.controller.BukuController;

public class KelolaStokView extends javax.swing.JFrame {

    private String idBuku;
    private final BukuController controller = new BukuController();

    public KelolaStokView() {
        initComponents();
        terapkanWarna();
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 32));
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, 32));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 24, 90, -1));
        getContentPane().add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 160, -1));
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 620, 330));

        pack();
        setSize(new java.awt.Dimension(850, 480));
    }// </editor-fold>//GEN-END:initComponents

    private void tampilData() {
        try {
            tblBuku.setModel(controller.tampilData());
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

            controller.updateStok(idBuku, stok);
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

    private void terapkanWarna() {
        java.awt.Color background = new java.awt.Color(55, 57, 62);
        java.awt.Color panel = new java.awt.Color(68, 71, 78);
        java.awt.Color button = new java.awt.Color(88, 92, 100);
        java.awt.Color border = new java.awt.Color(105, 109, 118);
        java.awt.Color text = new java.awt.Color(245, 245, 245);

        getContentPane().setBackground(background);
        for (javax.swing.JButton tombol : new javax.swing.JButton[]{btnKembali, btnSimpan}) {
            tombol.setBackground(button);
            tombol.setForeground(text);
            tombol.setFocusPainted(false);
        }
        jLabel1.setForeground(text);
        txtStok.setBackground(panel);
        txtStok.setForeground(text);
        txtStok.setCaretColor(text);
        txtStok.setBorder(javax.swing.BorderFactory.createLineBorder(border));
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBuku;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
