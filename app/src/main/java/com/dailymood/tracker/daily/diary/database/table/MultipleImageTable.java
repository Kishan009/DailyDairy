package com.dailymood.tracker.daily.diary.database.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "multiple_image")
public class MultipleImageTable
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "image_id")
    private  int image_id;

    @NonNull
    @ColumnInfo(name = "note_id")
    private  int note_id;

    @ColumnInfo(name = "image_path")
    private String image_path;


    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "updated")
    private long updated;


    @NonNull
    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(@NonNull int image_id) {
        this.image_id = image_id;
    }

    @NonNull
    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(@NonNull int note_id) {
        this.note_id = note_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }
}
