package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sitedetails_table")
public class SiteDetails {

    public void setSiteDetailsId(Integer siteDetailsId) {
        this.siteDetailsId = siteDetailsId;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "siteDetailsId")
    private Integer siteDetailsId;

    @NonNull
    @ColumnInfo(name = "reportId")
    private Integer reportId;

    @ColumnInfo(name = "bedrooms")
    private int bedrooms;

    @ColumnInfo(name = "bathrooms")
    private double bathrooms;

    @ColumnInfo(name = "stories")
    private int stories;

    @ColumnInfo(name = "squareFeet")
    private int squareFeet;

    @ColumnInfo(name = "inspectionFee")
    private double inspectionFee;

    @ColumnInfo(name = "yearBuilt")
    private int yearBuilt;

    @ColumnInfo(name = "furnished")
    private String furnished;

    @ColumnInfo(name = "presentAtInspection")
    private String presentAtInspection;

    @ColumnInfo(name = "orientation")
    private String orientation;

    @NonNull
    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(@NonNull Integer reportId) {
        this.reportId = reportId;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public double getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(double bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getStories() {
        return stories;
    }

    public void setStories(int stories) {
        this.stories = stories;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public double getInspectionFee() {
        return inspectionFee;
    }

    public void setInspectionFee(int inspectionFee) {
        this.inspectionFee = inspectionFee;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getFurnished() {
        return furnished;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public String getPresentAtInspection() {
        return presentAtInspection;
    }

    public void setPresentAtInspection(String presentAtInspection) {
        this.presentAtInspection = presentAtInspection;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Integer getSiteDetailsId() {
        return siteDetailsId;
    }

    public SiteDetails(@NonNull Integer reportId, int bedrooms, double bathrooms, int stories, int squareFeet, double inspectionFee, int yearBuilt, String furnished, String presentAtInspection, String orientation) {
        this.reportId = reportId;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.stories = stories;
        this.squareFeet = squareFeet;
        this.inspectionFee = inspectionFee;
        this.yearBuilt = yearBuilt;
        this.furnished = furnished;
        this.presentAtInspection = presentAtInspection;
        this.orientation = orientation;
        this.siteDetailsId = null;
    }
}
