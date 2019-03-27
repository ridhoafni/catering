package com.example.anonymous.catering.models;
import com.google.gson.annotations.SerializedName;

public class Pemesanan {
    @SerializedName("id_pemesanan")
    private Integer idPemesanan;
    @SerializedName("member_id")
    private Integer memberId;
    @SerializedName("nama_paket")
    private String namaPaket;
    @SerializedName("jumlah_pesanan")
    private Integer jumlahPesanan;
    @SerializedName("total")
    private Integer total;
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

    public Integer getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(Integer idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public Integer getJumlahPesanan() {
        return jumlahPesanan;
    }

    public void setJumlahPesanan(Integer jumlahPesanan) {
        this.jumlahPesanan = jumlahPesanan;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
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
}
