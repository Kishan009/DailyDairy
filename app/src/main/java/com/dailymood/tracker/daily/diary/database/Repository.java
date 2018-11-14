package com.dailymood.tracker.daily.diary.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.dailymood.tracker.daily.diary.database.async.ActivityAsync;
import com.dailymood.tracker.daily.diary.database.async.MoodAsync;
import com.dailymood.tracker.daily.diary.database.async.MultipleImageAsync;
import com.dailymood.tracker.daily.diary.database.async.NoteAsync;
import com.dailymood.tracker.daily.diary.database.async.ReminderAsync;
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
import com.dailymood.tracker.daily.diary.util.OperationType;

import java.util.List;

public class Repository {
    private ActivityDao activityDao;
    private MoodDao moodDao;
    private ReminderDao reminderDao;
    private NoteDao noteDao;
    private MultiIplemageDao multiIplemageDao;

    public Repository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        this.activityDao = database.activityDao();
        this.moodDao = database.moodDao();
        this.reminderDao = database.reminderDao();
        this.noteDao = database.noteDao();
        this.multiIplemageDao = database.multiIplemageDao();
    }

    public LiveData<List<MoodTable>> getAllMood() {
        return moodDao.getAllMood();
    }

    public void InsertMood(MoodTable moodTable) {
        new MoodAsync(moodDao, moodTable, OperationType.Insert).execute();
    }

    public void DeleteMood(MoodTable moodTable) {
        new MoodAsync(moodDao, moodTable, OperationType.Delete).execute();
    }

    public LiveData<List<ActivityTable>> getAllActivity() {
        return activityDao.getAllActivity();
    }

    public void InsertActivity(ActivityTable activityTable) {
        new ActivityAsync(activityDao, activityTable, OperationType.Insert).execute();
    }

    public void DeleteActivity(ActivityTable activityTable) {
        new ActivityAsync(activityDao, activityTable, OperationType.Delete).execute();
    }

    public void InsertReminder(ReminderTable reminderTable) {
        new ReminderAsync(reminderDao, reminderTable, OperationType.Insert).execute();
    }

    public void DeleteReminder(ReminderTable reminderTable) {
        new ReminderAsync(reminderDao, reminderTable, OperationType.Delete).execute();
    }

    public void UpdateReminder(ReminderTable reminderTable) {
        new ReminderAsync(reminderDao, reminderTable, OperationType.Update).execute();
    }

    public void InsertNote(NoteTable noteTable) {
        new NoteAsync(noteDao, noteTable, OperationType.Insert).execute();
    }

    public LiveData<List<ReminderTable>> getReminderList() {
        return reminderDao.GetAllReminder();
    }
    public void InsertMultipleImage(MultipleImageTable multipleImageTables) {
        new MultipleImageAsync(multiIplemageDao, multipleImageTables, OperationType.Insert).execute();
    }

}
