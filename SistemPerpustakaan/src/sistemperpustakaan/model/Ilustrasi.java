package sistemperpustakaan.model;

public class Ilustrasi extends Buku {

    private String ilustrator;
    private String warna;

    // Constructor
    public Ilustrasi(String ilustrator, String warna,
                     String idBuku, String judul,
                     String penulis, String genre, int stok) {

        super(idBuku, judul, penulis, genre, stok);

        this.ilustrator = ilustrator;
        this.warna = warna;
    }

    // Getter Setter
    public String getIlustrator() {
        return ilustrator;
    }

    public void setIlustrator(String ilustrator) {
        this.ilustrator = ilustrator;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    // Override Method
    @Override
    public void getKategori() {

        System.out.println("Kategori Buku : Ilustrasi");

    }
}