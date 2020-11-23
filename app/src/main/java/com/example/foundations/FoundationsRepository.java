package com.example.foundations;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class FoundationsRepository {

    private static final String TAG = FoundationsRepository.class.getSimpleName();
    private FoundationsDao foundationsDao;
    private LiveData<List<Profile>> allProfiles;
    private LiveData<List<Buyer>> allBuyers;
    private LiveData<List<Seller>> allSellers;
    private LiveData<List<Report>> allReports;
    private LiveData<List<Category>> allCategories;
    private LiveData<List<SubCategory>> allSubCategories;
    private LiveData<List<SiteDetails>> currentSiteDetails;
    private LiveData<List<Note>> currentReportNotes;
    private LiveData<List<Photo>> currentReportPhotos;
    private LiveData<List<ListItemDetails>> currentListItemDetails;

    FoundationsRepository(Application application) {
        FoundationsRoomDatabase db = FoundationsRoomDatabase.getDatabase(application);
        foundationsDao = db.foundationsDao();
        allProfiles = foundationsDao.getAllProfiles();
        allBuyers = foundationsDao.getAllBuyers();
        allSellers = foundationsDao.getAllSellers();
        allReports = foundationsDao.getAllReports();
        allCategories = foundationsDao.getAllCategories();
        allSubCategories = foundationsDao.getAllSubCategories();
        loadReportData(1);
    }

    // INSERT QUERIES
    void insertProfile(final Profile profile) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertProfile(profile);
        });
    }

    void insertBuyer(final Buyer buyer) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertBuyer(buyer);
        });
    }

    void insertSeller(final Seller seller) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertSeller(seller);
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

    void insertPhoto(final Photo photo) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertPhoto(photo);
        });
    }

    void insertNote(final Note note) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.insertNote(note);
        });
    }


    // UPDATE QUERIES
    void updateReportBuyer(Integer buyerId, Integer reportId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateReportBuyer(buyerId, reportId);
        });
    }

    void updateReportSeller(Integer sellerId, Integer reportId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateReportSeller(sellerId, reportId);
        });
    }

    void updateReportAddress(Integer reportId, String street, String city, String state, int zip) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateReportAddress(reportId, street, city, state, zip);
        });
    }

    void updateSiteDetails(Integer siteDetailsId, float bathrooms, int bedrooms, int stories, float inspectionFee, int yearBuilt, String furnished, String presentAtInspection, String orientation) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateSiteDetails(siteDetailsId, bathrooms, bedrooms, stories, inspectionFee, yearBuilt, furnished, presentAtInspection, orientation);
        });
    }

    void updateListItem(Integer listItemId, Boolean notes, Boolean photos) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateListItem(listItemId, notes, photos);
        });
    }

    void updateBuyerInfo(Integer buyerId, String firstName, String lastName, String email, String phone) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateBuyerInfo(buyerId, firstName, lastName, email, phone);
        });
    }

    void updateSellerInfo(Integer sellerId, String firstName, String lastName, String email, String phone) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateSellerInfo(sellerId, firstName, lastName, email, phone);
        });
    }

    void updateProfileInfo(Integer profileId, String firstName, String lastName, String email, String phone, String companyName, String licenseNumber) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.updateProfileInfo(profileId, firstName, lastName, email, phone, companyName, licenseNumber);
        });
    }


    // DELETE QUERIES
    void deleteProfile(Integer profileId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deleteProfile(profileId);
        });
    }

    void deleteBuyer(Integer buyerId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deleteBuyer(buyerId);
        });
    }

    void deleteSeller(Integer sellerId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deleteSeller(sellerId);
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

    void deletePhoto(Integer photoId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deletePhoto(photoId);
        });
    }

    void deleteNote(Integer noteId) {
        FoundationsRoomDatabase.databaseWriteExecutor.execute(() -> {
            foundationsDao.deleteNote(noteId);
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
    LiveData<List<Buyer>> getAllBuyers() {
        return allBuyers;
    }
    LiveData<List<Seller>> getAllSellers() {
        return allSellers;
    }
    LiveData<List<Report>> getAllReports() {
        return allReports;
    }
    LiveData<List<Category>> getAllCategories() { return allCategories; }
    LiveData<List<SubCategory>> getAllSubCategories() { return allSubCategories; }
    LiveData<List<SiteDetails>> getCurrentSiteDetails() { return currentSiteDetails; }
    LiveData<List<Photo>> getCurrentReportPhotos() { return currentReportPhotos; }
    LiveData<List<Note>> getCurrentReportNotes() { return currentReportNotes; }
    LiveData<List<ListItemDetails>> getCurrentListItemDetails() { return currentListItemDetails; }

    // FETCH REPORT DATA QUERIES
    void loadReportData(Integer reportId) {
        currentSiteDetails = foundationsDao.getSiteDetails(reportId);
        currentReportNotes = foundationsDao.getNotes(reportId);
        currentReportPhotos = foundationsDao.getPhotos(reportId);
        currentListItemDetails = foundationsDao.getListItems(reportId);
    }



}
