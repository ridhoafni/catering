package com.example.anonymous.catering.response;

import com.example.anonymous.catering.models.Pemesanan;
import com.example.anonymous.catering.models.PemesananJoin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePemesananJoin {
    @SerializedName("master")
    @Expose
    private List<PemesananJoin> master = null;

    public List<PemesananJoin> getMaster() {
        return master;
    }

    public void setMaster(List<PemesananJoin> master) {
        this.master = master;
    }
}
