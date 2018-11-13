package com.dailymood.tracker.daily.diary.database.crud;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dailymood.tracker.daily.diary.database.table.MoodTable;
import com.dailymood.tracker.daily.diary.database.table.ReminderTable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface ReminderDao
{
    @Insert(onConflict = IGNORE)
     long InsertReminder(ReminderTable reminderTable);

    @Query("DELETE FROM reminder WHERE reminder_id = :id")
    void  DeleteReminder(int id);

    @Update(onConflict = REPLACE)
    int UpdateReminder(ReminderTable reminderTable);

    @Query("SELECT * FROM reminder WHERE reminder_id = :id")
    LiveData<ReminderTable> GetReminder(int id);

    @Query("SELECT * FROM reminder")
    LiveData<List<ReminderTable>> GetAllReminder();



}
