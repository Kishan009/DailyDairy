package com.dailymood.tracker.daily.diary.activity;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.dailymood.tracker.daily.diary.R;
import com.dailymood.tracker.daily.diary.adapter.ActivityImageAdapter;
import com.dailymood.tracker.daily.diary.database.Repository;
import com.dailymood.tracker.daily.diary.database.table.ActivityTable;
import com.dailymood.tracker.daily.diary.util.DataHouse;
import java.io.IOException;
import java.io.InputStream;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity implements ActivityImageAdapter.ActivityListner {


    @BindView(R.id.mainemoji)
    ImageView mainEmoji;
    @BindView(R.id.activityname)
    EditText activityName;
    @BindView(R.id.donebtn)
    ImageView doneBtn;
    @BindView(R.id.activityList)
    RecyclerView activityList;
    ActivityImageAdapter activityImageAdapter;
    int position = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        activityImageAdapter = new ActivityImageAdapter(this, DataHouse.getActivityList(), this);
        activityList.setLayoutManager(new GridLayoutManager(this, 5));
        activityList.setAdapter(activityImageAdapter);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    public void submit() {

        String name = activityName.getText().toString();
        if (name.isEmpty()) {
            activityName.setError("Enter Activity Name");
        } else {
            ActivityTable activityTable = new ActivityTable();
            activityTable.setActivity_image(String.valueOf(position));
            activityTable.setActivity_name(name);
            activityTable.setCreated(System.currentTimeMillis());
            activityTable.setUpdated(System.currentTimeMillis());
            Repository repository = new Repository(getApplication());
            repository.InsertActivity(activityTable);
            Toast.makeText(this, "Added Activity", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void activityClick(int Position) {
        this.position = Position;
        String path = DataHouse.getActivityList().get(Position);
        AssetManager assetManager = getAssets();
        try {
            InputStream istr = assetManager.open(path);
            Glide.with(this).load(BitmapFactory.decodeStream(istr))
                    .into(mainEmoji);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
