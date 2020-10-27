package com.example.foundations;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoundationsDao {


    //Mainly for testing
    @Query("DELETE FROM profile_table")
    void deleteAllProfiles();


    //INSERT CREATE QUERIES
    @Insert
    void insertProfile(Profile profile);

    @Insert
    void insertBuyer(Buyer buyer);

    @Insert
    void insertSeller(Seller seller);

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

    @Insert
    void insertPhoto(Photo photo);

    @Insert
    void insertNote(Note note);


    //UPDATE QUERIES
    @Query("UPDATE report_table SET buyerId = :buyerId WHERE reportId = :reportId")
    void updateReportBuyer(Integer buyerId, Integer reportId);

    @Query("UPDATE report_table SET sellerId = :sellerId WHERE reportId  = :reportId")
    void updateReportSeller(Integer sellerId, Integer reportId);

    @Query("UPDATE report_table SET street = :street, city = :city, state = :state, zip = :zip WHERE reportId = :reportId")
    void updateReportAddress(Integer reportId, String street, String city, String state, int zip);

    @Query("UPDATE sitedetails_table SET bathrooms = :bathrooms, bedrooms = :bedrooms, stories = :stories, inspectionFee = :inspectionFee, yearBuilt = :yearBuilt, furnished = :furnished, presentAtInspection = :presentAtInspection, orientation = :orientation WHERE siteDetailsId =:siteDetailsId")
    void updateSiteDetails(Integer siteDetailsId, float bathrooms, int bedrooms, int stories, float inspectionFee, int yearBuilt, String furnished, String presentAtInspection, String orientation);

    @Query("UPDATE listitem_table SET notes = :notes, photos = :photos WHERE listItemId = :listItemId")
    void updateListItem(Integer listItemId, Boolean notes, Boolean photos);

    @Query("UPDATE buyer_table SET firstName = :firstName, lastName  = :lastName, email = :email, phone = :phone WHERE buyerId = :buyerId")
    void updateBuyerInfo(Integer buyerId, String firstName, String lastName, String email, int phone);

    @Query("UPDATE seller_table SET firstName = :firstName, lastName  = :lastName, email = :email, phone = :phone WHERE sellerId = :sellerId")
    void updateSellerInfo(Integer sellerId, String firstName, String lastName, String email, int phone);

    @Query("UPDATE profile_table SET firstName = :firstName, lastName  = :lastName, email = :email, phone = :phone, companyName = :companyName, licenseNumber = :licenseNumber WHERE profileId = :profileId")
    void updateProfileInfo(Integer profileId, String firstName, String lastName, String email, String phone, String companyName, String licenseNumber);


    // DELETE QUERIES
    @Query("DELETE FROM profile_table WHERE profileId = :profileId")
    void deleteProfile(Integer profileId);

    @Query("DELETE FROM buyer_table WHERE buyerId = :buyerId")
    void deleteBuyer(Integer buyerId);

    @Query("DELETE FROM seller_table WHERE sellerId = :sellerId")
    void deleteSeller(Integer sellerId);

    @Query("DELETE FROM report_table WHERE reportId = :reportId")
    void deleteReport(Integer reportId);

    @Query("DELETE FROM sitedetails_table WHERE siteDetailsId = :siteDetailsId")
    void deleteSiteDetails(Integer siteDetailsId);

    @Query("DELETE FROM listitem_table WHERE listItemId = :listItemId")
    void deleteListItem(Integer listItemId);

    @Query("DELETE FROM photo_table WHERE photoId = :photoId")
    void deletePhoto(Integer photoId);

    @Query("DELETE FROM note_table WHERE noteId = :noteId")
    void deleteNote(Integer noteId);

    @Query("DELETE FROM category_table WHERE categoryId = :categoryId")
    void deleteCategory(Integer categoryId);

    @Query("DELETE FROM subcategory_table WHERE subCategoryId = :subCategoryId")
    void deleteSubCategory(Integer subCategoryId);


    // FETCH ALL QUERIES
    @Query("SELECT * from profile_table ORDER BY profileId ASC")
    LiveData<List<Profile>> getAllProfiles();

    @Query("SELECT * from buyer_table ORDER BY buyerId ASC")
    LiveData<List<Buyer>> getAllBuyers();

    @Query("SELECT * from seller_table ORDER BY sellerId ASC")
    LiveData<List<Seller>> getAllSellers();

    @Query("SELECT * from report_table ORDER BY reportId ASC")
    LiveData<List<Report>> getAllReports();


    // FETCH REPORT DATA QUERIES
    @Query("SELECT * from sitedetails_table WHERE reportId = :reportId")
    LiveData<List<SiteDetails>> getSiteDetails(Integer reportId);

    @Query("SELECT * from photo_table WHERE listItemId = :listItemId")
    LiveData<List<Photo>> getPhotos(Integer listItemId);

    @Query("SELECT * from note_table WHERE listItemId = :listItemId")
    LiveData<List<Note>> getNotes(Integer listItemId);

    // GET LIST ITEM QUERY THAT RETURNS BOTH CATEGORY ID AND CATEGORY TITLE, SUBCAT TOO
    // @Query("SELECT listItemId, categoryId, subCategoryId, notes, photos, category_table.title, subcategory_table.title from listitem_table, category_table, subcategory_table WHERE reportId = :reportId")

}