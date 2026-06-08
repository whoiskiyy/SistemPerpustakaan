package sistemperpustakaan.model;

public class Pustakawan extends User {

    private int nip;
    private String shiftKerja;

    // Constructor
    public Pustakawan(int nip, String shiftKerja,
                      String id, String nama,
                      String email, String password) {

        super(id, nama, email, password);

        this.nip = nip;
        this.shiftKerja = shiftKerja;
    }

    // Getter Setter
    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public String getShiftKerja() {
        return shiftKerja;
    }

    public void setShiftKerja(String shiftKerja) {
        this.shiftKerja = shiftKerja;
    }

    // Method
    public void tambahBuku() {
        System.out.println("Buku berhasil ditambahkan");
    }

    public void tambahBuku(Buku buku) {
        System.out.println("Buku " + buku.getJudul() + " berhasil ditambahkan");
    }

    public void kelolaStok() {
        System.out.println("Stok buku dikelola");
    }

    public void kelolaStok(Buku buku, int stokBaru) {
        buku.setStok(stokBaru);
        System.out.println("Stok buku " + buku.getJudul() + " dikelola");
    }

    public void prosesPeminjaman() {
        System.out.println("Peminjaman diproses");
    }

    public void prosesPeminjaman(Peminjaman peminjaman) {
        peminjaman.pinjamBuku();
        System.out.println("Peminjaman diproses");
    }

    public void prosesPengembalian() {
        System.out.println("Pengembalian diproses");
    }

    public void prosesPengembalian(Peminjaman peminjaman) {
        peminjaman.kembalikanBuku();
        System.out.println("Pengembalian diproses");
    }

    public String lihatLaporan() {
        return "Menampilkan laporan";
    }
}
