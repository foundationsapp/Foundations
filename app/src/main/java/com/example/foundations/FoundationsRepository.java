package com.example.foundations;

import android.app.Application;

import java.util.List;

public class FoundationsRepository {

    private static final String TAG = FoundationsRepository.class.getSimpleName();
    private FoundationsDao foundationsDao;

    FoundationsRepository(Application application) {
        FoundationsRoomDatabase db = FoundationsRoomDatabase.getDatabase(application);
        foundationsDao = db.foundationsDao();
    }

    List<Profile> getAllProfiles() {
        return foundationsDao.getAllProfiles();
    }

    void insertProfile(final Profile profile) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertProfile(profile);
        });
    }
}
