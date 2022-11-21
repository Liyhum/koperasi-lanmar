
package com.example.koperasilanmar.utils.model.mutasi_pensiun;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pensiun {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("users")
    @Expose
    private List<User__1> users = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User__1> getUsers() {
        return users;
    }

    public void setUsers(List<User__1> users) {
        this.users = users;
    }

}
