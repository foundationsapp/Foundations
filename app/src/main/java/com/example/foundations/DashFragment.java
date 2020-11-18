package com.example.foundations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DashFragment extends Fragment implements SetReportHandler{

    private Report currentReport;

    public DashFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash, container, false);
        RecyclerView reportRecyclerView = view.findViewById(R.id.report_recyclerview);
        final ReportAdapter reportAdapter = new ReportAdapter(reportRecyclerView.getContext(), this);
        reportRecyclerView.setAdapter(reportAdapter);
        reportRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        mainViewModel.getAllReports().observe(getViewLifecycleOwner(), reportAdapter::setReports);
        return view;
    }

    @Override
    public void setCurrentReport(Report currentReport) {
        this.currentReport = currentReport;
    }
}
