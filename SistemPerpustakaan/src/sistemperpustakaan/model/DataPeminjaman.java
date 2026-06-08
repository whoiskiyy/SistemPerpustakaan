package sistemperpustakaan.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DataPeminjaman implements Peminjaman {

    private String idPinjam;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private boolean status;
    private Buku bukuPinjam;
    private static final int DENDA_PER_HARI = 1000;

    // Constructor
    public DataPeminjaman(String idPinjam,
                           LocalDate tanggalPinjam,
                           LocalDate tanggalKembali,
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

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(LocalDate tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(LocalDate tanggalKembali) {
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

        if (bukuPinjam != null) {
            bukuPinjam.kurangiStok();
        }
        status = true;
        System.out.println("Buku berhasil dipinjam");

    }

    @Override
    public void kembalikanBuku() {

        if (bukuPinjam != null) {
            bukuPinjam.tambahStok();
        }
        status = false;
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

    @Override
    public void bukuPinjam(Buku buku) {
        this.bukuPinjam = buku;
        pinjamBuku();
    }

    @Override
    public void bukuKembalikan(Buku buku) {
        this.bukuPinjam = buku;
        kembalikanBuku();
    }

    @Override
    public long hitungDenda() {
        if (tanggalKembali == null) {
            return 0;
        }

        long hariTerlambat = ChronoUnit.DAYS.between(tanggalKembali, LocalDate.now());
        return Math.max(0, hariTerlambat) * DENDA_PER_HARI;
    }
}
