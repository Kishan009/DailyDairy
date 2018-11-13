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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dailymood.tracker.daily.diary.R;
import com.dailymood.tracker.daily.diary.adapter.ChooseActivityAdapter;
import com.dailymood.tracker.daily.diary.database.table.ActivityTable;
import com.dailymood.tracker.daily.diary.viewmodel.ActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseActivity extends AppCompatActivity implements ChooseActivityAdapter.ChooseActivityListner {

    ActivityViewModel activityViewModel;
    @BindView(R.id.activityList)
    RecyclerView activityList;
    ChooseActivityAdapter chooseActivityAdapter;
    @BindView(R.id.addActivity)
    FloatingActionButton addActivity;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ArrayList<ActivityTable> activityTables = new ArrayList<ActivityTable>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel.class);
        chooseActivityAdapter = new ChooseActivityAdapter(this, this::chooseClick);
        activityList.setLayoutManager(new LinearLayoutManager(this));
        activityList.setAdapter(chooseActivityAdapter);
        activityViewModel.getActivity().observe(this, new Observer<List<ActivityTable>>() {
            @Override
            public void onChanged(@Nullable List<ActivityTable> activityTables) {
                chooseActivityAdapter.setItems(activityTables);
            }
        });
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            saveData();
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void chooseClick(ActivityTable activityTable, boolean types) {
        if (types) {
            activityTables.add(activityTable);
        } else {
            activityTables.remove(activityTable);
        }
    }

    public void saveData() {
        Toast.makeText(this, "save Data", Toast.LENGTH_SHORT).show();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("data", activityTables);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
