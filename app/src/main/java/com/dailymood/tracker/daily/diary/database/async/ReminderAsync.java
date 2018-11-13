package com.dailymood.tracker.daily.diary.database.async;

import android.os.AsyncTask;
import android.util.Log;

import com.dailymood.tracker.daily.diary.database.crud.ReminderDao;
import com.dailymood.tracker.daily.diary.database.table.ReminderTable;
import com.dailymood.tracker.daily.diary.util.OperationType;

public class ReminderAsync extends AsyncTask<Void, Void, Void> {
    ReminderDao reminderDao;
    ReminderTable reminderTable;
    String opearion;

    public ReminderAsync(ReminderDao reminderDao, ReminderTable reminderTable, String opearion) {
        this.reminderDao = reminderDao;
        this.reminderTable = reminderTable;
        this.opearion = opearion;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        if (opearion.equals(OperationType.Insert)) {
            long l = reminderDao.InsertReminder(reminderTable);
            Log.e("ReminderTable", l + "");
        } else if (opearion.equals(OperationType.Delete)) {
            reminderDao.DeleteReminder(reminderTable.getReminder_id());
        } else {
           int l =   reminderDao.UpdateReminder(reminderTable);
            Log.e("ReminderTable", l + "");
        }
        return null;
    }
}
