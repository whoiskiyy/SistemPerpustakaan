    package sistemperpustakaan.view;

public class MenuAnggota extends javax.swing.JFrame {

    private final String namaAnggota;

    public MenuAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Menu Anggota");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        btnFiturAnggota = new javax.swing.JButton();
        btnBuku = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SELAMAT DATANG, " + namaAnggota);

        btnFiturAnggota.setText("PINJAM BUKU");
        btnFiturAnggota.addActionListener(this::btnFiturAnggotaActionPerformed);

        btnBuku.setText("LIHAT BUKU");
        btnBuku.addActionListener(this::btnBukuActionPerformed);

        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(btnFiturAnggota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(btnFiturAnggota)
                .addGap(18, 18, 18)
                .addComponent(btnBuku)
                .addGap(18, 18, 18)
                .addComponent(btnLogout)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBukuActionPerformed(java.awt.event.ActionEvent evt) {
        new BukuView(true, namaAnggota).setVisible(true);
        this.dispose();
    }

    private void btnFiturAnggotaActionPerformed(java.awt.event.ActionEvent evt) {
        new AnggotaFiturView(namaAnggota).setVisible(true);
        this.dispose();
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        new LoginView().setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuku;
    private javax.swing.JButton btnFiturAnggota;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
