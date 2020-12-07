package com.example.foundations;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class InspectionFragment extends Fragment implements SetReportHandler {

    private static final String TAG = InspectionFragment.class.getSimpleName();
    FragmentSwitcher fragmentSwitcher;
    Report currentReport;

    public InspectionFragment(FragmentSwitcher fragmentSwitcher) {
        this.fragmentSwitcher = fragmentSwitcher;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentSwitcher.setCurrentReport(null);
        View view = inflater.inflate(R.layout.fragment_inspection, container, false);


        RecyclerView reportRecyclerView = view.findViewById(R.id.all_inspections_recyclerview);
        final ReportAdapter reportAdapter = new ReportAdapter(reportRecyclerView.getContext(), this);
        reportRecyclerView.setAdapter(reportAdapter);
        reportRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        mainViewModel.getAllReports().observe(getViewLifecycleOwner(), reportAdapter::setReports);

        Button newInspection = view.findViewById(R.id.inspection_frag_new_inspection);
        newInspection.setOnClickListener(v -> {
            Fragment fragment = new NewInspection(fragmentSwitcher);
            fragmentSwitcher.loadFragment(fragment);
        });
        Button selectInspection = view.findViewById(R.id.select_inspection);
        selectInspection.setOnClickListener(v -> {
            if (currentReport != null) {
                Fragment fragment = new NewInspection(fragmentSwitcher);
                fragmentSwitcher.setCurrentReport(currentReport);
                fragmentSwitcher.loadFragment(fragment);
            } else {
                Toast.makeText(getContext(), R.string.select_insp, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void setCurrentReport(Report currentReport) {
        this.currentReport = currentReport;
    }
}