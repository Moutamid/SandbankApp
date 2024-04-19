package com.moutamid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Stash.getBoolean("login", false))
        {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void create_account(View view) {
        startActivity(new Intent(this, CreateAccountActivity.class));
    }

    public void login(View view) {
        startActivity(new Intent(this, LoginScreenActivity.class));
    }
}