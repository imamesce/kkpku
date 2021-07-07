package id.imam.adminkkp.model;

public class ModelTambahWisata {
    String gambar_wisata;
    String tempat_wisata;
    String deskripsi_wisata;
    String harga_wisata;
    String keterangan_wisata;
    String status;
    String nama_wisata;

    public String getGambar_wisata() {
        return gambar_wisata;
    }

    public void setGambar_wisata(String gambar_wisata) {
        this.gambar_wisata = gambar_wisata;
    }

    public String getTempat_wisata() {
        return tempat_wisata;
    }

    public void setTempat_wisata(String tempat_wisata) {
        this.tempat_wisata = tempat_wisata;
    }

    public String getDeskripsi_wisata() {
        return deskripsi_wisata;
    }

    public void setDeskripsi_wisata(String deskripsi_wisata) {
        this.deskripsi_wisata = deskripsi_wisata;
    }

    public String getHarga_wisata() {
        return harga_wisata;
    }

    public void setHarga_wisata(String harga_wisata) {
        this.harga_wisata = harga_wisata;
    }

    public String getKeterangan_wisata() {
        return keterangan_wisata;
    }

    public void setKeterangan_wisata(String keterangan_wisata) {
        this.keterangan_wisata = keterangan_wisata;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public ModelTambahWisata(String gambar_wisata, String tempat_wisata, String deskripsi_wisata, String harga_wisata, String keterangan_wisata, String status, String nama_wisata) {
        this.gambar_wisata = gambar_wisata;
        this.tempat_wisata = tempat_wisata;
        this.deskripsi_wisata = deskripsi_wisata;
        this.harga_wisata = harga_wisata;
        this.keterangan_wisata = keterangan_wisata;
        this.status = status;
        this.nama_wisata = nama_wisata;
    }

    public ModelTambahWisata() {
    }
}
