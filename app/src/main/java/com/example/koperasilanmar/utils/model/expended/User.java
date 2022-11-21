
package com.example.koperasilanmar.utils.model.expended;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class User {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("nama_stn_kerja")
    @Expose
    private String namaStnKerja;
    @SerializedName("Personil")
    @Expose
    private Integer personil;

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

    public Integer getPersonil() {
        return personil;
    }

    public void setPersonil(Integer personil) {
        this.personil = personil;
    }

}
