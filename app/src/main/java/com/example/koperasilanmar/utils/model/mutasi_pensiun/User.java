
package com.example.koperasilanmar.utils.model.mutasi_pensiun;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id_mutasi")
    @Expose
    private Integer idMutasi;
    @SerializedName("no_tr")
    @Expose
    private String noTr;
    @SerializedName("no_st")
    @Expose
    private String noSt;
    @SerializedName("keluar_stn")
    @Expose
    private String keluarStn;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("npp")
    @Expose
    private String npp;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("img_user")
    @Expose
    private String imgUser;

    public Integer getIdMutasi() {
        return idMutasi;
    }

    public void setIdMutasi(Integer idMutasi) {
        this.idMutasi = idMutasi;
    }

    public String getNoTr() {
        return noTr;
    }

    public void setNoTr(String noTr) {
        this.noTr = noTr;
    }

    public String getNoSt() {
        return noSt;
    }

    public void setNoSt(String noSt) {
        this.noSt = noSt;
    }

    public String getKeluarStn() {
        return keluarStn;
    }

    public void setKeluarStn(String keluarStn) {
        this.keluarStn = keluarStn;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNpp() {
        return npp;
    }

    public void setNpp(String npp) {
        this.npp = npp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

}
