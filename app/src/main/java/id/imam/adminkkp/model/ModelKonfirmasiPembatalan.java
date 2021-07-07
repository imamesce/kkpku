package id.imam.adminkkp.model;

public class ModelKonfirmasiPembatalan {
    String alamat_email;
    String  harga_wisata;
    String id_order;
    String id_wisata;
    String keterangan;
    String keterangan_pembatalan;
    String nama_pemesan;
    String nama_wisata;
    String status;
    String tanggal_berangkat;
    String telepon_pemesan;
    String tempat_wisata;

    public String getAlamat_email() {
        return alamat_email;
    }

    public void setAlamat_email(String alamat_email) {
        this.alamat_email = alamat_email;
    }

    public String getHarga_wisata() {
        return harga_wisata;
    }

    public void setHarga_wisata(String harga_wisata) {
        this.harga_wisata = harga_wisata;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public String getId_wisata() {
        return id_wisata;
    }

    public void setId_wisata(String id_wisata) {
        this.id_wisata = id_wisata;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeterangan_pembatalan() {
        return keterangan_pembatalan;
    }

    public void setKeterangan_pembatalan(String keterangan_pembatalan) {
        this.keterangan_pembatalan = keterangan_pembatalan;
    }

    public String getNama_pemesan() {
        return nama_pemesan;
    }

    public void setNama_pemesan(String nama_pemesan) {
        this.nama_pemesan = nama_pemesan;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal_berangkat() {
        return tanggal_berangkat;
    }

    public void setTanggal_berangkat(String tanggal_berangkat) {
        this.tanggal_berangkat = tanggal_berangkat;
    }

    public String getTelepon_pemesan() {
        return telepon_pemesan;
    }

    public void setTelepon_pemesan(String telepon_pemesan) {
        this.telepon_pemesan = telepon_pemesan;
    }

    public String getTempat_wisata() {
        return tempat_wisata;
    }

    public void setTempat_wisata(String tempat_wisata) {
        this.tempat_wisata = tempat_wisata;
    }

    public ModelKonfirmasiPembatalan() {
    }

    public ModelKonfirmasiPembatalan(String alamat_email, String harga_wisata, String id_order, String id_wisata, String keterangan, String keterangan_pembatalan, String nama_pemesan, String nama_wisata, String status, String tanggal_berangkat, String telepon_pemesan, String tempat_wisata) {
        this.alamat_email = alamat_email;
        this.harga_wisata = harga_wisata;
        this.id_order = id_order;
        this.id_wisata = id_wisata;
        this.keterangan = keterangan;
        this.keterangan_pembatalan = keterangan_pembatalan;
        this.nama_pemesan = nama_pemesan;
        this.nama_wisata = nama_wisata;
        this.status = status;
        this.tanggal_berangkat = tanggal_berangkat;
        this.telepon_pemesan = telepon_pemesan;
        this.tempat_wisata = tempat_wisata;
    }
}
