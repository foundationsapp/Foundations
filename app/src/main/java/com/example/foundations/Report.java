package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName  = "report_table")
public class Report {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reportId")
    private Integer reportId;

    @NonNull
    @ColumnInfo(name = "profileId")
    private Integer profileId;

    @ColumnInfo(name ="buyerId")
    private Integer buyerId;

    @ColumnInfo(name = "sellerId")
    private Integer sellerId;

    @NonNull
    @ColumnInfo(name = "street")
    private String street;

    @NonNull
    @ColumnInfo(name  = "city")
    private String city;

    @NonNull
    @ColumnInfo(name = "state")
    private String state;

    @NonNull
    @ColumnInfo(name = "zip")
    private int zip;

    @NonNull
    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(@NonNull Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    @NonNull
    public String getStreet() {
        return street;
    }

    public void setStreet(@NonNull String street) {
        this.street = street;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    @NonNull
    public String getState() {
        return state;
    }

    public void setState(@NonNull String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public Integer getReportId() {
        return reportId;
    }

    public Report(@NonNull Integer profileId, Integer buyerId, Integer sellerId, @NonNull String street, @NonNull String city, @NonNull String state, @NonNull int zip) {
        this.reportId  = null;
        this.profileId = profileId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
