
package com.koreait.myjwtapp.repository.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koreait.myjwtapp.repository.models.response.common.Data;
import com.koreait.myjwtapp.repository.models.response.common.Result;


public class ResLogin extends Result {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }


}
