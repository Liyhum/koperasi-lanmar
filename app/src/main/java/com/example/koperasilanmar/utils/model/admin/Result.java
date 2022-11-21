
package com.example.koperasilanmar.utils.model.admin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("nama_stn_kerja")
    @Expose
    private String namaStnKerja;
    @SerializedName("Number")
    @Expose
    private Integer number;

    public String getNamaStnKerja() {
        return namaStnKerja;
    }

    public void setNamaStnKerja(String namaStnKerja) {
        this.namaStnKerja = namaStnKerja;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
