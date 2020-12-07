package com.example.foundations;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import java.util.List;

public class FoundationsRepository {

    private static final String TAG = FoundationsRepository.class.getSimpleName();
    private FoundationsDao foundationsDao;
    private LiveData<List<Profile>> allProfiles;
    private LiveData<List<Report>> allReports;
    private LiveData<List<Category>> allCategories;
    private LiveData<List<SubCategory>> allSubcategories;
    private LiveData<List<ListItem>> allListItems;
    private List<SiteDetails> allSiteDetails;
    private List<SiteDetails> currentSiteDetails;

    FoundationsRepository(Application application) {
        FoundationsRoomDatabase db = FoundationsRoomDatabase.getDatabase(application);
        foundationsDao = db.foundationsDao();
        allProfiles = foundationsDao.getAllProfiles();
        allReports = foundationsDao.getAllReports();
        allCategories = foundationsDao.getAllCategories();
        allSubcategories = foundationsDao.getAllSubcategories();
        allListItems = foundationsDao.getAllListItems();
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            allSiteDetails = foundationsDao.getAllSiteDetails();
        });
    }

    // INSERT QUERIES
    void insertProfile(final Profile profile) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertProfile(profile);
        });
    }

    void insertReport(final Report report) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertReport(report);
        });
    }

    void insertSiteDetails(final SiteDetails siteDetails) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertSiteDetails(siteDetails);
        });
    }

    void insertListItem(final ListItem listItem) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertListItem(listItem);
        });
    }

    void insertCategory(final Category category) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertCategory(category);
        });
    }

    void insertSubCategory(final SubCategory subCategory) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertSubCategory(subCategory);
        });
    }

    void updateReport(Integer reportId, String buyerFirstName, String buyerLastName, String sellerFirstName, String sellerLastName, @NonNull String street, @NonNull String city, @NonNull String state, @NonNull String zip, String photo) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateReport(reportId, buyerFirstName, buyerLastName, sellerFirstName, sellerLastName, street, city, state, zip, photo);
        });
    }

    void updateSiteDetails(Integer reportId, double bathrooms, int bedrooms, int stories, double inspectionFee, int yearBuilt, String furnished, String presentAtInspection, String orientation) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateSiteDetails(reportId, bathrooms, bedrooms, stories, inspectionFee, yearBuilt, furnished, presentAtInspection, orientation);
        });
    }

    void updateListItem(Integer listItemId, String notes, String photos, Integer subCat) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateListItem(listItemId, notes, photos, subCat);
        });
    }

    void updateProfileInfo(Integer profileId, String firstName, String lastName, String email, String phone, String companyName, String licenseNumber, String photo) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateProfileInfo(profileId, firstName, lastName, email, phone, companyName, licenseNumber, photo);
        });
    }


    // DELETE QUERIES
    void deleteProfile(Integer profileId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deleteProfile(profileId);
        });
    }

    void deleteReport(Integer reportId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deleteReport(reportId);
        });
    }

    void deleteSiteDetails(Integer siteDetailsId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deleteSiteDetails(siteDetailsId);
        });
    }

    void deleteListItem(Integer listItemId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deleteListItem(listItemId);
        });
    }

    void deleteCategory(Integer categoryId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deleteCategory(categoryId);
        });
    }

    void deleteSubCategory(Integer subCategoryId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deleteSubCategory(subCategoryId);
        });
    }


    // FETCH ALL QUERIES
    LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }
    LiveData<List<Report>> getAllReports() {
        return allReports;
    }
    List<SiteDetails> getAllSiteDetails() { return allSiteDetails; }
    List<SiteDetails> getCurrentSiteDetails() { return currentSiteDetails; }
    LiveData<List<Category>> getAllCategories() { return allCategories; }
    LiveData<List<SubCategory>> getAllSubcategories() { return allSubcategories; }
    LiveData<List<ListItem>> getAllListItems() { return allListItems; }

    Report getNewReport() {
        return foundationsDao.getNewReport();
    }

    List<SiteDetails> getSiteDetails(int id) {
        currentSiteDetails = foundationsDao.getSiteDetails(id);
        return currentSiteDetails;
    }




}
