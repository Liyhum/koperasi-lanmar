
package com.example.koperasilanmar.utils.model.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultUser {

    @SerializedName("id_users")
    @Expose
    private Integer idUsers;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("tmp_lahir")
    @Expose
    private String tmpLahir;
    @SerializedName("img_user")
    @Expose
    private String imgUser;
    @SerializedName("no_tr")
    @Expose
    private String noTr;
    @SerializedName("no_st")
    @Expose
    private String noSt;
    @SerializedName("no_kt")
    @Expose
    private String noKt;
    @SerializedName("msk_satuan")
    @Expose
    private String mskSatuan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("pngkt_gol")
    @Expose
    private String pngktGol;
    @SerializedName("npp")
    @Expose
    private String npp;
    @SerializedName("id_stn_kerja")
    @Expose
    private Integer idStnKerja;
    @SerializedName("nomor_hp")
    @Expose
    private String nomorHp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id_role")
    @Expose
    private Integer idRole;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("update_at")
    @Expose
    private String updateAt;
    @SerializedName("nama_stn_kerja")
    @Expose
    private String namaStnKerja;

    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getTmpLahir() {
        return tmpLahir;
    }

    public void setTmpLahir(String tmpLahir) {
        this.tmpLahir = tmpLahir;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
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

    public String getNoKt() {
        return noKt;
    }

    public void setNoKt(String noKt) {
        this.noKt = noKt;
    }

    public String getMskSatuan() {
        return mskSatuan;
    }

    public void setMskSatuan(String mskSatuan) {
        this.mskSatuan = mskSatuan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPngktGol() {
        return pngktGol;
    }

    public void setPngktGol(String pngktGol) {
        this.pngktGol = pngktGol;
    }

    public String getNpp() {
        return npp;
    }

    public void setNpp(String npp) {
        this.npp = npp;
    }

    public Integer getIdStnKerja() {
        return idStnKerja;
    }

    public void setIdStnKerja(Integer idStnKerja) {
        this.idStnKerja = idStnKerja;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getNamaStnKerja() {
        return namaStnKerja;
    }

    public void setNamaStnKerja(String namaStnKerja) {
        this.namaStnKerja = namaStnKerja;
    }
}
