package com.example.platzigram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.platzigram.view.ContainerActivity;
import com.example.platzigram.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goCreateAccount(View view){
        Intent intent =new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }
}
