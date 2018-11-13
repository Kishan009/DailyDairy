package com.dailymood.tracker.daily.diary.database.crud;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.dailymood.tracker.daily.diary.database.table.ActivityTable;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface ActivityDao {

    @Insert(onConflict = IGNORE)
    long InsertActvity(ActivityTable activityTable);

    @Query("DELETE FROM activity WHERE activity_id = :id")
    void DeleteActivity(int id);

    @Query("select *from activity")
    LiveData<List<ActivityTable>> getAllActivity();

}
