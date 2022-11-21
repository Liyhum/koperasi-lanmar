
package com.example.koperasilanmar.utils.model.mutasi_pensiun;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Mutasi {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("users")
    @Expose
    private List<com.example.koperasilanmar.utils.model.admin.menu.User> users = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<com.example.koperasilanmar.utils.model.admin.menu.User> getUsers() {
        return users;
    }

    public void setUsers(List<com.example.koperasilanmar.utils.model.admin.menu.User> users) {
        this.users = users;
    }

}
