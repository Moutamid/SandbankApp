package com.moutamid.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;

public class CreateAccountActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText etName, etLastName, etDNI, etEmail, etPhoneNumber, etDOB, etPassword, etStreet, etStreetNumber, etFloor, etPostalCode, etCity, etRegion, etCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        dbHelper = new DatabaseHelper(this);
        etName = findViewById(R.id.et_name);
        etLastName = findViewById(R.id.et_last_name);
        etDNI = findViewById(R.id.et_dni);
        etEmail = findViewById(R.id.et_email);
        etPhoneNumber = findViewById(R.id.et_phone_number);
        etDOB = findViewById(R.id.et_dob);
        etPassword = findViewById(R.id.et_password);
        etStreet = findViewById(R.id.et_street);
        etStreetNumber = findViewById(R.id.et_street_number);
        etFloor = findViewById(R.id.et_floor);
        etPostalCode = findViewById(R.id.et_postal_code);
        etCity = findViewById(R.id.et_city);
        etRegion = findViewById(R.id.et_region);
        etCountry = findViewById(R.id.et_country);

    }
    public void login(View view) {
        // Get the text from all EditText fields
        String name = etName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String dni = etDNI.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String dob = etDOB.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String street = etStreet.getText().toString().trim();
        String streetNumber = etStreetNumber.getText().toString().trim();
        String floor = etFloor.getText().toString().trim();
        String postalCode = etPostalCode.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String region = etRegion.getText().toString().trim();
        String country = etCountry.getText().toString().trim();

        if (region.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su Región", Toast.LENGTH_SHORT).show();
            return;
        }
        if (country.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su País", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su Contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        if (street.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su Dirección", Toast.LENGTH_SHORT).show();
            return;
        }
        if (streetNumber.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese el Número de su Calle", Toast.LENGTH_SHORT).show();
            return;
        }
        if (floor.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese el Piso", Toast.LENGTH_SHORT).show();
            return;
        }
        if (postalCode.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su Código Postal", Toast.LENGTH_SHORT).show();
            return;
        }
        if (city.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su Ciudad", Toast.LENGTH_SHORT).show();
            return;
        }

        if (name.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su Nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lastName.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su Apellido", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dni.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su DNI", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su Correo Electrónico", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su Número de Teléfono", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dob.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese su Fecha de Nacimiento", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!dbHelper.checkEmail(dni)) {
            Toast.makeText(this, "Este DNI ya está en uso.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 8) {
            Toast.makeText(this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
            Toast.makeText(this, "Password must contain at least one uppercase letter and one digit", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isInserted = dbHelper.addUser(name, lastName, dni, email, phoneNumber, dob, password, street, streetNumber, floor, postalCode, city, region, country);
        if (isInserted) {
            Stash.put("login", true);
            Stash.put("amount",0 );
            Stash.put("IBAN", "IBAN: 0000 0000 0000 0000 0000 0000");
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finishAffinity();
        } else {
            Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT).show();
        }
    }



}