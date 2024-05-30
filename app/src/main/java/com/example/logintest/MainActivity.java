package com.example.logintest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private EditText accountText;
    private EditText pwdText;
    private final String API_URL = "https://7s4d8r3p-7171.asse.devtunnels.ms/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loginBtn = findViewById(R.id.button);
        accountText = findViewById(R.id.editTextText);
        pwdText = findViewById(R.id.editTextTextPassword);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String account = accountText.getText().toString();
        String pwd = pwdText.getText().toString();
        if(account.isEmpty() || pwd.isEmpty()){
            Toast.makeText(MainActivity.this,"帳號或密碼為空，請檢查一下", Toast.LENGTH_SHORT).show();
            return;
        }
        authenticate(account,pwd);
        //navigateToHome(token);
    }

    private void navigateToHome(String token) {
    }

    private void authenticate(String uid, String pwd) {
        final String[] token = {""};
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService login = retrofit.create(LoginService.class);
        Call<TokenData> req = login.login(new UserLoginData(uid,pwd));
        req.enqueue(new Callback<TokenData>() {
            @Override
            public void onResponse(@NonNull Call<TokenData> call, @NonNull Response<TokenData> response) {
//                Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                token[0] = response.body().getToken();
                Toast.makeText(MainActivity.this, token[0], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<TokenData> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}