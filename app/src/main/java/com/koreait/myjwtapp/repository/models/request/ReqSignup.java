package com.koreait.myjwtapp.repository.models.request;

//{
//        "username":"devnote1",
//        "password":"1234",
//        "email":"devnote1@nate.com"
//        }

public class ReqSignup {
    public String username;
    public String password;
    public String email;

    public ReqSignup(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
