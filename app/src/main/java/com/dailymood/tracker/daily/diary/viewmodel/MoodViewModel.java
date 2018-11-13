package com.dailymood.tracker.daily.diary.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.dailymood.tracker.daily.diary.MyApplication;
import com.dailymood.tracker.daily.diary.database.Repository;
import com.dailymood.tracker.daily.diary.database.table.MoodTable;

import java.util.List;

public class MoodViewModel extends AndroidViewModel {


    private Repository repository;

    public MoodViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<MoodTable>> getMoodList() {

        return repository.getAllMood();
    }
}
