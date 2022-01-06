package com.koreait.myjwtapp.repository.models.response.common;

import com.google.gson.annotations.Expose;


public class Result {
    private int code;
    private String msg;
    @Expose
    private Data data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Data getData() {
        return data;
    }
}
