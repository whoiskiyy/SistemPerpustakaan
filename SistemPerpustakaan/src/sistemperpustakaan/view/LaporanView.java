package sistemperpustakaan.view;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import sistemperpustakaan.controller.LaporanController;

public class LaporanView extends javax.swing.JFrame {
    private final LaporanController controller = new LaporanController();

    public LaporanView() {
        initComponents();
        terapkanWarna();
        warnaStatusTerlambat();
        tampilLaporan();
        setLocationRelativeTo(null);
        setTitle("Laporan Perpustakaan");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnKembali = new javax.swing.JButton();
        lblTotalBuku = new javax.swing.JLabel();
        lblTotalAnggota = new javax.swing.JLabel();
        lblTotalPeminjaman = new javax.swing.JLabel();
        lblDipinjam = new javax.swing.JLabel();
        lblDikembalikan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLaporan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        lblTotalBuku.setText("Total Buku: 0");

        lblTotalAnggota.setText("Total Anggota: 0");

        lblTotalPeminjaman.setText("Total Peminjaman: 0");

        lblDipinjam.setText("Sedang Dipinjam: 0");

        lblDikembalikan.setText("Dikembalikan: 0");

        jScrollPane1.setViewportView(tblLaporan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lblTotalBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblTotalAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblTotalPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(lblDipinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblDikembalikan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalBuku)
                            .addComponent(lblTotalAnggota)
                            .addComponent(lblTotalPeminjaman))))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDipinjam)
                    .addComponent(lblDikembalikan))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tampilLaporan() {
        try {
            lblTotalBuku.setText("Total Buku: " + controller.totalBuku());
            lblTotalAnggota.setText("Total Anggota: " + controller.totalAnggota());
            lblTotalPeminjaman.setText("Total Peminjaman: " + controller.totalPeminjaman());
            lblDipinjam.setText("Sedang Dipinjam: " + controller.totalDipinjam());
            lblDikembalikan.setText("Dikembalikan: " + controller.totalDikembalikan());
            tblLaporan.setModel(controller.tampilLaporan());
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
        btnKembali.setBackground(button);
        btnKembali.setForeground(text);
        btnKembali.setFocusPainted(false);
        for (javax.swing.JLabel label : new javax.swing.JLabel[]{lblTotalBuku, lblTotalAnggota, lblTotalPeminjaman, lblDipinjam, lblDikembalikan}) {
            label.setForeground(text);
        }
        tblLaporan.setBackground(panel);
        tblLaporan.setForeground(text);
        tblLaporan.setGridColor(border);
        tblLaporan.setSelectionBackground(new java.awt.Color(82, 107, 150));
        tblLaporan.setSelectionForeground(text);
        tblLaporan.getTableHeader().setBackground(button);
        tblLaporan.getTableHeader().setForeground(text);
        jScrollPane1.getViewport().setBackground(panel);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(border));
    }

    private void warnaStatusTerlambat() {
        tblLaporan.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
                    component.setBackground(new java.awt.Color(95, 63, 67));
                    component.setForeground(new java.awt.Color(255, 205, 210));
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
