package com.dailymood.tracker.daily.diary.database.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "reminder")
public class ReminderTable implements Serializable
{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "reminder_id")
    private  int reminder_id;

    @ColumnInfo(name = "reminder_title")
    private String reminder_title;

    @ColumnInfo(name = "reminder_description")
    private String reminder_description;

    @ColumnInfo(name = "reminder_image")
    private String reminder_image;

    @ColumnInfo(name = "reminder_datetime")
    private long reminder_datetime;

    @ColumnInfo(name = "created")
    private long created;

    @ColumnInfo(name = "updated")
    private long updated;

    @NonNull
    public int getReminder_id() {
        return reminder_id;
    }

    public void setReminder_id(@NonNull int reminder_id) {
        this.reminder_id = reminder_id;
    }

    public String getReminder_title() {
        return reminder_title;
    }

    public void setReminder_title(String reminder_title) {
        this.reminder_title = reminder_title;
    }

    public String getReminder_description() {
        return reminder_description;
    }

    public void setReminder_description(String reminder_description) {
        this.reminder_description = reminder_description;
    }

    public String getReminder_image() {
        return reminder_image;
    }

    public void setReminder_image(String reminder_image) {
        this.reminder_image = reminder_image;
    }

    public long getReminder_datetime() {
        return reminder_datetime;
    }

    public void setReminder_datetime(long reminder_datetime) {
        this.reminder_datetime = reminder_datetime;
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
