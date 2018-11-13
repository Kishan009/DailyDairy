package com.dailymood.tracker.daily.diary.database.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "mood")
public class MoodTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "mood_id")
    private int mood_id;

    @ColumnInfo(name = "mood_name")
    private String mood_name;

    @ColumnInfo(name = "mood_image")
    private String mood_image;

    @ColumnInfo(name = "mood_color")
    private String mood_color;

    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "updated")
    private long updated;


    @NonNull
    public int getMood_id() {
        return mood_id;
    }

    public String getMood_color() {
        return mood_color;
    }

    public void setMood_color(String mood_color) {
        this.mood_color = mood_color;
    }

    public void setMood_id(@NonNull int mood_id) {
        this.mood_id = mood_id;
    }

    public String getMood_name() {
        return mood_name;
    }

    public void setMood_name(String mood_name) {
        this.mood_name = mood_name;
    }

    public String getMood_image() {
        return mood_image;
    }

    public void setMood_image(String mood_image) {
        this.mood_image = mood_image;
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
