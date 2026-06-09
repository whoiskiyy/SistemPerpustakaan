package sistemperpustakaan.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemperpustakaan.controller.PetugasShiftController;

public class PetugasShiftView extends javax.swing.JFrame {

    private final String namaPustakawan;
    private String nipDipilih;
    private String namaDipilih;
    private String shiftDipilih;
    private final PetugasShiftController controller = new PetugasShiftController();

    public PetugasShiftView(String namaPustakawan) {
        this.namaPustakawan = namaPustakawan;
        initComponents();
        terapkanWarna();
        pastikanTabelShiftAktif();
        tampilData();
        setLocationRelativeTo(null);
        setTitle("Petugas Shift");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnKembali = new javax.swing.JButton();
        btnTampilkan = new javax.swing.JButton();
        btnUbahShift = new javax.swing.JButton();
        btnJadikanAktif = new javax.swing.JButton();
        lblJudul = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        cmbShift = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPetugas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        btnTampilkan.setText("TAMPILKAN");
        btnTampilkan.addActionListener(this::btnTampilkanActionPerformed);

        btnUbahShift.setText("UBAH SHIFT");
        btnUbahShift.addActionListener(this::btnUbahShiftActionPerformed);

        btnJadikanAktif.setText("JADIKAN SEDANG BEKERJA");
        btnJadikanAktif.addActionListener(this::btnJadikanAktifActionPerformed);

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("PETUGAS SHIFT KERJA");

        lblInfo.setText("Login: -");

        cmbShift.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Pagi", "Siang", "Malam" }));
        cmbShift.addActionListener(this::cmbShiftActionPerformed);

        tblPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPetugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPetugas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTampilkan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cmbShift, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUbahShift, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnJadikanAktif, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnTampilkan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblInfo))
                    .addComponent(cmbShift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUbahShift, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnJadikanAktif, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbShiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbShiftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbShiftActionPerformed

    private void tampilData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Shift Kerja");

        try {
            String shift = cmbShift.getSelectedItem().toString();
            tblPetugas.setModel(controller.tampilData(shift));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Data shift belum tersedia. Pastikan tabel pustakawan punya kolom nip, nama, dan shift_kerja.");
            tblPetugas.setModel(model);
        }
    }

    private void btnTampilkanActionPerformed(java.awt.event.ActionEvent evt) {
        tampilData();
    }

    private void tblPetugasMouseClicked(java.awt.event.MouseEvent evt) {
        int baris = tblPetugas.getSelectedRow();
        nipDipilih = tblPetugas.getValueAt(baris, 0).toString();
        namaDipilih = tblPetugas.getValueAt(baris, 1).toString();
        shiftDipilih = tblPetugas.getValueAt(baris, 2).toString();
        lblInfo.setText("Dipilih: " + namaDipilih);

        if (shiftDipilih != null && !shiftDipilih.isEmpty()) {
            cmbShift.setSelectedItem(shiftDipilih);
        }
    }

    private void btnJadikanAktifActionPerformed(java.awt.event.ActionEvent evt) {
        if (!petugasDipilih()) {
            return;
        }

        String shiftAktif = "Semua".equals(cmbShift.getSelectedItem().toString())
                ? shiftDipilih
                : cmbShift.getSelectedItem().toString();

        try {
            controller.jadikanAktif(nipDipilih, namaDipilih, shiftAktif);
            JOptionPane.showMessageDialog(this, namaDipilih + " sedang bekerja pada shift " + shiftAktif);
            new MenuUtama(namaPustakawan).setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnUbahShiftActionPerformed(java.awt.event.ActionEvent evt) {
        if (!petugasDipilih()) {
            return;
        }

        String shiftBaru = cmbShift.getSelectedItem().toString();
        if ("Semua".equals(shiftBaru)) {
            JOptionPane.showMessageDialog(this, "Pilih shift Pagi, Siang, atau Malam");
            return;
        }

        try {
            if (!controller.ubahShift(nipDipilih, namaDipilih, shiftBaru)) {
                JOptionPane.showMessageDialog(this,
                        "Shift belum berubah. Pastikan data NIP/Nama petugas ada di tabel pustakawan.");
                return;
            }

            shiftDipilih = shiftBaru;
            JOptionPane.showMessageDialog(this, "Shift " + namaDipilih + " berhasil diubah");
            tampilData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        new MenuUtama(namaPustakawan).setVisible(true);
        this.dispose();
    }

    private void pastikanTabelShiftAktif() {
        try {
            controller.pastikanTabelShiftAktif();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private boolean petugasDipilih() {
        if (nipDipilih == null || nipDipilih.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih petugas terlebih dahulu dari tabel");
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
        for (javax.swing.JButton tombol : new javax.swing.JButton[]{btnKembali, btnTampilkan, btnUbahShift, btnJadikanAktif}) {
            tombol.setBackground(button);
            tombol.setForeground(text);
            tombol.setFocusPainted(false);
        }
        lblJudul.setForeground(text);
        lblInfo.setForeground(text);
        cmbShift.setBackground(panel);
        cmbShift.setForeground(text);
        tblPetugas.setBackground(panel);
        tblPetugas.setForeground(text);
        tblPetugas.setGridColor(border);
        tblPetugas.setSelectionBackground(new java.awt.Color(82, 107, 150));
        tblPetugas.setSelectionForeground(text);
        tblPetugas.getTableHeader().setBackground(button);
        tblPetugas.getTableHeader().setForeground(text);
        jScrollPane1.getViewport().setBackground(panel);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(border));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJadikanAktif;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnTampilkan;
    private javax.swing.JButton btnUbahShift;
    private javax.swing.JComboBox<String> cmbShift;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JTable tblPetugas;
    // End of variables declaration//GEN-END:variables
}
