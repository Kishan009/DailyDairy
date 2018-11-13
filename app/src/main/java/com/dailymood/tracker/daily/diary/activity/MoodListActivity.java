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
import com.dailymood.tracker.daily.diary.adapter.MoodAdapter;
import com.dailymood.tracker.daily.diary.database.crud.MoodDao;
import com.dailymood.tracker.daily.diary.database.table.MoodTable;
import com.dailymood.tracker.daily.diary.viewmodel.MoodViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoodListActivity extends AppCompatActivity {


    @BindView(R.id.moodList)
    RecyclerView moodList;
    @BindView(R.id.addMood)
    FloatingActionButton addMood;
    MoodViewModel moodViewModel;
    MoodAdapter moodAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_list);
        ButterKnife.bind(this);
        moodAdapter = new MoodAdapter(this);
        moodList.setLayoutManager(new LinearLayoutManager(this));
        moodList.setAdapter(moodAdapter);

        moodViewModel = ViewModelProviders.of(this).get(MoodViewModel.class);
        moodViewModel.getMoodList().observe(this, new Observer<List<MoodTable>>() {
            @Override
            public void onChanged(@Nullable List<MoodTable> moodTables) {

                moodAdapter.setItems(moodTables);
            }
        });
        addMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoodListActivity.this, MoodActivity.class);
                startActivity(intent);
            }
        });
    }
}
