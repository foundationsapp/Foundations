package com.example.foundations;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteId")
    private Integer noteId;

    @NonNull
    @ColumnInfo(name = "listItemId")
    private Integer listItemId;

    @NonNull
    @ColumnInfo(name = "details")
    private String details;

    @ColumnInfo(name = "title")
    private String title;

    public Integer getNoteId() {
        return noteId;
    }

    @NonNull
    public Integer getListItemId() {
        return listItemId;
    }

    @NonNull
    public String getDetails() {
        return details;
    }

    public String getTitle() {
        return title;
    }

    public void setDetails(@NonNull String details) {
        this.details = details;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Note(@NonNull Integer listItemId, @NonNull String details, String title) {
        this.listItemId = listItemId;
        this.details = details;
        this.title = title;
        this.noteId = null;
    }
}
