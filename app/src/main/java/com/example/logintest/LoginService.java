package com.example.logintest;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("api/WebApitest5/Login/")
    Call<TokenData> login(@Body @NonNull UserLoginData loginData);
}
