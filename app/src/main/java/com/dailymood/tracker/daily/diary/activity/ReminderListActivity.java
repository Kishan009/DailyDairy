package com.dailymood.tracker.daily.diary.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dailymood.tracker.daily.diary.R;
import com.dailymood.tracker.daily.diary.adapter.ReminderAdapter;
import com.dailymood.tracker.daily.diary.database.table.ReminderTable;
import com.dailymood.tracker.daily.diary.viewmodel.ReminderViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReminderListActivity extends AppCompatActivity {


    @BindView(R.id.addReminder)
    FloatingActionButton addReminder;

    @BindView(R.id.ReminderList)
    RecyclerView reminderList;

    ReminderAdapter reminderAdapter;
    ReminderViewModel reminderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        ButterKnife.bind(this);
        reminderAdapter = new ReminderAdapter(this);
        reminderList.setLayoutManager(new LinearLayoutManager(this));
        reminderList.setAdapter(reminderAdapter);
        reminderViewModel = ViewModelProviders.of(this).get(ReminderViewModel.class);
        reminderViewModel.getReminderList().observe(this, new Observer<List<ReminderTable>>() {
            @Override
            public void onChanged(@Nullable List<ReminderTable> reminderTables) {

                reminderAdapter.setItems(reminderTables);
            }
        });

        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReminderListActivity.this, AddReminderActivity.class);
                startActivity(intent);
            }
        });


    }
}
