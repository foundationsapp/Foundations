package com.example.foundations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SetProfileHandler {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Profile currentProfile;
    //ProfileFragment profileFragment = new ProfileFragment();
    String lName, fName, License, Company, email, phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView profileRecyclerView = findViewById(R.id.profile_recyclerview);
        final ProfileAdapter profileAdapter = new ProfileAdapter(this, this);
        profileRecyclerView.setAdapter(profileAdapter);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        mainViewModel.getAllProfiles().observe(this, profileAdapter::setProfiles);


    }

    public void goToSignUp(View view){
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }

    public void goToDash(View view) {
        Intent intent = new Intent(MainActivity.this, AppActivity.class);
        intent.putExtra(String.valueOf(R.string.userProfile), this.currentProfile);
        startActivity(intent);
    }

    @Override
    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }
}