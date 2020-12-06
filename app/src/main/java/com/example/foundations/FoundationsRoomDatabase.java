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
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                FoundationsDao foundationsDao = INSTANCE.foundationsDao();
                Category category = new Category("Exterior");
                foundationsDao.insertCategory(category);
                SubCategory subCategory = new SubCategory(1, "Doors");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(1, "Roofing");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(1, "Drainage");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(1, "Garage");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(1, "Wall Types");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(1, "Structures");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(1, "Foundation");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(1, "Eaves");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(1, "Porch");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(1, "Balcony");
                foundationsDao.insertSubCategory(subCategory);
                category = new Category("Electrical");
                foundationsDao.insertCategory(category);
                subCategory = new SubCategory(2, "Smoke Alarm");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(2, "Carbon Monoxide Alarm");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(2, "Breaker Box");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(2, "Wiring");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(2, "Lighting");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(2, "Ceiling Fans");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(2, "Exhaust Fans");
                foundationsDao.insertSubCategory(subCategory);
                category = new Category("HVAC");
                foundationsDao.insertCategory(category);
                subCategory = new SubCategory(3, "Heating");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(3, "Thermostat");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(3, "AC");
                foundationsDao.insertSubCategory(subCategory);
                category = new Category("Appliances");
                foundationsDao.insertCategory(category);
                subCategory = new SubCategory(4, "Washer/Dryer");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(4, "Range/Stove");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(4, "Range Hood");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(4, "Refrigerator");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(4, "Oven");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(4, "Microwave");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(4, "Dishwasher");
                foundationsDao.insertSubCategory(subCategory);
                category = new Category("Grounds");
                foundationsDao.insertCategory(category);
                subCategory = new SubCategory(5, "Landscaping");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(5, "Driveway");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(5, "Patios");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(5, "Shed");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(5, "Sidewalks");
                foundationsDao.insertSubCategory(subCategory);
                category = new Category("Plumbing");
                foundationsDao.insertCategory(category);
                subCategory = new SubCategory(6, "Piping");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(6, "Tubs");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(6, "Showers");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(6, "Water Heater");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(6, "Fixtures/Faucets");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(6, "Sinks");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(6, "Toilets");
                foundationsDao.insertSubCategory(subCategory);
                category = new Category("Interior");
                foundationsDao.insertCategory(category);
                subCategory = new SubCategory(7, "Flooring");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(7, "Windows");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(7, "Doors");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(7, "Stairs");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(7, "Railing");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(7, "Cabinets");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(7, "Ceilings");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(7, "Attic");
                foundationsDao.insertSubCategory(subCategory);
                subCategory = new SubCategory(7, "Insulation");
                foundationsDao.insertSubCategory(subCategory);

            });
        }
    };
}
