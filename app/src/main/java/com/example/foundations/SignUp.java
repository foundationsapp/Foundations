package com.example.foundations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.util.Patterns.EMAIL_ADDRESS;

public class SignUp extends AppCompatActivity {

    EditText editFirstName;
    EditText editLastName;
    EditText editLicenseNumber;
    EditText editCompanyName;
    EditText editEmail;
    EditText editPhone;
    Button signUpButton;
    MainViewModel mainViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);

        editFirstName = findViewById(R.id.edit_first_name);
        editLastName = findViewById(R.id.edit_last_name);
        editLicenseNumber = findViewById(R.id.edit_license_number);
        editCompanyName = findViewById(R.id.edit_company_name);
        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        signUpButton = findViewById(R.id.get_started_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    String firstName = editFirstName.getText().toString();
                    String lastName = editLastName.getText().toString();
                    String license = editLicenseNumber.getText().toString();
                    String email = editEmail.getText().toString();
                    String phone = editPhone.getText().toString();
                    String company = editCompanyName.getText().toString();
                    Profile newProfile = new Profile(firstName, lastName, license, email, phone, company);
                    mainViewModel.insertProfile(newProfile);
                    Intent intent = new Intent(SignUp.this, AppActivity.class);
                    intent.putExtra(String.valueOf(R.string.userProfile), newProfile);
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
                editEmail.getText().toString().isEmpty() || editPhone.getText().toString().isEmpty()) {
            f = false;
            // if any field empty boolean return false
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(editEmail.getText().toString()).matches()){
            editEmail.setError("Please Enter Valid Mail");
            f = false;
        }
        if (!editFirstName.getText().toString().matches("[a-zA-Z]+")){
            editFirstName.setError("Please Enter only in text for first name");
            f = false;
        }
        if(!editLastName.getText().toString().matches("[a-zA-Z]+")){
            editLastName.setError("Please Enter only in text for first name");
            f = false;
        }

        if (!editPhone.getText().toString().replaceAll(" ","").replaceAll("[-()]","").matches("[0-9]{10}")){
            editPhone.setError("Please Enter Only 10 Digit phone number");
            f = false;
        }
        else {
            f = true;
        }
        return f;
    }


}
