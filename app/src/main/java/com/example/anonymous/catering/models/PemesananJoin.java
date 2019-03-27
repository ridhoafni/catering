package com.example.anonymous.catering.models;

import com.google.gson.annotations.SerializedName;

public class PemesananJoin {
    @SerializedName("id_pemesanan")
    private String idPemesanan;
    @SerializedName("member_id")
    private String memberId;
    @SerializedName("nama_paket")
    private String namaPaket;
    @SerializedName("jumlah_pesanan")
    private String jumlahPesanan;
    @SerializedName("total")
    private String total;
    @SerializedName("alamat_lengkap")
    private String alamatLengkap;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("tgl_pesanan")
    private String tglPesanan;
    @SerializedName("pesan_tambahan")
    private String pesanTambahan;
    @SerializedName("status")
    private String status;
    @SerializedName("id_paket")
    private String idPaket;
    @SerializedName("harga_paket")
    private String hargaPaket;
    @SerializedName("makanan_utama")
    private String makananUtama;
    @SerializedName("makanan_pembuka")
    private String makananPembuka;
    @SerializedName("makanan_penutup")
    private String makananPenutup;
    @SerializedName("makanan_pondok")
    private String makananPondok;
    @SerializedName("bonus")
    private String bonus;

    public String getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(String idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public String getJumlahPesanan() {
        return jumlahPesanan;
    }

    public void setJumlahPesanan(String jumlahPesanan) {
        this.jumlahPesanan = jumlahPesanan;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTglPesanan() {
        return tglPesanan;
    }

    public void setTglPesanan(String tglPesanan) {
        this.tglPesanan = tglPesanan;
    }

    public String getPesanTambahan() {
        return pesanTambahan;
    }

    public void setPesanTambahan(String pesanTambahan) {
        this.pesanTambahan = pesanTambahan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public String getHargaPaket() {
        return hargaPaket;
    }

    public void setHargaPaket(String hargaPaket) {
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
