package com.dailymood.tracker.daily.diary.activity;

import android.app.Activity;
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
import com.dailymood.tracker.daily.diary.adapter.ActivityAdapter;
import com.dailymood.tracker.daily.diary.database.table.ActivityTable;
import com.dailymood.tracker.daily.diary.viewmodel.ActivityViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityList extends AppCompatActivity {

    @BindView(R.id.activityList)
    RecyclerView activityList;
    @BindView(R.id.addActivity)
    FloatingActionButton addActivity;
    ActivityAdapter activityAdapter;
    ActivityViewModel activityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel.class);
        activityAdapter = new ActivityAdapter(this);
        activityList.setLayoutManager(new LinearLayoutManager(this));
        activityList.setAdapter(activityAdapter);


        activityViewModel.getActivity().observe(this, new Observer<List<ActivityTable>>() {
            @Override
            public void onChanged(@Nullable List<ActivityTable> activityTables) {
                activityAdapter.setItems(activityTables);
            }
        });
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityList.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
}
