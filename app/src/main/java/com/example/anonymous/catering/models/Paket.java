package com.example.anonymous.catering.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paket {
    @SerializedName("id_paket")
    @Expose
    private Integer idPaket;
    @SerializedName("nama_paket")
    @Expose
    private String namaPaket;
    @SerializedName("harga_paket")
    @Expose
    private Integer hargaPaket;
    @SerializedName("makanan_utama")
    @Expose
    private String makananUtama;
    @SerializedName("makanan_pembuka")
    @Expose
    private String makananPembuka;
    @SerializedName("makanan_penutup")
    @Expose
    private String makananPenutup;
    @SerializedName("makanan_pondok")
    @Expose
    private String makananPondok;
    @SerializedName("bonus")
    @Expose
    private String bonus;

    public Integer getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(Integer idPaket) {
        this.idPaket = idPaket;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public Integer getHargaPaket() {
        return hargaPaket;
    }

    public void setHargaPaket(Integer hargaPaket) {
        this.hargaPaket = hargaPaket;
    }

    public String getMakananUtama() {
        return makananUtama;
    }

    public void setMakananUtama(String makananUtama) {
        this.makananUtama = makananUtama;
    }

    public String getMakananPembuka() {
        return makananPembuka;
    }

    public void setMakananPembuka(String makananPembuka) {
        this.makananPembuka = makananPembuka;
    }

    public String getMakananPenutup() {
        return makananPenutup;
    }

    public void setMakananPenutup(String makananPenutup) {
        this.makananPenutup = makananPenutup;
    }

    public String getMakananPondok() {
        return makananPondok;
    }

    public void setMakananPondok(String makananPondok) {
        this.makananPondok = makananPondok;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }
}
