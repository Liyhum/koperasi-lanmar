
package com.example.koperasilanmar.utils.model.expended;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Simpanan {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("nama_stn_kerja")
    @Expose
    private String namaStnKerja;
    @SerializedName("Simpanan")
    @Expose
    private String simpanan;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamaStnKerja() {
        return namaStnKerja;
    }

    public void setNamaStnKerja(String namaStnKerja) {
        this.namaStnKerja = namaStnKerja;
    }

    public String getSimpanan() {
        return simpanan;
    }

    public void setSimpanan(String simpanan) {
        this.simpanan = simpanan;
    }

}
