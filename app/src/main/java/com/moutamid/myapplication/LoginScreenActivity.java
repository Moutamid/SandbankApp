package com.moutamid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fxn.stash.Stash;

public class LoginScreenActivity extends AppCompatActivity {
    private EditText etIdentifier, etPassword;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        dbHelper = new DatabaseHelper(this);
        etIdentifier = findViewById(R.id.et_identifier);
        etPassword = findViewById(R.id.et_password);
    }
    public void main_page(View view) {
        String identifier = etIdentifier.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (identifier.isEmpty()) {
            Toast.makeText(this, "Please enter your identifier", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dbHelper.checkLogin(identifier, password)) {
            Stash.put("login", true);
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish(); // Finish the current activity
        } else {
            Toast.makeText(this, "Invalid identifier or password", Toast.LENGTH_SHORT).show();
        }
    }
}