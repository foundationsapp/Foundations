package com.example.foundations;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class FoundationsRepository {

    private static final String TAG = FoundationsRepository.class.getSimpleName();
    private FoundationsDao foundationsDao;
    private LiveData<List<Profile>> allProfiles;

    FoundationsRepository(Application application) {
        FoundationsRoomDatabase db = FoundationsRoomDatabase.getDatabase(application);
        foundationsDao = db.foundationsDao();
        allProfiles = foundationsDao.getAllProfiles();
    }

    LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }

    void insertProfile(final Profile profile) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertProfile(profile);
        });
    }
}
