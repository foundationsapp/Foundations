package com.example.foundations;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class InspectionFragment extends Fragment {

    FragmentSwitcher fragmentSwitcher;

    public InspectionFragment(FragmentSwitcher fragmentSwitcher) {
        this.fragmentSwitcher = fragmentSwitcher;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspection, container, false);
        RecyclerView reportRecyclerView = view.findViewById(R.id.all_inspections_recyclerview);
        final ReportAdapter reportAdapter = new ReportAdapter(reportRecyclerView.getContext());
        reportRecyclerView.setAdapter(reportAdapter);
        reportRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        mainViewModel.getAllReports().observe(getViewLifecycleOwner(), reportAdapter::setReports);
        Button newInspection = view.findViewById(R.id.inspection_frag_new_inspection);
        newInspection.setOnClickListener(v -> {
            Fragment fragment = new NewInspection();
            fragmentSwitcher.loadFragment(fragment);
        });
        return view;
    }

}