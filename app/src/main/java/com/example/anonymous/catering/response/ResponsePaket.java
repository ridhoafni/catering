package com.example.anonymous.catering.response;

import com.example.anonymous.catering.models.Paket;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePaket {
    @SerializedName("master")
    @Expose
    private List<Paket> master = null;

    public List<Paket> getMaster() {
        return master;
    }

    public void setMaster(List<Paket> master) {
        this.master = master;
    }
}
