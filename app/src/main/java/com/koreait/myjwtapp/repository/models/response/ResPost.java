package com.koreait.myjwtapp.repository.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResPost {

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("msg")
    @Expose
    public String msg;

    @SerializedName("data")
    @Expose
    public List<Data> data = null;

    public class Data implements Serializable {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("content")
        @Expose
        public String content;
        @SerializedName("user")
        @Expose
        public User user;
        @SerializedName("created")
        @Expose
        public String created;
        @SerializedName("updated")
        @Expose
        public String updated;

        public class User implements Serializable {
            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("username")
            @Expose
            public String username;
            @SerializedName("password")
            @Expose
            public String password;
            @SerializedName("email")
            @Expose
            public String email;
            @SerializedName("created")
            @Expose
            public String created;
            @SerializedName("updated")
            @Expose
            public String updated;

        }
    }
}
