package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "categoryId")
    private Integer categoryId;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public Category(@NonNull String title) {
        this.title = title;
        this.categoryId = null;
    }
}
