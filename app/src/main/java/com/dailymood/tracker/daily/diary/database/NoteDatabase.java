package com.dailymood.tracker.daily.diary.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.dailymood.tracker.daily.diary.database.crud.ActivityDao;
import com.dailymood.tracker.daily.diary.database.crud.MoodDao;
import com.dailymood.tracker.daily.diary.database.crud.MultiIplemageDao;
import com.dailymood.tracker.daily.diary.database.crud.NoteDao;
import com.dailymood.tracker.daily.diary.database.crud.ReminderDao;
import com.dailymood.tracker.daily.diary.database.table.ActivityTable;
import com.dailymood.tracker.daily.diary.database.table.MoodTable;
import com.dailymood.tracker.daily.diary.database.table.MultipleImageTable;
import com.dailymood.tracker.daily.diary.database.table.NoteTable;
import com.dailymood.tracker.daily.diary.database.table.ReminderTable;

@Database(entities = {
        ActivityTable.class,
        MoodTable.class,
        MultipleImageTable.class,
        NoteTable.class,
        ReminderTable.class
}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract ActivityDao activityDao();

    public abstract MoodDao moodDao();

    public abstract MultiIplemageDao multiIplemageDao();

    public abstract NoteDao noteDao();

    public abstract ReminderDao reminderDao();

    private static NoteDatabase Instance;

    static NoteDatabase getInstance(final Context context) {
        if (Instance == null) {
            synchronized (RoomDatabase.class) {
                if (Instance == null) {
                    Instance = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "goal_database").build();
                }
            }
        }
        return Instance;
    }


}