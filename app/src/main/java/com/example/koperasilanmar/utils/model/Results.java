
package com.example.koperasilanmar.utils.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

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
    @SerializedName("nama_role")
    @Expose
    private String namaRole;
    @SerializedName("id_gasim")
    @Expose
    private Integer idGasim;
    @SerializedName("gaji")
    @Expose
    private Integer gaji;
    @SerializedName("simpanan")
    @Expose
    private Integer simpanan;
    @SerializedName("id_mutasi")
    @Expose
    private Integer idMutasi;

    @SerializedName("keluar_stn")
    @Expose
    private String keluarStn;

    @SerializedName("tglKMutasi")
    @Expose
    private String tglMutasi;
    @SerializedName("tglKPensiun")
    @Expose
    private String tglPensiun;

    @SerializedName("active_mutasi")
    private Integer activeMutasi;

    @SerializedName("active_pensiun")
    private Integer activePensiun;

    @SerializedName("namaStn")
    private String namaStn;


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

    public String getNamaRole() {
        return namaRole;
    }

    public void setNamaRole(String namaRole) {
        this.namaRole = namaRole;
    }

    public Integer getIdGasim() {
        return idGasim;
    }

    public void setIdGasim(Integer idGasim) {
        this.idGasim = idGasim;
    }

    public Integer getGaji() {
        return gaji;
    }

    public void setGaji(Integer gaji) {
        this.gaji = gaji;
    }

    public Integer getSimpanan() {
        return simpanan;
    }
    public void setSimpanan(Integer simpanan) {
        this.simpanan = simpanan;
    }

    public Integer getIdMutasi() {
        return idMutasi;
    }

    public void setIdMutasi(Integer idMutasi) {
        this.idMutasi = idMutasi;
    }

    public String getKeluarStn() {
        return keluarStn;
    }

    public void setKeluarStn(String keluarStn) {
        this.keluarStn = keluarStn;
    }

    @Nullable
    public Integer getActiveMutasi() {
        return activeMutasi;
    }
    @Nullable
    public Integer getActivePensiun() {
        return activePensiun;
    }

    public String getTglmutasi() {
        return tglMutasi;
    }
    public String getTglPensiun() {
        return tglPensiun;
    }

    public String getNamaStn() {
        return namaStn;
    }
    public void setActiveMutasi(Integer activeMutasi) {
        this.activeMutasi = activeMutasi;
    }

}
