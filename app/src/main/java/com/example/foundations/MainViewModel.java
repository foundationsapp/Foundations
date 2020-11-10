package com.example.foundations;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    LiveData<List<Profile>> allProfiles;
    LiveData<List<Report>> allReports;
    private FoundationsRepository foundationsRepository;

    public MainViewModel(Application application) {
        super(application);
        foundationsRepository = new FoundationsRepository(application);
        allProfiles = foundationsRepository.getAllProfiles();
        allReports  = foundationsRepository.getAllReports();
    }


    // INSERT
    public void insertProfile(Profile profile) {foundationsRepository.insertProfile(profile);}
    public void insertBuyer(Buyer buyer) {foundationsRepository.insertBuyer(buyer);}
    public void insertSeller(Seller seller) {foundationsRepository.insertSeller(seller);}
    public void insertReport(Report report) {foundationsRepository.insertReport(report);}
    public void insertSiteDetails(SiteDetails siteDetails) {foundationsRepository.insertSiteDetails(siteDetails);}
    public void insertListItem(ListItem listItem) {foundationsRepository.insertListItem(listItem);}
    public void insertCategory(Category category) {foundationsRepository.insertCategory(category);}
    public void insertSubCategory(SubCategory subCategory) {foundationsRepository.insertSubCategory(subCategory);}
    public void insertPhoto(Photo photo) {foundationsRepository.insertPhoto(photo);}
    public void insertNote(Note note) {foundationsRepository.insertNote(note);}


    // UPDATE
//    public void updateReportBuyer(Integer buyerId, Integer reportId) {foundationsRepository.updateReportBuyer(buyerId, reportId);}
//    public void updateReportSeller(Integer sellerId, Integer reportId) {foundationsRepository.updateReportSeller(sellerId, reportId);}
    public void updateReportAddress(Integer reportId, String street, String city, String state, int zip) {foundationsRepository.updateReportAddress(reportId, street, city, state, zip);}
    public void updateSiteDetails(Integer siteDetailsId, float bathrooms, int bedrooms, int stories, float inspectionFee, int yearBuilt, String furnished, String presentAtInspection, String orientation) {foundationsRepository.updateSiteDetails(siteDetailsId, bathrooms, bedrooms, stories, inspectionFee, yearBuilt, furnished, presentAtInspection, orientation);}
    public void updateListItem(Integer listItemId, Boolean notes, Boolean photos) {foundationsRepository.updateListItem(listItemId, notes, photos);}
    public void updateBuyerInfo(Integer buyerId, String firstName, String lastName, String email, String phone) {foundationsRepository.updateBuyerInfo(buyerId, firstName, lastName, email, phone);}
    public void updateSellerInfo(Integer sellerId, String firstName, String lastName, String email, String phone) {foundationsRepository.updateSellerInfo(sellerId, firstName, lastName, email, phone);}
    public void updateProfileInfo(Integer profileId, String firstName, String lastName, String email, String phone, String companyName, String licenseNumber) {foundationsRepository.updateProfileInfo(profileId, firstName, lastName, email, phone, companyName, licenseNumber);}

    public LiveData<List<Profile>> getAllProfiles() {
        return allProfiles;
    }
    public LiveData<List<Report>> getAllReports() { return allReports; }

    public Report getNewReport() {
        return foundationsRepository.getNewReport();
    }

}
