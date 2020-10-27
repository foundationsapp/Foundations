package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subcategory_table")
public class SubCategory {

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subCategoryId")
    private Integer subCategoryId;

    @NonNull
    @ColumnInfo(name = "categoryId")
    private Integer categoryId;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    @NonNull
    public Integer getCategoryId() {
        return categoryId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public SubCategory(@NonNull Integer categoryId, @NonNull String title) {
        this.categoryId = categoryId;
        this.title = title;
        this.subCategoryId = null;
    }
}
