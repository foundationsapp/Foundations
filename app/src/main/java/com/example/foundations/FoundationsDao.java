package com.example.foundations;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoundationsDao {

    @Insert
    void insertProfile(Profile profile);

    @Query("DELETE FROM profile_table")
    void deleteAllProfiles();

    @Query("SELECT * from profile_table ORDER BY profileId ASC")
    LiveData<List<Profile>> getAllProfiles();

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

    
}