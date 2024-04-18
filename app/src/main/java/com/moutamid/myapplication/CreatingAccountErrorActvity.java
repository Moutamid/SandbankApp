package com.moutamid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreatingAccountErrorActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_one_actvity);
    }

    public void next(View view) {
        startActivity(new Intent(this, UpdateProfileActivity.class));

    }
}