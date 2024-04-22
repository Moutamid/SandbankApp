package com.moutamid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;

public class UserProfileActivity extends AppCompatActivity {
    EditText current_user_IBAN, current_dni, password;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        current_user_IBAN = findViewById(R.id.current_user_IBAN);
        current_dni = findViewById(R.id.current_dni);
        password = findViewById(R.id.password);
        databaseHelper = new DatabaseHelper(this);

    }

    public void home(View view) {

        String current_userIBAN = current_user_IBAN.getText().toString().trim();
        String currentDni = current_dni.getText().toString().trim();
        String password_str = password.getText().toString().trim();
        if (current_userIBAN.isEmpty()) {
            current_user_IBAN.setError("Required");
            return;
        }
        if (currentDni.isEmpty()) {
            current_dni.setError("Required");
            return;
        }
        if (password_str.isEmpty()) {
            password.setError("Required");
            return;
        }
        if ((databaseHelper.checkLogin(currentDni, password_str))) {
            String destinationIBAN = Stash.getString("destinationIBAN");
            String recipientPhoneNumber = Stash.getString("recipientPhoneNumber");
            String purposeOfTransfer = Stash.getString("purposeOfTransfer");
            String amountToTransfer = Stash.getString("amountToTransfer");

            boolean isInserted = databaseHelper.addUserTransferData(destinationIBAN, recipientPhoneNumber, purposeOfTransfer, amountToTransfer);
            if (isInserted) {
                int amount = Stash.getInt("amount");
                amount += Integer.parseInt(amountToTransfer);
                Stash.put("amount",amount );
                startActivity(new Intent(this, UserProfileActivity.class));
            } else {
                Toast.makeText(this, "Failed to add data to database", Toast.LENGTH_SHORT).show();
            }
            Stash.put("IBAN", "IBAN: " + current_userIBAN);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        else
        {
            startActivity(new Intent(this, TransferErrorActivity.class));

        }
    }

    public  void  home_(View view)
    {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

}