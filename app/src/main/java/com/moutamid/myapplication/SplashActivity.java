package com.moutamid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;

public class SplashActivity extends AppCompatActivity {
    private static final String IBAN_PREFIX = "ES21 1234 0000 0000";
    private static final String IBAN_COUNTER_KEY = "ibanCounter";
    private int ibanCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView textView = findViewById(R.id.textView1);
        ibanCounter = Stash.getInt(IBAN_COUNTER_KEY, 1);

        generateUniqueIBAN();
        SpannableString spannableString = new SpannableString(textView.getText());
        int startIndex = textView.getText().toString().indexOf("Regístrate");

        int endIndex = startIndex + "Regístrate".length();
        spannableString.setSpan(new UnderlineSpan(), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);

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
    private String generateUniqueIBAN() {
        String ibanNumber = IBAN_PREFIX + String.format("%04d", ibanCounter);
        ibanCounter++;
        Stash.put(IBAN_COUNTER_KEY, ibanCounter);
        Log.d("data", ibanNumber);
        return ibanNumber;
    }
}