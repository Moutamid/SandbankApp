package com.moutamid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UpdateProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
    }

    public void error(View view) {
        startActivity(new Intent(this, CreatingAccountErrorActvity.class));

    }

    public void home(View view) {
        startActivity(new Intent(this, MainActivity.class));

    }
}