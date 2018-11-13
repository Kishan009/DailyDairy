package com.dailymood.tracker.daily.diary.activity;


import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.dailymood.tracker.daily.diary.adapter.EmojiAdapter;
import com.dailymood.tracker.daily.diary.database.Repository;
import com.dailymood.tracker.daily.diary.database.table.MoodTable;
import com.dailymood.tracker.daily.diary.util.ColorType;
import java.io.IOException;
import java.io.InputStream;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoodActivity extends AppCompatActivity implements EmojiAdapter.EmojiListner, View.OnClickListener {

    @BindView(R.id.moodList)
    RecyclerView moodList;
    @BindView(R.id.redcircle)
    ImageView redcircle;
    @BindView(R.id.graycircle)
    ImageView graycircle;
    @BindView(R.id.bluecircle)
    ImageView bluecircle;
    @BindView(R.id.purplecircle)
    ImageView purplecircle;
    @BindView(R.id.greencircle)
    ImageView greencircle;
    @BindView(R.id.mainemoji)
    ImageView mainemoji;
    @BindView(R.id.donebtn)
    ImageView donebtn;
    @BindView(R.id.moodname)
    EditText moodName;
    EmojiAdapter emojiAdapter;
    String[] emojiarray;
    int color, position;
    Repository repository;
    String ColorString = ColorType.gray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        ButterKnife.bind(this);

        repository = new Repository(getApplication());

        emojiarray = getResources().getStringArray(R.array.emoji_array);
        moodList.setLayoutManager(new GridLayoutManager(this, 5));
        emojiAdapter = new EmojiAdapter(this, emojiarray, this);
        moodList.setAdapter(emojiAdapter);
        redcircle.setOnClickListener(this);
        purplecircle.setOnClickListener(this);
        greencircle.setOnClickListener(this);
        bluecircle.setOnClickListener(this);
        graycircle.setOnClickListener(this);
        donebtn.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.redcircle:
                setdefault();
                mainemoji.setColorFilter(Color.parseColor(ColorType.red));
                color = Color.parseColor(ColorType.red);
                ColorString = ColorType.red;
                redcircle.setForeground(getResources().getDrawable(R.drawable.ic_done_black_24dp));
                break;

            case R.id.greencircle:
                setdefault();
                mainemoji.setColorFilter(Color.parseColor(ColorType.green));
                color = Color.parseColor(ColorType.green);
                ColorString = ColorType.green;
                greencircle.setForeground(getResources().getDrawable(R.drawable.ic_done_black_24dp));
                break;

            case R.id.bluecircle:
                setdefault();
                mainemoji.setColorFilter(Color.parseColor(ColorType.blue));
                color = Color.parseColor(ColorType.blue);
                ColorString = ColorType.blue;
                bluecircle.setForeground(getDrawable(R.drawable.ic_done_black_24dp));
                break;

            case R.id.graycircle:
                setdefault();
                mainemoji.setColorFilter(Color.parseColor(ColorType.gray));
                color = Color.parseColor(ColorType.gray);
                ColorString = ColorType.gray;
                graycircle.setForeground(getDrawable(R.drawable.ic_done_black_24dp));
                break;

            case R.id.purplecircle:
                setdefault();
                mainemoji.setColorFilter(Color.parseColor(ColorType.purple));
                color = Color.parseColor(ColorType.purple);
                ColorString = ColorType.purple;
                purplecircle.setForeground(getResources().getDrawable(R.drawable.ic_done_black_24dp));
                break;

            case R.id.donebtn:
                submit();
                break;
        }
    }

    public void submit() {
        String mood = moodName.getText().toString().trim();
        if (mood.isEmpty()) {
            moodName.setError("Enter Mood");
        } else {
            MoodTable moodTable = new MoodTable();
            moodTable.setMood_image(String.valueOf(position));
            moodTable.setMood_color(ColorString);
            moodTable.setMood_name(mood);
            moodTable.setCreated(System.currentTimeMillis());
            moodTable.setUpdated(System.currentTimeMillis());
            repository.InsertMood(moodTable);
            Toast.makeText(this, moodTable.getMood_name(), Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void emojiClick(int color, int position) {
        setdefault();
        color = Color.parseColor(ColorType.gray);
        this.position = position;
        AssetManager assetManager = getAssets();
        try {
            InputStream istr = assetManager.open(emojiarray[position]);
            Glide.with(this).load(BitmapFactory.decodeStream(istr))
                    .into(mainemoji);
            mainemoji.setColorFilter(color);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setdefault() {
        redcircle.setForeground(null);
        purplecircle.setForeground(null);
        graycircle.setForeground(null);
        greencircle.setForeground(null);
        bluecircle.setForeground(null);
    }


}
