package sistemperpustakaan.model;

public class Fiksi extends Buku {

    private String author;
    private int volume;

    // Constructor
    public Fiksi(String author, int volume,
                  String idBuku, String judul,
                  String penulis, String genre, int stok) {

        super(idBuku, judul, penulis, genre, stok);

        this.author = author;
        this.volume = volume;
    }

    // Getter Setter
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    // Override Method
    @Override
    public void getKategori() {

        System.out.println("Kategori Buku : Fiksi");

    }
}