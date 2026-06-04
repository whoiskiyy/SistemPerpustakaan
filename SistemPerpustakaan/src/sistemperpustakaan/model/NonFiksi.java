package sistemperpustakaan.model;

public class NonFiksi extends Buku {

    private String subjek;
    private String referensi;

    // Constructor
    public NonFiksi(String subjek, String referensi,
                    String idBuku, String judul,
                    String penulis, String genre, int stok) {

        super(idBuku, judul, penulis, genre, stok);

        this.subjek = subjek;
        this.referensi = referensi;
    }

    // Getter Setter
    public String getSubjek() {
        return subjek;
    }

    public void setSubjek(String subjek) {
        this.subjek = subjek;
    }

    public String getReferensi() {
        return referensi;
    }

    public void setReferensi(String referensi) {
        this.referensi = referensi;
    }

    // Override Method
    @Override
    public void getKategori() {

        System.out.println("Kategori Buku : Non Fiksi");

    }
}