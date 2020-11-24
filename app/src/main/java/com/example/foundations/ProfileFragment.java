package com.example.foundations;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

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

import static android.content.ContentValues.TAG;
import static android.content.Intent.getIntent;

public class ProfileFragment extends DialogFragment{
    EditText fName,lName,lnumber, companyname, Email, phone;
    String firstName,lastName, license, email, phoneN, company;
    Button btn_confirm, btn_edit;
    ImageView profile_pic;
    Uri contentUri;
    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Profile currentProfile = intent.getParcelableExtra(String.valueOf(R.string.userProfile));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        AppActivity activity =(AppActivity) getActivity();
        activity.getResources();

        Profile currentProfile = activity.getIntent().getParcelableExtra(String.valueOf(R.string.userProfile));
        Log.d(TAG, "onCreate: " + currentProfile.getFullName());



        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        String profile_pic_path = (activity.photo);
        System.out.println(profile_pic_path);

        File file = new File(profile_pic_path);

        contentUri = Uri.fromFile(file);
        Bitmap bitmap = BitmapFactory.decodeFile(contentUri.getPath());

        fName = (EditText) view.findViewById(R.id.firstnameFrag);
        lName = (EditText) view.findViewById(R.id.lastnameFrag);
        lnumber = (EditText) view.findViewById(R.id.licenseNumberFrag);
        companyname = (EditText) view.findViewById(R.id.companyFrag);
        Email = (EditText) view.findViewById(R.id.emailFrag);
        phone = (EditText) view.findViewById(R.id.phoneFrag);
        btn_confirm =(Button)view.findViewById(R.id.confirm);
        btn_edit = (Button)view.findViewById(R.id.Profile_edit);

        profile_pic =(ImageView)view.findViewById(R.id.profile_pic);
        float degree = getDegree();

        //Bitmap bitmap2 = rotateBitmap(bitmap, degree);

        //profile_pic.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_account_circle_24));
        Picasso.get().load(file).into(profile_pic);

        fName.setText(activity.fName);
        lName.setText(activity.lName);
        lnumber.setText(activity.License);
        companyname.setText(activity.Company);
        Email.setText(activity.email);
        phone.setText(activity.phone);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
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
                    if (lastName != currentProfile.getLastName()){
                        //System.out.println(activity.lName);
                        //System.out.println("new last name :" + lastName);
                        currentProfile.setLastName(lastName);
                    }
                    if(firstName != currentProfile.getFirstName()){
                        currentProfile.setFirstName(firstName);
                    }
                    if(license != currentProfile.getLicenseNumber()){
                        currentProfile.setLicenseNumber(license);
                    }
                    if(email != currentProfile.getEmail()){
                        currentProfile.setEmail(email);
                    }
                    if(company != currentProfile.getCompanyName()){
                        currentProfile.setCompanyName(company);
                    }
                    if(phoneN != currentProfile.getPhone()){
                        currentProfile.setPhone(phoneN);
                    }

                }
                startActivity(activity.getIntent());

            }
        });



        return view;
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
        boolean f;
        if (fName.getText().toString().isEmpty() || lName.getText().toString().isEmpty() ||
                lnumber.getText().toString().isEmpty() || companyname.getText().toString().isEmpty() ||
                Email.getText().toString().isEmpty() || phone.getText().toString().isEmpty()) {
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
        else {
            f = true;
        }
        return f;
    }






    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Fragmentstyle);
    }

}