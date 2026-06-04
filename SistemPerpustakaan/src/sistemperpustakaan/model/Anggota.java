package sistemperpustakaan.model;

public class Anggota extends User {

    private int nomorAnggota;
    private String alamat;
    private String telepon;
    private String waktuPinjam;

    // Constructor
    public Anggota(int nomorAnggota, String alamat, String telepon,
                   String waktuPinjam,
                   String id, String nama, String email, String password) {

        super(id, nama, email, password);

        this.nomorAnggota = nomorAnggota;
        this.alamat = alamat;
        this.telepon = telepon;
        this.waktuPinjam = waktuPinjam;
    }

    // Getter Setter
    public int getNomorAnggota() {
        return nomorAnggota;
    }

    public void setNomorAnggota(int nomorAnggota) {
        this.nomorAnggota = nomorAnggota;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getWaktuPinjam() {
        return waktuPinjam;
    }

    public void setWaktuPinjam(String waktuPinjam) {
        this.waktuPinjam = waktuPinjam;
    }

    // Method
    public void pinjamBuku() {
        System.out.println(nama + " meminjam buku");
    }

    public void lihatRiwayat() {
        System.out.println("Menampilkan riwayat peminjaman");
    }

    public void beriUlasan() {
        System.out.println("Ulasan berhasil diberikan");
    }
}