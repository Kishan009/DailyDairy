package com.dailymood.tracker.daily.diary.database.async;

import android.os.AsyncTask;
import android.util.Log;

import com.dailymood.tracker.daily.diary.database.crud.ActivityDao;
import com.dailymood.tracker.daily.diary.database.table.ActivityTable;
import com.dailymood.tracker.daily.diary.util.OperationType;

public class ActivityAsync extends AsyncTask<Void, Void, Void> {
    ActivityDao activityDao;
    ActivityTable activityTable;
    String operation;

    public ActivityAsync(ActivityDao activityDao, ActivityTable activityTable, String operation) {
        this.activityDao = activityDao;
        this.activityTable = activityTable;
        this.operation = operation;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        if (operation.equals(OperationType.Insert)) {
            long l = activityDao.InsertActvity(activityTable);
            Log.e("ActivtyTable", "" + l);
        } else if (operation.equals(OperationType.Delete)) {
            activityDao.DeleteActivity(activityTable.getActivity_id());
        }

        return null;
    }
}
