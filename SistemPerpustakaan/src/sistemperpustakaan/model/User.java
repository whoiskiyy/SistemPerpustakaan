package sistemperpustakaan.model;

public abstract class User {

    protected String id;
    protected String nama;
    protected String email;
    protected String password;

    // Constructor
    public User(String id, String nama, String email, String password) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    // Getter Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method
    public void login() {
        System.out.println(nama + " berhasil login");
    }

    public void logout() {
        System.out.println(nama + " berhasil logout");
    }
}