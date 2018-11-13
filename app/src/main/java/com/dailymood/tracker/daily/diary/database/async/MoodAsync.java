package com.dailymood.tracker.daily.diary.database.async;

import android.os.AsyncTask;
import android.util.Log;

import com.dailymood.tracker.daily.diary.database.crud.MoodDao;
import com.dailymood.tracker.daily.diary.database.table.MoodTable;
import com.dailymood.tracker.daily.diary.util.OperationType;

public class MoodAsync extends AsyncTask<Void, Void, Void> {

    MoodDao moodDao;
    MoodTable moodTable;
    String operation;

    public MoodAsync(MoodDao moodDao, MoodTable moodTable, String operation) {
        this.moodDao = moodDao;
        this.moodTable = moodTable;
        this.operation = operation;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        if (operation.equals(OperationType.Insert)) {
            long id = moodDao.InsertMood(moodTable);
        } else if (operation.equals(OperationType.Delete)) {
            moodDao.DeleteMood(moodTable.getMood_id());
        }

        return null;
    }
}
