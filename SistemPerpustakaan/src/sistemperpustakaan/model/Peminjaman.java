package sistemperpustakaan.model;

public interface Peminjaman {

    void pinjamBuku();

    void kembalikanBuku();

    void cekStatus();

    void bukuPinjam(Buku buku);

    void bukuKembalikan(Buku buku);

    long hitungDenda();

}
