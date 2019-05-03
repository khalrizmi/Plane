package com.hamz.hamzplane.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hamz on 15/12/2017.
 */

public class Penerbangan {

    @SerializedName("id_penerbangan")
    private int idPenerbangan;
    @SerializedName("nmMaskapai")
    private String kapal;
    @SerializedName("nama")
    private String pesawat;
    @SerializedName("masuk")
    private String jam;
    @SerializedName("keluar")
    private String keluar;
    private String waktu;
    @SerializedName("tgl_terbang")
    private String langsung;
    @SerializedName("dewasa")
    private String harga;

    public Penerbangan(){}

    public Penerbangan(String kapal, String jam, String waktu, String langsung, String harga ){
        this.kapal = kapal;
        this.jam = jam;
        this.waktu = waktu;
        this.langsung = langsung;
        this.harga = harga;
    }

    public int getIdPenerbangan() {
        return idPenerbangan;
    }

    public void setIdPenerbangan(int idPenerbangan) {
        this.idPenerbangan = idPenerbangan;
    }

    public String getKapal() {
        return kapal;
    }

    public void setKapal(String kapal) {
        this.kapal = kapal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getLangsung() {
        return langsung;
    }

    public void setLangsung(String langsung) {
        this.langsung = langsung;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setKeluar(String keluar) {
        this.keluar = keluar;
    }

    public String getKeluar() {
        return keluar;
    }

    public void setPesawat(String pesawat) {
        this.pesawat = pesawat;
    }

    public String getPesawat() {
        return pesawat;
    }
}
