package com.koreait.myjwtapp.repository.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koreait.myjwtapp.repository.models.response.common.Data;
import com.koreait.myjwtapp.repository.models.response.common.Result;

import java.io.Serializable;
import java.util.List;

public class ResUpdatePost extends Result {

    @SerializedName("data")
    @Expose
    public Data data = null;

}
