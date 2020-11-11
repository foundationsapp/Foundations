package com.example.foundations;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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
    private SiteDetails siteDetails;


    private static final String TAG = SiteDetailsFragment.class.getSimpleName();
    public SiteDetailsFragment(FragmentSwitcher fragmentSwitcher) {
        this.fragmentSwitcher = fragmentSwitcher;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sitedetails, container, false);

        bedrooms = (EditText) view.findViewById(R.id.sd_bedrooms_amt);
        bathrooms = (EditText) view.findViewById(R.id.sd_bathrooms_amt);
        stories = (EditText) view.findViewById(R.id.sd_stories_amt);
        squareFeet = (EditText) view.findViewById(R.id.sd_square_feet_amt);
        inspectionFee = (EditText) view.findViewById(R.id.sd_inspection_fee_amt);
        yearBuilt = (EditText) view.findViewById(R.id.sd_year_built_amt);
        furnished =(EditText)  view.findViewById(R.id.sd_furnished_amt);
        present = (EditText)  view.findViewById(R.id.sd_present_amt);
        orientation = (EditText) view.findViewById(R.id.sd_orientation_amt);
        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        if (getArguments() != null) {
            currentReportId = getArguments().getInt(getString(R.string.current_report_key));
            siteDetails = mainViewModel.getSiteDetails(currentReportId).get(0);
            bedrooms.setText(Integer.toString(siteDetails.getBedrooms()), TextView.BufferType.EDITABLE);
            bathrooms.setText(Double.toString(siteDetails.getBathrooms()), TextView.BufferType.EDITABLE);
            stories.setText(Integer.toString(siteDetails.getStories()), TextView.BufferType.EDITABLE);
            squareFeet.setText(Integer.toString(siteDetails.getStories()), TextView.BufferType.EDITABLE);
            inspectionFee.setText(Double.toString(siteDetails.getInspectionFee()), TextView.BufferType.EDITABLE);
            yearBuilt.setText(Integer.toString(siteDetails.getYearBuilt()), TextView.BufferType.EDITABLE);
            furnished.setText(siteDetails.getFurnished(), TextView.BufferType.EDITABLE);
            present.setText(siteDetails.getPresentAtInspection(), TextView.BufferType.EDITABLE);
            orientation.setText(siteDetails.getOrientation(), TextView.BufferType.EDITABLE);
        } else {
            currentReportId = mainViewModel.getNewReport().getReportId();
            siteDetails = mainViewModel.getSiteDetails(1).get(0);
        }

        currentReportId = mainViewModel.getNewReport().getReportId();
        siteDetails = mainViewModel.getSiteDetails(1).get(0);

        Log.d(TAG, "onCreateView: " + siteDetails.getBathrooms() + " " + siteDetails.getPresentAtInspection() + " " + currentReportId);




        return view;
    };
};
