package com.example.foundations;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class NewInspection extends Fragment {

    private static final String TAG = NewInspection.class.getSimpleName();
    private EditText niBuyerFirstName;
    private EditText niBuyerLastName;
    private EditText niSellerFirstName;
    private EditText niSellerLastName;
    private EditText niAddress;
    private EditText niCity;
    private EditText niState;
    private EditText niZip;
    private Button createButton;
    private Button addPhoto;
    private ImageView propertyPhoto;
    private MainViewModel mainViewModel;
    private Report currentReport;
    private boolean updateReport;
    private FragmentSwitcher fragmentSwitcher;
    private String photoPath = null;
    private Uri photoUri;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public NewInspection(FragmentSwitcher fragmentSwitcher) { this.fragmentSwitcher = fragmentSwitcher; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        updateReport = false;

        View view = inflater.inflate(R.layout.start_inspection, container, false);

        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);

        niBuyerFirstName = view.findViewById(R.id.ni_buyer_first_name);
        niBuyerLastName = view.findViewById(R.id.ni_buyer_last_name);
        niSellerFirstName = view.findViewById(R.id.ni_seller_first_name);
        niSellerLastName = view.findViewById(R.id.ni_seller_last_name);
        niAddress = view.findViewById(R.id.ni_address);
        niCity = view.findViewById(R.id.ni_city);
        niState = view.findViewById(R.id.ni_state);
        niZip = view.findViewById(R.id.ni_zip);
        createButton = view.findViewById(R.id.ni_create_report);
        addPhoto = view.findViewById(R.id.add_property_photo);
        propertyPhoto = view.findViewById(R.id.property_photo_image);

        addPhoto.setOnClickListener(v -> {
            takePicture();
        });
        createButton.setOnClickListener(v -> { createReport(); });

        if (fragmentSwitcher.getCurrentReport() != null) {
            currentReport = fragmentSwitcher.getCurrentReport();
            niBuyerFirstName.setText(currentReport.getBuyerFirstName());
            niBuyerLastName.setText(currentReport.getBuyerLastName());
            niSellerFirstName.setText(currentReport.getSellerFirstName());
            niSellerLastName.setText(currentReport.getSellerLastName());
            niAddress.setText(currentReport.getStreet());
            niState.setText(currentReport.getState());
            niCity.setText(currentReport.getCity());
            niZip.setText(currentReport.getZip());
            if (currentReport.getPropertyPhoto() != null) {
                photoPath = currentReport.getPropertyPhoto();
                File photoFile = new File(photoPath);
                photoUri = FileProvider.getUriForFile(getActivity(), "com.example.Foundations.file_provider", photoFile);
                ListItemDialogFragment.bitmapSetup(propertyPhoto, photoUri);
            }
            updateReport = true;
        }


        return view;
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String file_name = System.currentTimeMillis() + ".jpg";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        photoPath = storageDir.getAbsolutePath() + file_name;
        File photoFile = new File(photoPath);
        photoUri = FileProvider.getUriForFile(getActivity(), "com.example.Foundations.file_provider", photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            ListItemDialogFragment.bitmapSetup(propertyPhoto, photoUri);
        }
    }

    public void createReport() {
        String buyerFirstName = niBuyerFirstName.getText().toString();
        String buyerLastName = niBuyerLastName.getText().toString();
        String sellerFirstName = niSellerFirstName.getText().toString();
        String sellerLastName = niSellerLastName.getText().toString();
        String address = niAddress.getText().toString();
        String city = niCity.getText().toString();
        String state = niState.getText().toString();
        String zip = niZip.getText().toString();

        Report report = new Report(fragmentSwitcher.getProfile().getProfileId(), buyerFirstName, buyerLastName, sellerFirstName, sellerLastName, address, city, state, zip, photoPath);

        if (updateReport) {
            mainViewModel.updateReport(currentReport.getReportId(), buyerFirstName, buyerLastName, sellerFirstName, sellerLastName, address, city, state, zip, photoPath);
        } else {
            mainViewModel.insertReport(report);
        }
        Fragment fragment = new SiteDetailsFragment(fragmentSwitcher);
        fragmentSwitcher.loadFragment(fragment);


    }
}