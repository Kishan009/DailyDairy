package com.dailymood.tracker.daily.diary.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dailymood.tracker.daily.diary.database.Repository;
import com.dailymood.tracker.daily.diary.database.table.ReminderTable;

import java.util.List;

public class ReminderViewModel extends AndroidViewModel {

    private Repository repository;

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<ReminderTable>> getReminderList() {
        return repository.getReminderList();
    }
}
