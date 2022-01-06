package com.koreait.myjwtapp.repository.models.request;

//{
//        "username":"devnote1",
//        "password":"1234"
//        }
public class ReqLogin {

    public String username;
    public String password;

    public ReqLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
