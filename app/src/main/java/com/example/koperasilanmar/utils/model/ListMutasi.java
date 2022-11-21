
package com.example.koperasilanmar.utils.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListMutasi {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("results")
    @Expose
    private List<com.example.koperasilanmar.utils.model.admin.menu.Result> results = null;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<com.example.koperasilanmar.utils.model.admin.menu.Result> getResults() {
        return results;
    }

    public void setResults(List<com.example.koperasilanmar.utils.model.admin.menu.Result> results) {
        this.results = results;
    }

}
