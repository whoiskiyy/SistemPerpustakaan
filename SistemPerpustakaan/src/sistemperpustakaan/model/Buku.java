package sistemperpustakaan.model;

public abstract class Buku {

    protected String idBuku;
    protected String judul;
    protected String penulis;
    protected String genre;
    protected int stok;

    // Constructor
    public Buku(String idBuku, String judul,
                String penulis, String genre, int stok) {

        this.idBuku = idBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.genre = genre;
        this.stok = stok;
    }

    // Getter Setter
    public String getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    // Method
    public void getDetail() {

        System.out.println("ID Buku : " + idBuku);
        System.out.println("Judul : " + judul);
        System.out.println("Penulis : " + penulis);
        System.out.println("Genre : " + genre);
        System.out.println("Stok : " + stok);

    }

    // Abstract Method
    public abstract void getKategori();
}