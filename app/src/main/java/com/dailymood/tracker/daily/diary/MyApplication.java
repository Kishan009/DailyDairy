package com.dailymood.tracker.daily.diary;

import android.app.Application;
import com.dailymood.tracker.daily.diary.database.Repository;

public class MyApplication extends Application {
    public static Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        repository = new Repository(this);
    }
}
