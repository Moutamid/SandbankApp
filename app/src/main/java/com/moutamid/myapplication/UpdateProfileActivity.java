package com.moutamid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.moutamid.myapplication.Model.UserData;

public class UpdateProfileActivity extends AppCompatActivity {
    private EditText etName, etLastName, etDNI, etEmail, etPhoneNumber, etDOB, etPassword,
            etStreet, etStreetNumber, etFloor, etPostalCode, etCity, etRegion, etCountry;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        dbHelper = new DatabaseHelper(this);

        // Initialize EditText fields
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

        // Retrieve data from SQLite database and set it to EditText fields
        UserData userData = dbHelper.getUserData();

        if (userData != null) {
            etName.setText(userData.getName());
            etLastName.setText(userData.getLastName());
            etDNI.setText(userData.getDni());
            etEmail.setText(userData.getEmail());
            etPhoneNumber.setText(userData.getPhoneNumber());
            etDOB.setText(userData.getDob());
            etPassword.setText(userData.getPassword());
            etStreet.setText(userData.getStreet());
            etStreetNumber.setText(userData.getStreetNumber());
            etFloor.setText(userData.getFloor());
            etPostalCode.setText(userData.getPostalCode());
            etCity.setText(userData.getCity());
            etRegion.setText(userData.getRegion());
            etCountry.setText(userData.getCountry());
        }
    }

    public void error(View view) {
        startActivity(new Intent(this, CreatingAccountErrorActvity.class));

    }

    public void home(View view) {
        startActivity(new Intent(this, MainActivity.class));

    }

    public void update(View view) {
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

        dbHelper.updateUserData(
                etName.getText().toString(),
                etLastName.getText().toString(),
                etDNI.getText().toString(),
                etEmail.getText().toString(),
                etPhoneNumber.getText().toString(),
                etDOB.getText().toString(),
                etPassword.getText().toString(),
                etStreet.getText().toString(),
                etStreetNumber.getText().toString(),
                etFloor.getText().toString(),
                etPostalCode.getText().toString(),
                etCity.getText().toString(),
                etRegion.getText().toString(),
                etCountry.getText().toString()
        );
        finish();
    }
}