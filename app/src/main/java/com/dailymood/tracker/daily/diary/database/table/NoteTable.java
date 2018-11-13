package com.dailymood.tracker.daily.diary.database.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "note")
public class NoteTable
{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "note_id")
    private  int note_id;

    @ColumnInfo(name = "note_title")
    private String note_title;

    @ColumnInfo(name = "note_description")
    private String note_description;

    @ColumnInfo(name = "note_image")
    private String note_image;

    @ColumnInfo(name = "note_datetime")
    private long note_datetime;

    @ColumnInfo(name = "note_mode_id")
    private int  note_mode_id;

    @ColumnInfo(name = "note_activity_id")
    private String note_activity_id;

    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "updated")
    private long updated;


    @NonNull
    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(@NonNull int note_id) {
        this.note_id = note_id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_description() {
        return note_description;
    }

    public void setNote_description(String note_description) {
        this.note_description = note_description;
    }

    public String getNote_image() {
        return note_image;
    }

    public void setNote_image(String note_image) {
        this.note_image = note_image;
    }

    public long getNote_datetime() {
        return note_datetime;
    }

    public void setNote_datetime(long note_datetime) {
        this.note_datetime = note_datetime;
    }

    public int getNote_mode_id() {
        return note_mode_id;
    }

    public void setNote_mode_id(int note_mode_id) {
        this.note_mode_id = note_mode_id;
    }

    public String getNote_activity_id() {
        return note_activity_id;
    }

    public void setNote_activity_id(String note_activity_id) {
        this.note_activity_id = note_activity_id;
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
