
package com.example.koperasilanmar.utils.model.mutasi_pensiun;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class ListPensiun {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("mutasi")
    @Expose
    private List<Mutasi> mutasi = null;
    @SerializedName("pensiun")
    @Expose
    private List<Pensiun> pensiun = null;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Mutasi> getMutasi() {
        return mutasi;
    }

    public void setMutasi(List<Mutasi> mutasi) {
        this.mutasi = mutasi;
    }

    public List<Pensiun> getPensiun() {
        return pensiun;
    }

    public void setPensiun(List<Pensiun> pensiun) {
        this.pensiun = pensiun;
    }

}
