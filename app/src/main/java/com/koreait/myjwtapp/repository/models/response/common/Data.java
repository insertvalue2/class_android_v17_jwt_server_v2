
package com.koreait.myjwtapp.repository.models.response.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.koreait.myjwtapp.repository.models.response.ResPost;

import java.io.Serializable;


public class Data implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("user")
    @Expose
    private User user;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Object getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }
}
