package com.example.anonymous.catering.response;

import com.example.anonymous.catering.models.Guru;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponGuruDetail {
    @SerializedName("master")
    @Expose
    private Guru master;

    public Guru getMaster() {
        return master;
    }

    public void setMaster(Guru master) {
        this.master = master;
    }
}
