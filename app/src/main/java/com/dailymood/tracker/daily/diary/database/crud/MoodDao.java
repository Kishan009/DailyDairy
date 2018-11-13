package com.dailymood.tracker.daily.diary.database.crud;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.dailymood.tracker.daily.diary.database.table.MoodTable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface MoodDao
{

    @Insert(onConflict = IGNORE)
    long InsertMood(MoodTable moodTable);

    @Query("DELETE FROM mood WHERE mood_id = :id")
    void  DeleteMood(int id);

    @Query("select * from mood")
    LiveData<List<MoodTable>> getAllMood();


}
