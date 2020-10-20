package com.example.foundations;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class AppActivity extends AppCompatActivity {

    private final static String TAG = AppActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_page);
        Intent intent = getIntent();
        Profile currentProfile = intent.getParcelableExtra("userProfile");

    }

}
