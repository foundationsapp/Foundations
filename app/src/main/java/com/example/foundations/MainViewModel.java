package com.example.foundations;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    List<Profile> allProfiles;
    private FoundationsRepository foundationsRepository;

    public MainViewModel(Application application) {
        super(application);
        foundationsRepository = new FoundationsRepository(application);
        allProfiles = foundationsRepository.getAllProfiles();
    }

    public void insertProfile(Profile profile) {foundationsRepository.insertProfile(profile);}

}
