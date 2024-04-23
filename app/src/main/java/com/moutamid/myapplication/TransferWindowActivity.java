package com.moutamid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;

import java.util.Random;

public class TransferWindowActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText editTextIbanDestino, edittext_telephone, edittext_cantidad_transferir, edittext_amount_transferir;
    private static final String IBAN_REGEX = "^[Ee][Ss]\\d{2} [A-Za-z\\d]{4} [A-Za-z\\d]{4} [A-Za-z\\d]{2} [A-Za-z\\d]{4} [A-Za-z\\d]{4} [A-Za-z\\d]{4}$";
    private static final int IBAN_LENGTH = 29;

    private double currentBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_window);
        editTextIbanDestino = findViewById(R.id.edittext_iban_destino);
        edittext_telephone = findViewById(R.id.edittext_telephone);
        edittext_cantidad_transferir = findViewById(R.id.edittext_cantidad_transferir);
        edittext_amount_transferir = findViewById(R.id.edittext_amount_transferir);
        databaseHelper = new DatabaseHelper(this);
        editTextIbanDestino.setFilters(new InputFilter[]{new InputFilter.LengthFilter(IBAN_LENGTH)});
        editTextIbanDestino.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No implementation needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No implementation needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Remove any non-alphanumeric characters


                Log.d("s", s.length()+"");
                String cleanedText = s.toString().replaceAll("[^A-Za-z0-9]", "");

                // Insert spaces after every four characters
                StringBuilder formattedIban = new StringBuilder();
                for (int i = 0; i < cleanedText.length(); i++) {
                    if (i > 0 && i % 4 == 0) {
                        formattedIban.append(" ");
                    }
                    formattedIban.append(cleanedText.charAt(i));
                }

                editTextIbanDestino.removeTextChangedListener(this);
                editTextIbanDestino.setText(formattedIban.toString());
                editTextIbanDestino.setSelection(formattedIban.length());
                editTextIbanDestino.addTextChangedListener(this);
            }
        });
        double total_amount = (double) Stash.getObject("amount", Double.class);

        currentBalance = total_amount;

    }

    public void next(View view) {
        if (editTextIbanDestino.getText().toString().length() == 29) {
            String destinationIBAN = editTextIbanDestino.getText().toString().trim();
            String recipientPhoneNumber = edittext_telephone.getText().toString().trim();
            String purposeOfTransfer = edittext_cantidad_transferir.getText().toString().trim();
            String amountToTransfer = edittext_amount_transferir.getText().toString().trim();
            if (destinationIBAN.isEmpty()) {
                editTextIbanDestino.setError("Required");
                return;
            }
            if (recipientPhoneNumber.isEmpty()) {
                edittext_telephone.setError("Required");
                return;
            }
            if (purposeOfTransfer.isEmpty()) {
                edittext_cantidad_transferir.setError("Required");
                return;
            }
            if (amountToTransfer.isEmpty()) {
                edittext_amount_transferir.setError("Required");
                return;
            }
            Stash.put("destinationIBAN", destinationIBAN);
            Stash.put("recipientPhoneNumber", recipientPhoneNumber);
            Stash.put("purposeOfTransfer", purposeOfTransfer);
            Stash.put("amountToTransfer", amountToTransfer);
            if (Double.parseDouble(String.valueOf(amountToTransfer)) > currentBalance) {
                startActivity(new Intent(this, TransferErrorActivity.class));
            } else {
                Random random = new Random();
                int randomNumber = random.nextInt(2);
                if (randomNumber == 0) {
                    boolean isInserted = databaseHelper.addUserTransferData(destinationIBAN, recipientPhoneNumber, purposeOfTransfer, amountToTransfer);
                    if (isInserted) {
                        currentBalance -= Double.parseDouble(amountToTransfer);
                        Stash.put("amount", currentBalance);
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(this, TransferErrorActivity.class));
                    }
                } else {
                    startActivity(new Intent(this, TransferErrorActivity.class));
                }
            }
        }
        else
        {
            editTextIbanDestino.setError("Inavlid Format");
        }
    }

}