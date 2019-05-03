package com.hamz.hamzplane.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hamz on 17/12/2017.
 */

public class Ringkas {

    @SerializedName("id_penerbangan")
        private Integer id_penerbangan;
    @SerializedName("kotaAsal")
        String kotaAsal;
    @SerializedName("kotaTujuan")
        String kotaTujuan;
    @SerializedName("tgl_terbang")
            private String tanggal;
    @SerializedName("nmMaskapai")
            private String maskapai;
    @SerializedName("dari")
            private String kodeAsal;
    @SerializedName("ke")
            private String kodeTujuan;
    @SerializedName("nama")
            private String pesawat;
    @SerializedName("anak")
            private Integer hargaAnak;
    @SerializedName("dewasa")
            private Integer hargaDewasa;

    public Ringkas(){}

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public void setKodeAsal(String kodeAsal) {
        this.kodeAsal = kodeAsal;
    }

    public String getKodeAsal() {
        return kodeAsal;
    }

    public void setKodeTujuan(String kodeTujuan) {
        this.kodeTujuan = kodeTujuan;
    }

    public String getKodeTujuan() {
        return kodeTujuan;
    }

    public void setPesawat(String pesawat) {
        this.pesawat = pesawat;
    }

    public String getPesawat() {
        return pesawat;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public String getKotaAsal() {
        return kotaAsal;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setHargaAnak(Integer hargaAnak) {
        this.hargaAnak = hargaAnak;
    }

    public Integer getHargaAnak() {
        return hargaAnak;
    }

    public void setHargaDewasa(Integer hargaDewasa) {
        this.hargaDewasa = hargaDewasa;
    }

    public Integer getHargaDewasa() {
        return hargaDewasa;
    }

    public void setId_penerbangan(Integer id_penerbangan) {
        this.id_penerbangan = id_penerbangan;
    }

    public Integer getId_penerbangan() {
        return id_penerbangan;
    }
}
