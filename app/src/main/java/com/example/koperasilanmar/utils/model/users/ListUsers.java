
package com.example.koperasilanmar.utils.model.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListUsers {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("result")
    @Expose
    private List<ResultUser> result = null;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultUser> getResult() {
        return result;
    }

    public void setResult(List<ResultUser> result) {
        this.result = result;
    }

}
