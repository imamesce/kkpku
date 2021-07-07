package id.imam.adminkkp.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class ModelStatusPelanggan implements Serializable {
    @Exclude
    private @ServerTimestamp
    Date dibuat;
    private String id;
    private String nama_wisata;
    private String  id_transaksi;
    private String  harga_wisata;
    private String keterangan;
    private String keterangan_wisata;

    public ModelStatusPelanggan(Date dibuat, String id, String nama_wisata, String id_transaksi, String harga_wisata, String keterangan, String keterangan_wisata) {
        this.dibuat = dibuat;
        this.id = id;
        this.nama_wisata = nama_wisata;
        this.id_transaksi = id_transaksi;
        this.harga_wisata = harga_wisata;
        this.keterangan = keterangan;
        this.keterangan_wisata = keterangan_wisata;
    }

    public Date getDibuat() {
        return dibuat;
    }

    public void setDibuat(Date dibuat) {
        this.dibuat = dibuat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getHarga_wisata() {
        return harga_wisata;
    }

    public void setHarga_wisata(String harga_wisata) {
        this.harga_wisata = harga_wisata;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeterangan_wisata() {
        return keterangan_wisata;
    }

    public void setKeterangan_wisata(String keterangan_wisata) {
        this.keterangan_wisata = keterangan_wisata;
    }

    public ModelStatusPelanggan() {
    }
}
