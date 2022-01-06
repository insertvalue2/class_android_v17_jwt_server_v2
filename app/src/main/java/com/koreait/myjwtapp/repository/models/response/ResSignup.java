package com.koreait.myjwtapp.repository.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koreait.myjwtapp.repository.models.response.common.Data;

public class ResSignup {

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
