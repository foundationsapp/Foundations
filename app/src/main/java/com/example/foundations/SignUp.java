package com.example.foundations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText editFirstName;
    EditText editLastName;
    EditText editLicenseNumber;
    EditText editCompanyName;
    EditText editAddress;
    EditText editPhone;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        editFirstName = findViewById(R.id.edit_first_name);
        editLastName = findViewById(R.id.edit_last_name);
        editLicenseNumber =findViewById(R.id.edit_licence_number);
        editCompanyName = findViewById(R.id.edit_company_name);
        editAddress = findViewById(R.id.edit_address);
        editPhone = findViewById(R.id.edit_phone);
        signUpButton = findViewById(R.id.get_started_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    Intent intent = new Intent(SignUp.this, AppActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignUp.this, R.string.empty_field, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean check() {
        boolean f;
        if (editFirstName.getText().toString().isEmpty() || editLastName.getText().toString().isEmpty() ||
                editLicenseNumber.getText().toString().isEmpty() || editCompanyName.getText().toString().isEmpty() ||
                editAddress.getText().toString().isEmpty() || editPhone.getText().toString().isEmpty()) {
            f = false;
        } else {
            f = true;
        }
        return f;
    }

}
