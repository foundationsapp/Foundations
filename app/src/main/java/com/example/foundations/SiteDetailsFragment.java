package com.example.foundations;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

public class SiteDetailsFragment extends Fragment {

    private FragmentSwitcher fragmentSwitcher;
    private static final String TAG = SiteDetailsFragment.class.getSimpleName();
    public SiteDetailsFragment(FragmentSwitcher fragmentSwitcher) {
        this.fragmentSwitcher = fragmentSwitcher;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sitedetails, container, false);
        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        Report currentReport = mainViewModel.getNewReport();
        Log.d(TAG, "onCreateView: " + currentReport.getReportId() + " " + currentReport.getBuyerFirstName());


        return view;
    };
};
