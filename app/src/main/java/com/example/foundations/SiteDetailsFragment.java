package com.example.foundations;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

public class SiteDetailsFragment extends Fragment {

    private FragmentSwitcher fragmentSwitcher;
    private int currentReportId;
    private EditText bedrooms;
    private EditText bathrooms;
    private EditText stories;
    private EditText squareFeet;
    private EditText inspectionFee;
    private EditText yearBuilt;
    private EditText furnished;
    private EditText present;
    private EditText orientation;


    private static final String TAG = SiteDetailsFragment.class.getSimpleName();
    public SiteDetailsFragment(FragmentSwitcher fragmentSwitcher) {
        this.fragmentSwitcher = fragmentSwitcher;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sitedetails, container, false);
        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
//        if (getArguments() != null) {
//            currentReportId = getArguments().getInt(getString(R.string.current_report_key));
//        } else {
//
//        }
        currentReportId = mainViewModel.getNewReport().getReportId();
        SiteDetails siteDetails = mainViewModel.getSiteDetails().get(0);

        Log.d(TAG, "onCreateView: " + siteDetails.getBathrooms() + " " + siteDetails.getPresentAtInspection() + " " + currentReportId);




        return view;
    };
};
