package com.moutamid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;

public class TransferWindowActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText edittext_iban_destino, edittext_telephone, edittext_cantidad_transferir, edittext_amount_transferir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_window);
        edittext_iban_destino = findViewById(R.id.edittext_iban_destino);
        edittext_telephone = findViewById(R.id.edittext_telephone);
        edittext_cantidad_transferir = findViewById(R.id.edittext_cantidad_transferir);
        edittext_amount_transferir = findViewById(R.id.edittext_amount_transferir);
        databaseHelper = new DatabaseHelper(this);
    }

    public void next(View view) {
        String destinationIBAN = edittext_iban_destino.getText().toString().trim();
        String recipientPhoneNumber = edittext_telephone.getText().toString().trim();
        String purposeOfTransfer = edittext_cantidad_transferir.getText().toString().trim();
        String amountToTransfer = edittext_amount_transferir.getText().toString().trim();
        if (destinationIBAN.isEmpty()) {
            edittext_iban_destino.setError("Required");
            return;
        }
        if (recipientPhoneNumber.isEmpty()) {
            edittext_telephone.setError("Required");
            return;
        }
        if (purposeOfTransfer.isEmpty()) {
            edittext_cantidad_transferir.setError("Required");
            return;
        }if (amountToTransfer.isEmpty()) {
            edittext_amount_transferir.setError("Required");
            return;
        }
        Stash.put("destinationIBAN", destinationIBAN);
        Stash.put("recipientPhoneNumber", recipientPhoneNumber);
        Stash.put("purposeOfTransfer", purposeOfTransfer);
        Stash.put("amountToTransfer", amountToTransfer);
        startActivity(new Intent(this, UserProfileActivity.class));



    }
}