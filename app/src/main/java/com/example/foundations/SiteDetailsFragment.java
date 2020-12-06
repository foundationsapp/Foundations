package com.example.foundations;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button submit;


    private static final String TAG = SiteDetailsFragment.class.getSimpleName();
    public SiteDetailsFragment(FragmentSwitcher fragmentSwitcher) {
        this.fragmentSwitcher = fragmentSwitcher;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sitedetails, container, false);
        MainViewModel mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        bedrooms = (EditText) view.findViewById(R.id.sd_bedrooms_amt);
        bathrooms = (EditText) view.findViewById(R.id.sd_bathrooms_amt);
        stories = (EditText) view.findViewById(R.id.sd_stories_amt);
        squareFeet = (EditText) view.findViewById(R.id.sd_square_feet_amt);
        inspectionFee = (EditText) view.findViewById(R.id.sd_inspection_fee_amt);
        yearBuilt = (EditText) view.findViewById(R.id.sd_year_built_amt);
        furnished =(EditText)  view.findViewById(R.id.sd_furnished_amt);
        present = (EditText)  view.findViewById(R.id.sd_present_amt);
        orientation = (EditText) view.findViewById(R.id.sd_orientation_amt);
        submit = (Button) view.findViewById(R.id.sd_submit);
        submit.setOnClickListener(v -> {
            checkInputs();
            mainViewModel.updateSiteDetails(currentReportId,
                    Double.parseDouble(bathrooms.getText().toString()),
                    Integer.parseInt(bedrooms.getText().toString()),
                    Integer.parseInt(stories.getText().toString()),
                    Double.parseDouble(inspectionFee.getText().toString()),
                    Integer.parseInt(yearBuilt.getText().toString()),
                    furnished.getText().toString(),
                    present.getText().toString(),
                    orientation.getText().toString()
                    );
            Fragment fragment = new MainInspectionFragment(fragmentSwitcher, currentReportId);
            fragmentSwitcher.loadFragment(fragment);
        });

        if (fragmentSwitcher.getCurrentReport() != null) {
            Log.d(TAG, "onCreateView: loading siteDetails");
            currentReportId = fragmentSwitcher.getCurrentReport().getReportId();
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
            siteDetails = new SiteDetails(currentReportId, 0, 0.0, 0, 0, 0.0, 0, "N/A", "N/A", "N/A");
            mainViewModel.insertSiteDetails(siteDetails);
        }
        return view;
    };

    void checkInputs() {
        if (bathrooms.getText().toString().equals("")) {
            bathrooms.setText("0");
        }
        if (bedrooms.getText().toString().equals("")) {
            bedrooms.setText("0");
        }
        if (stories.getText().toString().equals("")) {
            stories.setText("0");
        }
        if (inspectionFee.getText().toString().equals("")) {
            inspectionFee.setText("0");
        }
        if (yearBuilt.getText().toString().equals("")) {
            yearBuilt.setText("0");
        }
    }
};
