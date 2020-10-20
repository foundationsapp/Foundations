package com.example.foundations;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    LiveData<List<Profile>> allProfiles;
    private FoundationsRepository foundationsRepository;

    public MainViewModel(Application application) {
        super(application);
        foundationsRepository = new FoundationsRepository(application);
        allProfiles = foundationsRepository.getAllProfiles();
    }

    public void insertProfile(Profile profile) {foundationsRepository.insertProfile(profile);}
    public LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }

}
