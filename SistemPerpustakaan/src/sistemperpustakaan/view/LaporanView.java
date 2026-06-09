package sistemperpustakaan.view;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LaporanView extends javax.swing.JFrame {

    public LaporanView() {
        initComponents();
        tampilLaporan();
        setLocationRelativeTo(null);
        setTitle("Laporan Perpustakaan");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTotalBuku = new javax.swing.JLabel();
        lblTotalAnggota = new javax.swing.JLabel();
        lblTotalPeminjaman = new javax.swing.JLabel();
        lblDipinjam = new javax.swing.JLabel();
        lblDikembalikan = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLaporan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTotalBuku.setText("Total Buku: 0");

        lblTotalAnggota.setText("Total Anggota: 0");

        lblTotalPeminjaman.setText("Total Peminjaman: 0");

        lblDipinjam.setText("Sedang Dipinjam: 0");

        lblDikembalikan.setText("Dikembalikan: 0");

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        jScrollPane1.setViewportView(tblLaporan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDipinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDikembalikan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(140, 140, 140)
                        .addComponent(btnKembali))
                    .addComponent(lblTotalPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotalBuku)
                        .addGap(6, 6, 6)
                        .addComponent(lblTotalAnggota))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDipinjam)
                        .addGap(6, 6, 6)
                        .addComponent(lblDikembalikan))
                    .addComponent(btnKembali))
                .addGap(6, 6, 6)
                .addComponent(lblTotalPeminjaman)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tampilLaporan() {
        lblTotalBuku.setText("Total Buku: " + hitungData("SELECT COUNT(*) FROM buku"));
        lblTotalAnggota.setText("Total Anggota: " + hitungData("SELECT COUNT(*) FROM anggota"));
        lblTotalPeminjaman.setText("Total Peminjaman: " + hitungData("SELECT COUNT(*) FROM peminjaman"));
        lblDipinjam.setText("Sedang Dipinjam: " + hitungData("SELECT COUNT(*) FROM peminjaman WHERE status IN ('Dipinjam', 'Terlambat')"));
        lblDikembalikan.setText("Dikembalikan: " + hitungData("SELECT COUNT(*) FROM peminjaman WHERE status='Dikembalikan'"));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Anggota");
        model.addColumn("Buku");
        model.addColumn("Tanggal Pinjam");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Status");

        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT * FROM peminjaman ORDER BY id_pinjam DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("id_pinjam"),
                    rs.getString("nama_anggota"),
                    rs.getString("judul_buku"),
                    rs.getString("tanggal_pinjam"),
                    rs.getString("tanggal_kembali"),
                    rs.getString("status")
                });
            }

            tblLaporan.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private int hitungData(String sql) {
        try {
            Connection conn = Koneksi.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        return 0;
    }

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        new MenuUtama().setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDikembalikan;
    private javax.swing.JLabel lblDipinjam;
    private javax.swing.JLabel lblTotalAnggota;
    private javax.swing.JLabel lblTotalBuku;
    private javax.swing.JLabel lblTotalPeminjaman;
    private javax.swing.JTable tblLaporan;
    // End of variables declaration//GEN-END:variables
}
