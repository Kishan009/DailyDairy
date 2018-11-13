package com.dailymood.tracker.daily.diary.database.crud;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dailymood.tracker.daily.diary.database.table.NoteTable;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDao
{
    @Insert(onConflict = IGNORE)
    long InsertNote(NoteTable noteTable);

    @Update (onConflict = REPLACE)
    void UpdateNote(NoteTable noteTable);

    @Query("Delete from note where note_id = :id")
    void  DeleteNote(int id);



}
