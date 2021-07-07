package id.imam.adminkkp.model;

public class ModelSemuaUser {
    private String biodata;
    private String email;
    private String telepon;
    private String username;

    public ModelSemuaUser() {
    }

    public String getBiodata() {
        return biodata;
    }

    public void setBiodata(String biodata) {
        this.biodata = biodata;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ModelSemuaUser(String biodata, String email, String telepon, String username) {
        this.biodata = biodata;
        this.email = email;
        this.telepon = telepon;
        this.username = username;
    }
}
