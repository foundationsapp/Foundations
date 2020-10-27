package com.example.foundations;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile_table")
public class Profile implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profileId")
    private Integer profileId;

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    protected Profile(Parcel in) {
        if (in.readByte() == 0) {
            profileId = null;
        } else {
            profileId = in.readInt();
        }
        firstName = in.readString();
        lastName = in.readString();
        licenseNumber = in.readString();
        email = in.readString();
        phone = in.readString();
        companyName = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public Integer getProfileId() { return profileId; }

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

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        fullName.append(firstName);
        fullName.append(" ");
        fullName.append(lastName);
        return fullName.toString();
    }

    public Profile(@NonNull String firstName, @NonNull String lastName, @NonNull String licenseNumber, @NonNull String email, @NonNull String phone, String companyName) {
        this.profileId = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (profileId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(profileId);
        }
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(licenseNumber);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(companyName);
    }
}
