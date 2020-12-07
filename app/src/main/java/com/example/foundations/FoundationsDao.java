package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoundationsDao {


//    //Mainly for testing
//    @Query("DELETE FROM profile_table")
//    void deleteAllProfiles();
//
//    @Query("DELETE FROM sitedetails_table")
//    void deleteSiteDetails();
//
//    @Query("DELETE FROM report_table")
//    void deleteReports();
//
//    @Query("DELETE FROM listitem_table")
//    void deleteListItems();
//
//    @Query("DELETE FROM category_table")
//    void deleteCategory();
//
//    @Query("DELETE FROM subcategory_table")
//    void deleteSubcategory();



    //INSERT CREATE QUERIES
    @Insert
    void insertProfile(Profile profile);

    @Insert
    void insertReport(Report report);

    @Insert
    void insertSiteDetails(SiteDetails siteDetails);

    @Insert
    void insertListItem(ListItem listItem);

    @Insert
    void insertCategory(Category category);

    @Insert
    void insertSubCategory(SubCategory subCategory);

    @Query("UPDATE report_table SET buyerFirstName = :buyerFirstName, buyerLastName = :buyerLastName, sellerFirstName = :sellerFirstName, sellerLastName = :sellerLastName, street = :street, city = :city, state = :state, zip = :zip, propertyPhoto = :photo WHERE reportId = :reportId")
    void updateReport(Integer reportId, String buyerFirstName, String buyerLastName, String sellerFirstName, String sellerLastName, @NonNull String street, @NonNull String city, @NonNull String state, @NonNull String zip, String photo);

    @Query("UPDATE sitedetails_table SET bathrooms = :bathrooms, bedrooms = :bedrooms, stories = :stories, inspectionFee = :inspectionFee, yearBuilt = :yearBuilt, furnished = :furnished, presentAtInspection = :presentAtInspection, orientation = :orientation WHERE reportId =:reportId")
    void updateSiteDetails(Integer reportId, double bathrooms, int bedrooms, int stories, double inspectionFee, int yearBuilt, String furnished, String presentAtInspection, String orientation);

    @Query("UPDATE listitem_table SET notes = :notes, photos = :photos, subCategoryId = :subCat WHERE listItemId = :listItemId")
    void updateListItem(Integer listItemId, String notes, String photos, Integer subCat);

    @Query("UPDATE profile_table SET firstName = :firstName, lastName  = :lastName, email = :email, phone = :phone, companyName = :companyName, licenseNumber = :licenseNumber, photo = :photo WHERE profileId = :profileId")
    void updateProfileInfo(Integer profileId, String firstName, String lastName, String email, String phone, String companyName, String licenseNumber, String photo);

    @Query("UPDATE report_table SET pdf = :pdf WHERE reportId = :reportId")
    void addPDF(Integer reportId, String pdf);

//    // DELETE QUERIES
//    @Query("DELETE FROM profile_table WHERE profileId = :profileId")
//    void deleteProfile(Integer profileId);
//
//    @Query("DELETE FROM report_table WHERE reportId = :reportId")
//    void deleteReport(Integer reportId);
//
//    @Query("DELETE FROM sitedetails_table WHERE siteDetailsId = :siteDetailsId")
//    void deleteSiteDetails(Integer siteDetailsId);

    @Query("DELETE FROM listitem_table WHERE listItemId = :listItemId")
    void deleteListItem(Integer listItemId);

//    @Query("DELETE FROM category_table WHERE categoryId = :categoryId")
//    void deleteCategory(Integer categoryId);
//
//    @Query("DELETE FROM subcategory_table WHERE subCategoryId = :subCategoryId")
//    void deleteSubCategory(Integer subCategoryId);


    // FETCH ALL QUERIES
    @Query("SELECT * from profile_table ORDER BY profileId ASC")
    LiveData<List<Profile>> getAllProfiles();

    @Query("SELECT * from report_table ORDER BY reportId ASC")
    LiveData<List<Report>> getAllReports();

    @Query("SELECT * from category_table ORDER BY title ASC")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * from subcategory_table ORDER BY title ASC")
    LiveData<List<SubCategory>> getAllSubcategories();

    @Query("SELECT * from sitedetails_table ORDER BY siteDetailsId ASC")
    List<SiteDetails> getAllSiteDetails();

//    @Query("SELECT * from listitem_table WHERE :reportId = reportId")
//    LiveData<List<ListItem>> getCurrentListItems(Integer reportId);

    @Query("SELECT * from listitem_table ORDER BY listItemId ASC")
    LiveData<List<ListItem>> getAllListItems();

    // FETCH REPORT DATA QUERIES
    @Query("SELECT * from sitedetails_table where reportId = :reportId")
    List<SiteDetails> getSiteDetails(Integer reportId);

    @Query("SELECT * from report_table order by reportId DESC limit 1")
    Report getNewReport();

}