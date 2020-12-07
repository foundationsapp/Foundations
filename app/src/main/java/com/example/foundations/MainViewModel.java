package com.example.foundations;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    LiveData<List<Profile>> allProfiles;
    LiveData<List<Report>> allReports;
    private LiveData<List<Category>> allCategories;
    private LiveData<List<SubCategory>> allSubcategories;
    private LiveData<List<ListItem>> allListItems;

    private FoundationsRepository foundationsRepository;

    public MainViewModel(Application application) {
        super(application);
        foundationsRepository = new FoundationsRepository(application);
        allProfiles = foundationsRepository.getAllProfiles();
        allReports  = foundationsRepository.getAllReports();
        allCategories = foundationsRepository.getAllCategories();
        allSubcategories = foundationsRepository.getAllSubcategories();
        allListItems = foundationsRepository.getAllListItems();
    }



    // INSERT
    public void insertProfile(Profile profile) {foundationsRepository.insertProfile(profile);}
    public void insertReport(Report report) {foundationsRepository.insertReport(report);}
    public void insertSiteDetails(SiteDetails siteDetails) {foundationsRepository.insertSiteDetails(siteDetails);}
    public void insertListItem(ListItem listItem) {foundationsRepository.insertListItem(listItem);}
    public void insertCategory(Category category) {foundationsRepository.insertCategory(category);}
    public void insertSubCategory(SubCategory subCategory) {foundationsRepository.insertSubCategory(subCategory);}

    // DELETE
    public void deleteListItem(int id) {foundationsRepository.deleteListItem(id);}

    // UPDATE
    public void updateReport(Integer reportId, String buyerFirstName, String buyerLastName, String sellerFirstName, String sellerLastName, @NonNull String street, @NonNull String city, @NonNull String state, @NonNull String zip, String photo) {foundationsRepository.updateReport(reportId, buyerFirstName, buyerLastName, sellerFirstName, sellerLastName, street, city, state, zip, photo);}
    public void updateSiteDetails(Integer reportId, double bathrooms, int bedrooms, int stories, double inspectionFee, int yearBuilt, String furnished, String presentAtInspection, String orientation) {foundationsRepository.updateSiteDetails(reportId, bathrooms, bedrooms, stories, inspectionFee, yearBuilt, furnished, presentAtInspection, orientation);}
    public void updateListItem(Integer listItemId, String notes, String photos, Integer subCat) {foundationsRepository.updateListItem(listItemId, notes, photos, subCat);}
    public void updateProfileInfo(Integer profileId, String firstName, String lastName, String email, String phone, String companyName, String licenseNumber, String photo) {foundationsRepository.updateProfileInfo(profileId, firstName, lastName, email, phone, companyName, licenseNumber, photo);}

    public LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }
    public LiveData<List<Report>> getAllReports() { return allReports; }
    public LiveData<List<Category>> getAllCategories() { return allCategories; }
    public LiveData<List<SubCategory>> getAllSubcategories() { return allSubcategories; }
    public LiveData<List<ListItem>> getAllListItems() { return allListItems; }

    public Report getNewReport() {
        return foundationsRepository.getNewReport();
    }

    public List<SiteDetails> getSiteDetails(int id) {
        return foundationsRepository.getSiteDetails(id);
    }

}
