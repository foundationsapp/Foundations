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

    @Query("SELECT * from profile_table ORDER BY id ASC")
    LiveData<List<Profile>> getAllProfiles();
}