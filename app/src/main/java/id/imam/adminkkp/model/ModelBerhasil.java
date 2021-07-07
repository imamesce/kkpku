package id.imam.adminkkp.model;

public class ModelBerhasil {
    String nama_pemesan;
    String nama_wisata;
    String tempat_wisata;
        String gambar_wisata;

    public String getTempat_wisata() {
        return tempat_wisata;
    }

    public void setTempat_wisata(String tempat_wisata) {
        this.tempat_wisata = tempat_wisata;
    }

    public ModelBerhasil(String tempat_wisata) {
        this.tempat_wisata = tempat_wisata;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getGambar_wisata() {
        return gambar_wisata;
    }

    public void setGambar_wisata(String gambar_wisata) {
        this.gambar_wisata = gambar_wisata;
    }

    public ModelBerhasil(String nama_wisata, String gambar_wisata) {
        this.nama_wisata = nama_wisata;
        this.gambar_wisata = gambar_wisata;
    }

    public ModelBerhasil() {
    }
}
