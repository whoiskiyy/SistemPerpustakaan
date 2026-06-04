package sistemperpustakaan.model;

public class DataPeminjaman implements Peminjaman {

    private String idPinjam;
    private String tanggalPinjam;
    private String tanggalKembali;
    private boolean status;

    // Constructor
    public DataPeminjaman(String idPinjam,
                           String tanggalPinjam,
                           String tanggalKembali,
                           boolean status) {

        this.idPinjam = idPinjam;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.status = status;
    }

    // Getter Setter
    public String getIdPinjam() {
        return idPinjam;
    }

    public void setIdPinjam(String idPinjam) {
        this.idPinjam = idPinjam;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Implement Method Interface
    @Override
    public void pinjamBuku() {

        System.out.println("Buku berhasil dipinjam");

    }

    @Override
    public void kembalikanBuku() {

        System.out.println("Buku berhasil dikembalikan");

    }

    @Override
    public void cekStatus() {

        if (status) {
            System.out.println("Buku sedang dipinjam");
        } else {
            System.out.println("Buku tersedia");
        }

    }
}