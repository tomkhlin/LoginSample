package com.example.logintest;

public class TokenData {
    private int id;
    private String token;

    public TokenData(int id, String token){
        this.id = id;
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}
