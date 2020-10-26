package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "listitem_table")
public class ListItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "listItemId")
    private Integer listItemId;

    @NonNull
    @ColumnInfo(name = "reportId")
    private Integer reportId;

    @NonNull
    @ColumnInfo(name = "categoryId")
    private Integer categoryId;

    @ColumnInfo(name = "subCategoryId")
    private Integer subCategoryId;

    @ColumnInfo(name = "notes")
    private Boolean notes = false;

    @ColumnInfo(name = "photos")
    private Boolean photos = false;

    public Integer getListItemId() {
        return listItemId;
    }

    @NonNull
    public Integer getReportId() {
        return reportId;
    }

    @NonNull
    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public Boolean getNotes() {
        return notes;
    }

    public Boolean getPhotos() {
        return photos;
    }

    public void setNotes(Boolean notes) {
        this.notes = notes;
    }

    public void setPhotos(Boolean photos) {
        this.photos = photos;
    }

    public ListItem(@NonNull Integer reportId, @NonNull Integer categoryId, Integer subCategoryId, Boolean notes, Boolean photos) {
        this.reportId = reportId;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.notes = notes;
        this.photos = photos;
        this.listItemId = null;
    }
}
