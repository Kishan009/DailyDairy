package com.dailymood.tracker.daily.diary.database.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "activity")
public class ActivityTable implements Serializable
{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "activity_id")
    private  int activity_id;

    @ColumnInfo(name = "activity_name")
    private String activity_name;

    @ColumnInfo(name = "activity_image")
    private String activity_image;

    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "updated")
    private long updated;


    @NonNull
    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(@NonNull int activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_image() {
        return activity_image;
    }

    public void setActivity_image(String activity_image) {
        this.activity_image = activity_image;
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
