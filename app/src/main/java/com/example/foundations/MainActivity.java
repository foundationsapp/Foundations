package com.example.foundations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SetProfileHandler {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Profile currentProfile;

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
        if (currentProfile != null) {
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            intent.putExtra(String.valueOf(R.string.userProfile), this.currentProfile);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.please_select_insp), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }
}