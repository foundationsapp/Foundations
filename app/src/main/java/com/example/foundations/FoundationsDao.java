package com.example.foundations;

import androidx.annotation.NonNull;
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

    @Query("DELETE FROM buyer_table")
    void deleteBuyers();

    @Query("DELETE FROM seller_table")
    void deleteSellers();

    @Query("DELETE FROM sitedetails_table")
    void deleteSiteDetails();

    @Query("DELETE FROM report_table")
    void deleteReports();

    @Query("DELETE FROM listitem_table")
    void deleteListItems();

    @Query("DELETE FROM note_table")
    void deleteNotes();

    @Query("DELETE FROM category_table")
    void deleteCategory();

    @Query("DELETE FROM subcategory_table")
    void deleteSubcategory();



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
//    @Query("UPDATE report_table SET buyerId = :buyerId WHERE reportId = :reportId")
//    void updateReportBuyer(Integer buyerId, Integer reportId);
//
//    @Query("UPDATE report_table SET sellerId = :sellerId WHERE reportId  = :reportId")
//    void updateReportSeller(Integer sellerId, Integer reportId);

    @Query("UPDATE report_table SET buyerFirstName = :buyerFirstName, buyerLastName = :buyerLastName, sellerFirstName = :sellerFirstName, sellerLastName = :sellerLastName, street = :street, city = :city, state = :state, zip = :zip WHERE reportId = :reportId")
    void updateReport(Integer reportId, String buyerFirstName, String buyerLastName, String sellerFirstName, String sellerLastName, @NonNull String street, @NonNull String city, @NonNull String state, @NonNull String zip);

    @Query("UPDATE sitedetails_table SET bathrooms = :bathrooms, bedrooms = :bedrooms, stories = :stories, inspectionFee = :inspectionFee, yearBuilt = :yearBuilt, furnished = :furnished, presentAtInspection = :presentAtInspection, orientation = :orientation WHERE reportId =:reportId")
    void updateSiteDetails(Integer reportId, double bathrooms, int bedrooms, int stories, double inspectionFee, int yearBuilt, String furnished, String presentAtInspection, String orientation);

    @Query("UPDATE listitem_table SET notes = :notes, photos = :photos WHERE listItemId = :listItemId")
    void updateListItem(Integer listItemId, String notes, Boolean photos);

    @Query("UPDATE buyer_table SET firstName = :firstName, lastName  = :lastName, email = :email, phone = :phone WHERE buyerId = :buyerId")
    void updateBuyerInfo(Integer buyerId, String firstName, String lastName, String email, String phone);

    @Query("UPDATE seller_table SET firstName = :firstName, lastName  = :lastName, email = :email, phone = :phone WHERE sellerId = :sellerId")
    void updateSellerInfo(Integer sellerId, String firstName, String lastName, String email, String phone);

    @Query("UPDATE profile_table SET firstName = :firstName, lastName  = :lastName, email = :email, phone = :phone, companyName = :companyName, licenseNumber = :licenseNumber, photo = :photo WHERE profileId = :profileId")
    void updateProfileInfo(Integer profileId, String firstName, String lastName, String email, String phone, String companyName, String licenseNumber, String photo);


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

    @Query("SELECT * from category_table ORDER BY title ASC")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * from subcategory_table ORDER BY title ASC")
    LiveData<List<SubCategory>> getAllSubcategories();

    @Query("SELECT * from sitedetails_table ORDER BY siteDetailsId ASC")
    List<SiteDetails> getAllSiteDetails();

    @Query("SELECT * from listitem_table WHERE :reportId = reportId")
    LiveData<List<ListItem>> getCurrentListItems(Integer reportId);

    @Query("SELECT * from listitem_table ORDER BY listItemId ASC")
    LiveData<List<ListItem>> getAllListItems();

    // FETCH REPORT DATA QUERIES
    @Query("SELECT * from sitedetails_table where reportId = :reportId")
    List<SiteDetails> getSiteDetails(Integer reportId);

    @Query("SELECT * from photo_table WHERE reportId = :reportId")
    LiveData<List<Photo>> getPhotos(Integer reportId);

    @Query("SELECT * from note_table WHERE reportId = :reportId")
    LiveData<List<Note>> getNotes(Integer reportId);

    @Query("SELECT * from report_table order by reportId DESC limit 1")
    Report getNewReport();

    // GET LIST ITEM QUERY THAT RETURNS BOTH CATEGORY ID AND CATEGORY TITLE, SUBCAT TOO
    @Query("SELECT listitem_table.listItemId AS listItemId, listitem_table.categoryId AS categoryId, listitem_table.subCategoryId AS subCategoryId, listitem_table.notes AS notes, listitem_table.photos AS photos, category_table.title AS categoryTitle, subcategory_table.title AS subCategoryTitle from listitem_table, category_table, subcategory_table WHERE reportId = :reportId")
    LiveData<List<ListItemDetails>> getListItems(Integer reportId);

}