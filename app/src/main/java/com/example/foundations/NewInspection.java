package com.example.foundations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class NewInspection extends Fragment {

    public NewInspection() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.start_inspection, container, false);
        return view;


    }
}