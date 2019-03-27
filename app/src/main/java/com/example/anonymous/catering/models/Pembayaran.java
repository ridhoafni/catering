package com.example.anonymous.catering.models;
import com.google.gson.annotations.SerializedName;

public class Pembayaran {

    @SerializedName("id_pembayaran")
    private Integer idPembayaran;
    @SerializedName("pemesanan_id")
    private Integer pemesananId;
    @SerializedName("bukti_transfer")
    private String buktiTransfer;
    @SerializedName("jumlah_transfer")
    private Integer jumlahTransfer;
    @SerializedName("total")
    private Integer total;
    @SerializedName("tanggal_upload")
    private String tanggalUpload;

    public Integer getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(Integer idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public Integer getPemesananId() {
        return pemesananId;
    }

    public void setPemesananId(Integer pemesananId) {
        this.pemesananId = pemesananId;
    }

    public String getBuktiTransfer() {
        return buktiTransfer;
    }

    public void setBuktiTransfer(String buktiTransfer) {
        this.buktiTransfer = buktiTransfer;
    }

    public Integer getJumlahTransfer() {
        return jumlahTransfer;
    }

    public void setJumlahTransfer(Integer jumlahTransfer) {
        this.jumlahTransfer = jumlahTransfer;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTanggalUpload() {
        return tanggalUpload;
    }

    public void setTanggalUpload(String tanggalUpload) {
        this.tanggalUpload = tanggalUpload;
    }
}
