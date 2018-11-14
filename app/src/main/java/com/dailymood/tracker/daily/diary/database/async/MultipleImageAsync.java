package com.dailymood.tracker.daily.diary.database.async;

import android.os.AsyncTask;
import android.util.Log;
import com.dailymood.tracker.daily.diary.MyApplication;
import com.dailymood.tracker.daily.diary.database.crud.MultiIplemageDao;
import com.dailymood.tracker.daily.diary.database.table.MultipleImageTable;
import com.dailymood.tracker.daily.diary.util.OperationType;


public class MultipleImageAsync extends AsyncTask<Void, Void, Void> {
    MultiIplemageDao multiIplemageDao;
    MultipleImageTable imageTableList;
    String Opeation;

    public MultipleImageAsync(MultiIplemageDao multiIplemageDao, MultipleImageTable imageTableList, String opeation) {
        this.multiIplemageDao = multiIplemageDao;
        this.imageTableList = imageTableList;
        Opeation = opeation;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (Opeation.equals(OperationType.Insert)) {
            long l = multiIplemageDao.InsertImage(imageTableList);
            Log.e("FileCopied", l + " " + MyApplication.noteId + " " + imageTableList.getImage_path());
        }
        return null;
    }
}
