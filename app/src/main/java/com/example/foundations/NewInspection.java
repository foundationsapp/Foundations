package com.example.foundations;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class NewInspection extends Fragment {
    Button toSummaryPage;

    public NewInspection() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_inspection, container, false);
        //toSummaryPage = view.findViewById(R.id.ni_create_report);
        //toSummaryPage.setOnClickListener(v -> {
        //    Intent intent = new Intent(getActivity(), SummaryActivity.class);
        //    startActivity(intent);
        // });
        return view;
    }
}
