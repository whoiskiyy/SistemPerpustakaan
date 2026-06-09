package sistemperpustakaan.view;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PetugasShiftView extends javax.swing.JFrame {

    private final String namaPustakawan;
    private String nipDipilih;
    private String namaDipilih;
    private String shiftDipilih;

    public PetugasShiftView(String namaPustakawan) {
        this.namaPustakawan = namaPustakawan;
        initComponents();
        pastikanTabelShiftAktif();
        tampilData();
        setLocationRelativeTo(null);
        setTitle("Petugas Shift");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblJudul = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        cmbShift = new javax.swing.JComboBox<>();
        btnTampilkan = new javax.swing.JButton();
        btnJadikanAktif = new javax.swing.JButton();
        btnUbahShift = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPetugas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("PETUGAS SHIFT KERJA");

        lblInfo.setText("Login: " + (namaPustakawan == null || namaPustakawan.isEmpty() ? "-" : namaPustakawan));

        cmbShift.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Pagi", "Siang", "Malam" }));

        btnTampilkan.setText("TAMPILKAN");
        btnTampilkan.addActionListener(this::btnTampilkanActionPerformed);

        btnJadikanAktif.setText("JADIKAN SEDANG BEKERJA");
        btnJadikanAktif.addActionListener(this::btnJadikanAktifActionPerformed);

        btnUbahShift.setText("UBAH SHIFT");
        btnUbahShift.addActionListener(this::btnUbahShiftActionPerformed);

        btnKembali.setText("KEMBALI");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        tblPetugas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"NIP", "Nama", "Shift Kerja"}
        ));
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbShift, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTampilkan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUbahShift)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnJadikanAktif)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(btnKembali)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblJudul)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfo)
                    .addComponent(cmbShift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTampilkan)
                    .addComponent(btnUbahShift)
                    .addComponent(btnJadikanAktif)
                    .addComponent(btnKembali))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tampilData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("Shift Kerja");

        try {
            Connection conn = Koneksi.getConnection();
            String shift = cmbShift.getSelectedItem().toString();
            ResultSet rs = ambilDataPetugas(conn, shift);
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getString("nip"),
                    rs.getString("nama"),
                    rs.getString("shift_kerja")
                });
            }

            tblPetugas.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Data shift belum tersedia. Pastikan tabel pustakawan punya kolom nip, nama, dan shift_kerja.");
            tblPetugas.setModel(model);
        }
    }

    private ResultSet ambilDataPetugas(Connection conn, String shift) throws Exception {
        String[][] sqlList = {
            {"SELECT nip, nama, shift_kerja FROM pustakawan", "shift_kerja"},
            {"SELECT nip, nama, shiftKerja AS shift_kerja FROM pustakawan", "shiftKerja"},
            {"SELECT nip, username AS nama, shift_kerja FROM user", "shift_kerja"}
        };

        for (String[] query : sqlList) {
            try {
                String sql = query[0];
                if (!"Semua".equals(shift)) {
                    sql += " WHERE " + query[1] + "=?";
                }

                PreparedStatement pst = conn.prepareStatement(sql);
                if (!"Semua".equals(shift)) {
                    pst.setString(1, shift);
                }

                return pst.executeQuery();
            } catch (Exception e) {
                // Coba nama tabel/kolom berikutnya.
            }
        }

        throw new Exception("Data petugas shift belum tersedia");
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
            Connection conn = Koneksi.getConnection();
            String sql = "REPLACE INTO shift_aktif(id, nip, nama, shift_kerja) VALUES (1, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nipDipilih);
            pst.setString(2, namaDipilih);
            pst.setString(3, shiftAktif);
            pst.executeUpdate();

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
            Connection conn = Koneksi.getConnection();
            int jumlahData = updateShiftPetugas(conn, shiftBaru);

            if (jumlahData == 0) {
                JOptionPane.showMessageDialog(this,
                        "Shift belum berubah. Pastikan data NIP/Nama petugas ada di tabel pustakawan.");
                return;
            }

            shiftDipilih = shiftBaru;
            updateShiftAktifJikaPetugasSedangBekerja(conn, shiftBaru);
            JOptionPane.showMessageDialog(this, "Shift " + namaDipilih + " berhasil diubah");
            tampilData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private int updateShiftPetugas(Connection conn, String shiftBaru) {
        String[] sqlList = {
            "UPDATE pustakawan SET shift_kerja=? WHERE nip=? OR nama=?",
            "UPDATE pustakawan SET shiftKerja=? WHERE nip=? OR nama=?",
            "UPDATE user SET shift_kerja=? WHERE nip=? OR username=?"
        };

        for (String sql : sqlList) {
            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, shiftBaru);
                pst.setString(2, nipDipilih);
                pst.setString(3, namaDipilih);

                int jumlahData = pst.executeUpdate();
                if (jumlahData > 0) {
                    return jumlahData;
                }
            } catch (Exception e) {
                // Coba struktur tabel/kolom berikutnya.
            }
        }

        return 0;
    }

    private void updateShiftAktifJikaPetugasSedangBekerja(Connection conn, String shiftBaru) {
        try {
            String sql = "UPDATE shift_aktif SET shift_kerja=? WHERE nip=? OR nama=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, shiftBaru);
            pst.setString(2, nipDipilih);
            pst.setString(3, namaDipilih);
            pst.executeUpdate();
        } catch (Exception e) {
            // Abaikan jika belum ada petugas aktif.
        }
    }

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        new MenuUtama(namaPustakawan).setVisible(true);
        this.dispose();
    }

    private void pastikanTabelShiftAktif() {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "CREATE TABLE IF NOT EXISTS shift_aktif ("
                    + "id INT PRIMARY KEY,"
                    + "nip VARCHAR(50),"
                    + "nama VARCHAR(100),"
                    + "shift_kerja VARCHAR(50)"
                    + ")";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.executeUpdate();
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
