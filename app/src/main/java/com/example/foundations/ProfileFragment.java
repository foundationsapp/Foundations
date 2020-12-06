package com.example.foundations;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static android.content.Intent.getIntent;

public class ProfileFragment extends DialogFragment{
    EditText fName,lName,lnumber, companyname, Email, phone;
    String firstName,lastName, license, email, phoneN, company;
    Button btn_edit;
    ImageView profile_pic, editcamera;
    String pic_path;
    Uri contentUri;
    FragmentSwitcher fragmentSwitcher;
    public ProfileFragment(FragmentSwitcher fragmentSwitcher) {
        this.fragmentSwitcher = fragmentSwitcher;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        AppActivity activity =(AppActivity) getActivity();
        Profile currentProfile = fragmentSwitcher.getProfile();

        fName = (EditText) view.findViewById(R.id.firstnameFrag);
        lName = (EditText) view.findViewById(R.id.lastnameFrag);
        lnumber = (EditText) view.findViewById(R.id.licenseNumberFrag);
        companyname = (EditText) view.findViewById(R.id.companyFrag);
        Email = (EditText) view.findViewById(R.id.emailFrag);
        phone = (EditText) view.findViewById(R.id.phoneFrag);

        btn_edit = (Button)view.findViewById(R.id.Profile_edit);

        profile_pic =(ImageView)view.findViewById(R.id.profile_pic);

        editcamera = (ImageView)view.findViewById(R.id.ecamera);

        fName.setText(currentProfile.getFirstName());
        lName.setText(currentProfile.getLastName());
        lnumber.setText(currentProfile.getLicenseNumber());
        companyname.setText(currentProfile.getCompanyName());
        Email.setText(currentProfile.getEmail());
        phone.setText(currentProfile.getPhone());
        String profile_pic_path = (currentProfile.getPhoto());
        if (profile_pic_path != null) {
            File file = new File(profile_pic_path);
            Picasso.get().load(file).into(profile_pic);
        }

        editcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String file_name = System.currentTimeMillis() +".jpg";
                File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                pic_path = storageDir.getAbsolutePath() + file_name;
                File file = new File(pic_path);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    contentUri = FileProvider.getUriForFile(activity, "com.example.Foundations.file_provider",file);
                } else {
                    contentUri = Uri.fromFile(file);
                }

                intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                startActivityForResult(intent,1);
            }
        });


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()){
                    firstName = fName.getText().toString();
                    lastName = lName.getText().toString();
                    license = lnumber.getText().toString();
                    email = Email.getText().toString();
                    phoneN = phone.getText().toString();
                    company = companyname.getText().toString();
                    if (!lastName.equals(currentProfile.getLastName())){
                        //System.out.println(activity.lName);
                        //System.out.println("new last name :" + lastName);
                        currentProfile.setLastName(lastName);
                    }
                    if(!firstName.equals(currentProfile.getFirstName())){
                        currentProfile.setFirstName(firstName);
                    }
                    if(!license.equals(currentProfile.getLicenseNumber())){
                        currentProfile.setLicenseNumber(license);
                    }
                    if(!email.equals(currentProfile.getEmail())){
                        currentProfile.setEmail(email);
                    }
                    if(!company.equals(currentProfile.getCompanyName())){
                        currentProfile.setCompanyName(company);
                    }
                    if(!phoneN.equals(currentProfile.getPhone())){
                        currentProfile.setPhone(phoneN);
                    }
                    fragmentSwitcher.updateCurrentProfile(currentProfile);
                    startActivity(activity.getIntent());
                }


            }
        });



        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AppActivity activity = (AppActivity) getActivity();
        Profile currentProfile = fragmentSwitcher.getProfile();
        if (resultCode == RESULT_OK) {
            if (!pic_path.equals(currentProfile.getPhoto())) {
                currentProfile.setPhoto(pic_path);
                String profile_pic_path = (currentProfile.getPhoto());
                if (profile_pic_path != null) {
                    File file = new File(profile_pic_path);
                    Picasso.get().load(file).into(profile_pic);
                }
            }
            fragmentSwitcher.updateCurrentProfile(currentProfile);
            startActivity(activity.getIntent());
        }
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
    public Bitmap resizeBitmap(int targetWidth, Bitmap source) {
        double ratio = (double) targetWidth / (double) source.getWidth();
        int targetHeight = (int) (source.getHeight() * ratio);
        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);

        if (result != source) {
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
    public boolean check() {
        boolean f = true;
        if (fName.getText().toString().isEmpty() || lName.getText().toString().isEmpty() ||
                lnumber.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || phone.getText().toString().isEmpty()) {
            f = false;
            // if any field empty boolean return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches()){
            Email.setError("Please Enter Valid Mail");
            f = false;
        }
        if (!fName.getText().toString().matches("[a-zA-Z]+")){
            fName.setError("Please Enter only in text for first name");
            f = false;
        }
        if(!lName.getText().toString().matches("[a-zA-Z]+")){
            lName.setError("Please Enter only in text for first name");
            f = false;
        }

        if (!phone.getText().toString().replaceAll(" ","").replaceAll("[-()]","").matches("[0-9]{10}")){
            phone.setError("Please Enter Only 10 Digit phone number");
            f = false;
        }
        return f;
    }






    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Fragmentstyle);
    }
}