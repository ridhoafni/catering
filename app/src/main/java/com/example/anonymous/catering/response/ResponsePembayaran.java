package com.example.anonymous.catering.response;

import com.example.anonymous.catering.models.Pembayaran;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePembayaran {
    @SerializedName("master")
    @Expose
    private List<Pembayaran> master = null;

    public List<Pembayaran> getMaster() {
        return master;
    }

    public void setMaster(List<Pembayaran> master) {
        this.master = master;
    }
}
