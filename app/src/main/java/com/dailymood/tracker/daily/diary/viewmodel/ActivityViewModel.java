package com.dailymood.tracker.daily.diary.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dailymood.tracker.daily.diary.database.Repository;
import com.dailymood.tracker.daily.diary.database.table.ActivityTable;

import java.util.List;

public class ActivityViewModel extends AndroidViewModel {
    private Repository repository;

    public ActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<ActivityTable>> getActivity() {
        return repository.getAllActivity();
    }
}
