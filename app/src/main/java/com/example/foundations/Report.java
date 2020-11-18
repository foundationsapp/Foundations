package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName  = "report_table")
public class Report {

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reportId")
    private Integer reportId;

    @NonNull
    @ColumnInfo(name = "profileId")
    private Integer profileId;

    @ColumnInfo(name ="buyerFirstName")
    private String buyerFirstName;

    @ColumnInfo(name = "buyerLastName")
    private String buyerLastName;

    @ColumnInfo(name = "sellerFirstName")
    private String sellerFirstName;

    @ColumnInfo(name = "sellerLastName")
    private String sellerLastName;

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
    private String zip;

    @NonNull
    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(@NonNull Integer profileId) {
        this.profileId = profileId;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public void setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public void setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getReportId() {
        return reportId;
    }

    public String getStreetAddress() {
        StringBuilder addr = new StringBuilder();
        addr.append(getStreet());
        addr.append(", ");
        addr.append(getCity());
        addr.append(", ");
        addr.append(getState());
        addr.append(", ");
        addr.append(getZip());
        return addr.toString();
    }

    public Report(@NonNull Integer profileId, String buyerFirstName, String buyerLastName, String sellerFirstName, String sellerLastName, @NonNull String street, @NonNull String city, @NonNull String state, @NonNull String zip) {
        this.reportId  = null;
        this.profileId = profileId;
        this.buyerFirstName = buyerFirstName;
        this.buyerLastName = buyerLastName;
        this.sellerFirstName = sellerFirstName;
        this.sellerLastName = sellerLastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
