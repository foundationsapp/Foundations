package com.example.foundations;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Profile.class, Buyer.class, Category.class, ListItem.class, Note.class, Photo.class, Report.class, Seller.class, SiteDetails.class, SubCategory.class}, version = 1, exportSchema = false)
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
                foundationsDao.deleteBuyers();
                foundationsDao.deleteSellers();
                foundationsDao.deleteSiteDetails();
                foundationsDao.deleteReports();
                foundationsDao.deleteListItems();
                foundationsDao.deleteNotes();
                foundationsDao.deleteCategory();
                foundationsDao.deleteSubcategory();

                // future category/subcat base setup. It will check if there are any and then populate if not.
                Category category = new Category("Exterior");
                foundationsDao.insertCategory(category);
                category = new Category("interior");
                foundationsDao.insertCategory(category);
                SubCategory subCategory = new SubCategory(1, "Porch");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(2, "Kitchen");
                foundationsDao.insertSubCategory(subCategory);

                Profile profile = new Profile("Kevin", "Pettinger", "ahd7sha", "kevin@gmail.com", "2063334455", null);
                foundationsDao.insertProfile(profile);
                profile = new Profile("Natnael", "Bekele", "dk8d9s", "nate@gmail.com", "2223331100", "Builders" );
                foundationsDao.insertProfile(profile);
                profile = new Profile("Joette", "Damo", "dkd980s", "joette@gmail.com", "2034488999", null);
                foundationsDao.insertProfile(profile);
                profile = new Profile("Jin", "Choi", "21k3dd", "jin@gmail.com", "4253490999", "Delta");
                foundationsDao.insertProfile(profile);
                profile = new Profile("Jin", "Choi", "21k3dd", "jin@gmail.com", "4253490999", "Delta");
                foundationsDao.insertProfile(profile);
                Buyer buyer  = new Buyer("Steve", "Buyer", "steve@buyer.com", "2061112233");
                foundationsDao.insertBuyer(buyer);
                Seller seller = new Seller("Ben", "Gold", "ben@seller.com", "2064438888");
                foundationsDao.insertSeller(seller);
                Report report = new Report(1, 1, 1, "123 Fake St.", "Seattle", "WA", 98011);
                foundationsDao.insertReport(report);
                report = new Report(1, 1, 1, "456 Fairy Tale Ln.", "Houston", "TX", 77066);
                foundationsDao.insertReport(report);
                report = new Report(1, 1, 1, "111 Airport Rd", "Renton", "WA", 99455);
                foundationsDao.insertReport(report);
                report = new Report(1, 1, 1, "777 Ocean Ave.", "Edmonds", "WA", 99887);
                foundationsDao.insertReport(report);
                SiteDetails siteDetails = new SiteDetails(1, 2, 2.5, 2, 2000, 100.4, 2000, "no", "buyer", "North");
                foundationsDao.insertSiteDetails(siteDetails);
                ListItem listItem = new ListItem(1, 1, 1, true, false);
                foundationsDao.insertListItem(listItem);
                listItem = new ListItem(1, 2, 2, true, false);
                foundationsDao.insertListItem(listItem);
                Note note = new Note(1, "it's a great porch", "good porch", 1);
                foundationsDao.insertNote(note);
                note = new Note(2, "sweet kitchen", "The KITCHEN", 1);
                foundationsDao.insertNote(note);


            });
        }
    };
}
