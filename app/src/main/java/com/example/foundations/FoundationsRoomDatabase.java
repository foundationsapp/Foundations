package com.example.foundations;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Profile.class}, version = 1, exportSchema = false)
public abstract class FoundationsRoomDatabase extends RoomDatabase{

    public abstract FoundationsDao foundationsDao();

    private static final String TAG = FoundationsRoomDatabase.class.getSimpleName();
    private static volatile FoundationsRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static FoundationsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FoundationsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FoundationsRoomDatabase.class, "foundation_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                FoundationsDao foundationsDao = INSTANCE.foundationsDao();
                foundationsDao.deleteAllProfiles();
                Profile profile = new Profile(null,"Kevin", "Pettinger", "ahd7sha", "kevin@gmail.com", "2063334455", null);
                foundationsDao.insertProfile(profile);
                profile = new Profile(null,"Natnael", "Bekele", "dk8d9s", "nate@gmail.com", "2223331100", "Builders" );
                foundationsDao.insertProfile(profile);
                profile = new Profile(null,"Joette", "Damo", "dkd980s", "joette@gmail.com", "2034488999", null);
                foundationsDao.insertProfile(profile);
                profile = new Profile(null,"Jin", "Choi", "21k3dd", "jin@gmail.com", "4253490999", "Delta");
                foundationsDao.insertProfile(profile);

            });
        }
    };
}
