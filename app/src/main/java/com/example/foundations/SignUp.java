package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

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

    String [] permission_list = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    String dir_path;
    Uri contentUri;
    ImageView profileimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);

        profileimage = (ImageView)findViewById(R.id.signupimage);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permission_list,0);
        } else {
            init();
        }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int a1 : grantResults){
            if(a1 == PackageManager.PERMISSION_DENIED){
                return;
            }
        }
        init();
    }

    public void init(){
        String temp_path = getExternalFilesDir(null).getAbsolutePath();
        dir_path = temp_path + "/Android/data/" + getPackageName();
        File file = new File(dir_path);
        if(file.exists() == false){
            file.mkdir();
        }
    }
    public void startCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String file_name = System.currentTimeMillis() +".jpg";
        String pic_path = "/storage/emulated/0/DCIM/Camera" + file_name;

        File file = new File(pic_path);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            contentUri = FileProvider.getUriForFile(this, "com.example.Foundations.file_provider",file);
        } else {
            contentUri = Uri.fromFile(file);
        }

        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bitmap bitmap = BitmapFactory.decodeFile(contentUri.getPath());
            profileimage.setImageBitmap(bitmap);
        }
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
