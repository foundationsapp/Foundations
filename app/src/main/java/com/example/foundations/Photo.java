package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity(tableName = "photo_table")
public class Photo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "photoId")
    private Integer photoId;

    @NonNull
    @ColumnInfo(name = "listItemId")
    private Integer listItemId;

    @NonNull
    @ColumnInfo(name = "photoBlob")
    private Blob photoBlob;

    public Integer getPhotoId() {
        return photoId;
    }

    @NonNull
    public Integer getListItemId() {
        return listItemId;
    }

    @NonNull
    public Blob getPhotoBlob() {
        return photoBlob;
    }

    public Photo(@NonNull Integer listItemId, @NonNull Blob photoBlob) {
        this.listItemId = listItemId;
        this.photoBlob = photoBlob;
        this.photoId = null;
    }
}
