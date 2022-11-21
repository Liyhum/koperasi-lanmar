
package com.example.koperasilanmar.utils.model.expended;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Personil {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("users")
    @Expose
    private List<User> users = null;
    @SerializedName("simpanan")
    @Expose
    private List<Simpanan> simpanan = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Simpanan> getSimpanan() {
        return simpanan;
    }

    public void setSimpanan(List<Simpanan> simpanan) {
        this.simpanan = simpanan;
    }

}
