package id.imam.adminkkp.model;

public class ModelSemuaAdmin {
    String username;
    String email;
    String id_admin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }

    public ModelSemuaAdmin(String username, String email, String id_admin) {
        this.username = username;
        this.email = email;
        this.id_admin = id_admin;
    }

    public ModelSemuaAdmin() {
    }
}
