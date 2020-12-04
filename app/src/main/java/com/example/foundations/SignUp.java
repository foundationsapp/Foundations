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
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
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
    String firstName,lastName, license, email, phone, company;
    String photo = null;
    String dir_path;
    String pic_path;
    Uri contentUri;
    ImageView profileimage;
    private static final String TAG = "SignUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);

        profileimage = (ImageView)findViewById(R.id.signupimage);
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
                    firstName = editFirstName.getText().toString();
                    lastName = editLastName.getText().toString();
                    license = editLicenseNumber.getText().toString();
                    email = editEmail.getText().toString();
                    phone = editPhone.getText().toString();
                    company = editCompanyName.getText().toString();
                    photo = pic_path;
                    Log.d(TAG, "onClick: " + photo);
                    Profile newProfile = new Profile(firstName, lastName, license, email, phone, company, photo);
                    mainViewModel.insertProfile(newProfile);
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(SignUp.this, R.string.empty_field, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void startCamera(View view){

     Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

     String file_name = System.currentTimeMillis() +".jpg";


     pic_path = "/sdcard/DCIM/Camera" + file_name;

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
            float degree = getDegree();

            Bitmap bitmap2  = resizeBitmap(200,bitmap);

            Bitmap bitmap3 = rotateBitmap(bitmap2, degree);
            profileimage.setImageBitmap(bitmap3);

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

    public Bitmap rotateBitmap(Bitmap bitmap, float degree){
        try{
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();

            Matrix matrix = new Matrix();

            matrix.postRotate(degree);
            Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0,width, height, matrix, true);

            bitmap.recycle();
            return resizeBitmap;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Bitmap resizeBitmap(int targetWidth, Bitmap source){
        double ratio = (double)targetWidth/(double)source.getWidth();
        int targetHeight = (int)(source.getHeight()*ratio);
        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);

        if (result != source){
            source.recycle();
        }
        return result;
    }

    public float getDegree(){
        try{
            ExifInterface exif = new ExifInterface(contentUri.getPath());
            int degree = 0;

            int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            switch (ori){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;

            }
            return (float)degree;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }


}
