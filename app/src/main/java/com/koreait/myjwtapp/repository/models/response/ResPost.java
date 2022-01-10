package com.koreait.myjwtapp.repository.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koreait.myjwtapp.repository.models.response.common.Data;
import com.koreait.myjwtapp.repository.models.response.common.Result;

import java.util.List;

public class ResPost extends Result {

    @SerializedName("data")
    @Expose
    public List<Data> listData = null;

    public List<Data> getListData() {
        return listData;
    }
}
