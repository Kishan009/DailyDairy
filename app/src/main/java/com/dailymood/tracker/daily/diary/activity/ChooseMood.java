package com.dailymood.tracker.daily.diary.activity;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;

import com.dailymood.tracker.daily.diary.R;
import com.dailymood.tracker.daily.diary.adapter.ChooseActivityAdapter;
import com.dailymood.tracker.daily.diary.adapter.ChooseMoodAdapter;
import com.dailymood.tracker.daily.diary.database.table.MoodTable;
import com.dailymood.tracker.daily.diary.viewmodel.MoodViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseMood extends AppCompatActivity implements ChooseMoodAdapter.ChooseMoodListner {

    @BindView(R.id.addMood)
    FloatingActionButton addMood;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.moodList)
    RecyclerView moodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mood);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ChooseMoodAdapter chooseMoodAdapter = new ChooseMoodAdapter(this, this::moodClick);
        moodList.setLayoutManager(new GridLayoutManager(this, 2));
        moodList.setAdapter(chooseMoodAdapter);
        MoodViewModel moodViewModel = ViewModelProviders.of(this).get(MoodViewModel.class);
        moodViewModel.getMoodList().observe(this, new Observer<List<MoodTable>>() {
            @Override
            public void onChanged(@Nullable List<MoodTable> moodTables) {
                chooseMoodAdapter.setItems(moodTables);
            }
        });
        addMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseMood.this, MoodActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void moodClick(MoodTable moodTable)
    {
        Intent intent = new Intent();
        intent.putExtra("data", moodTable);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
