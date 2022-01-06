package com.koreait.myjwtapp.repository.models.request;

public class ReqPost {
    public String title;
    public String content;

    public ReqPost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
