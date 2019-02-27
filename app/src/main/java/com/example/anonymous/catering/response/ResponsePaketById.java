package com.example.anonymous.catering.response;

import com.example.anonymous.catering.models.Paket;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePaketById {
    @SerializedName("master")
    @Expose
    private Paket master;

    public Paket getMaster() {
        return master;
    }

    public void setMaster(Paket master) {
        this.master = master;
    }
}
