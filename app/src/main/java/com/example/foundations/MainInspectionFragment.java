package com.example.foundations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class MainInspectionFragment extends Fragment {

    private FragmentSwitcher fragmentSwitcher;

    public MainInspectionFragment(FragmentSwitcher fragmentSwitcher) {
        this.fragmentSwitcher = fragmentSwitcher;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_inspection, container, false);

        return view;
    }
}
