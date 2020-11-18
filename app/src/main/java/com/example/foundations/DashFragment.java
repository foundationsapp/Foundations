package com.example.foundations;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DashFragment extends Fragment implements SetReportHandler{

    private Report currentReport;

    private Profile currentProfile;
    private FragmentSwitcher fragmentSwitcher;

    public DashFragment(FragmentSwitcher fragmentSwitcher) {
        this.fragmentSwitcher=fragmentSwitcher;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash, container, false);



        Button inspection_frag_new_inspection=(Button) view.findViewById(R.id.inspection_frag_new_inspection);
        inspection_frag_new_inspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Fragment fragment=new NewInspection(fragmentSwitcher);
               fragmentSwitcher.loadFragment(fragment);


            }
        });



      
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
