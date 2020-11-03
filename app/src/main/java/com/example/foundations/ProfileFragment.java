package com.example.foundations;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends DialogFragment{
    TextView fName,lName,lnumber, companyname, Email, phone, edit_Email;
    Button btn_confirm, btn_edit;
    ImageView profile_pic;

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fName = (TextView)view.findViewById(R.id.firstnameFrag);
        lName = (TextView)view.findViewById(R.id.lastnameFrag);
        lnumber = (TextView)view.findViewById(R.id.licenseNumberFrag);
        companyname = (TextView)view.findViewById(R.id.companyFrag);
        Email = (TextView)view.findViewById(R.id.emailFrag);
        phone = (TextView)view.findViewById(R.id.phoneFrag);
        btn_confirm =(Button)view.findViewById(R.id.confirm);
        btn_edit = (Button)view.findViewById(R.id.Profile_edit);
        edit_Email =(TextView)view.findViewById(R.id.editTextTextPersonName6);

        AppActivity activity =(AppActivity) getActivity();
        fName.setText(activity.fName);
        lName.setText(activity.lName);
        lnumber.setText(activity.License);
        companyname.setText(activity.Company);
        Email.setText(activity.email);
        edit_Email.setText(activity.email);
        phone.setText(activity.phone);



        return view;
    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Fragmentstyle);
    }
}