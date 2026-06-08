package sistemperpustakaan.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Anggota extends User {

    private int nomorAnggota;
    private String alamat;
    private String telepon;
    private LocalTime waktuPinjam;
    private final List<Peminjaman> riwayatPeminjaman = new ArrayList<>();
    private final List<String> daftarUlasan = new ArrayList<>();

    // Constructor
    public Anggota(int nomorAnggota, String alamat, String telepon,
                   LocalTime waktuPinjam,
                   String id, String nama, String email, String password) {

        super(id, nama, email, password);

        this.nomorAnggota = nomorAnggota;
        this.alamat = alamat;
        this.telepon = telepon;
        this.waktuPinjam = waktuPinjam;
    }

    public Anggota(int nomorAnggota, String alamat, String telepon,
                   String waktuPinjam,
                   String id, String nama, String email, String password) {

        this(nomorAnggota, alamat, telepon, LocalTime.parse(waktuPinjam),
                id, nama, email, password);
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

    public LocalTime getWaktuPinjam() {
        return waktuPinjam;
    }

    public void setWaktuPinjam(LocalTime waktuPinjam) {
        this.waktuPinjam = waktuPinjam;
    }

    // Method
    public void pinjamBuku() {
        waktuPinjam = LocalTime.now();
        System.out.println(nama + " meminjam buku");
    }

    public void pinjamBuku(Peminjaman peminjaman) {
        pinjamBuku();
        riwayatPeminjaman.add(peminjaman);
    }

    public List<Peminjaman> lihatRiwayat() {
        return Collections.unmodifiableList(riwayatPeminjaman);
    }

    public void beriUlasan(String ulasan) {
        daftarUlasan.add(ulasan);
        System.out.println("Ulasan berhasil diberikan");
    }

    public void beriUlasan() {
        beriUlasan("Ulasan dari " + nama);
    }

    public List<String> getDaftarUlasan() {
        return Collections.unmodifiableList(daftarUlasan);
    }
}
