package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile_table")
public class Profile {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    public Integer getId() { return id; }

    @NonNull
    @ColumnInfo(name = "firstName")
    private String firstName;
    public String getFirstName() { return firstName; }

    @NonNull
    @ColumnInfo(name = "lastName")
    private String lastName;
    public String getLastName() { return lastName; }

    @NonNull
    @ColumnInfo(name = "licenseNumber")
    private String licenseNumber;
    public String getLicenseNumber() { return licenseNumber;}

    @NonNull
    @ColumnInfo(name = "email")
    private String email;
    public String getEmail() { return email; }

    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;
    public String getPhone() { return phone; }

    @ColumnInfo(name = "companyName")
    private String companyName;
    public String getCompanyName() { return companyName; }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public void setLicenseNumber(@NonNull String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Profile(Integer id, @NonNull String firstName, @NonNull String lastName, @NonNull String licenseNumber, @NonNull String email, @NonNull String phone, String companyName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
    }
}
