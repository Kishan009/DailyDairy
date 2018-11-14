package com.dailymood.tracker.daily.diary.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dailymood.tracker.daily.diary.R;
import com.dailymood.tracker.daily.diary.adapter.ImageAdapter;
import com.dailymood.tracker.daily.diary.database.Repository;
import com.dailymood.tracker.daily.diary.database.table.ActivityTable;
import com.dailymood.tracker.daily.diary.database.table.MoodTable;
import com.dailymood.tracker.daily.diary.database.table.NoteTable;
import com.dailymood.tracker.daily.diary.util.DateFormat;
import com.dailymood.tracker.daily.diary.util.RealPathUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HowAreYouActivty extends AppCompatActivity implements View.OnClickListener {


    BottomSheetBehavior sheetBehavior;
    @BindView(R.id.bottom_sheet)
    LinearLayout linearLayout;
    @BindView(R.id.bottomFab)
    FloatingActionButton bottomFab;
    @BindView(R.id.getImage)
    LinearLayout getImage;
    @BindView(R.id.getActivity)
    LinearLayout getActivity;
    @BindView(R.id.getmood)
    LinearLayout getmood;
    @BindView(R.id.date)
    TextView txtDate;
    @BindView(R.id.time)
    TextView txtTime;
    @BindView(R.id.imageList)
    RecyclerView imageList;
    @BindView(R.id.activityChoosed)
    TextView activityChoosed;
    @BindView(R.id.moodChoosed)
    TextView moodChoosed;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.description)
    EditText Inputdescription;
    @BindView(R.id.title)
    EditText Inputtitle;

    int Month, Day, Year, Hour, Minute;
    ImageAdapter imageAdapter;
    List<Uri> uriList = new ArrayList<Uri>();
    ArrayList<ActivityTable> tableList;
    MoodTable moodTable;
    List<String> activityID = new ArrayList<>();
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_are_you_activty);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sheetBehavior = BottomSheetBehavior.from(linearLayout);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        imageAdapter = new ImageAdapter(this);
        imageList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        imageList.setAdapter(imageAdapter);
        repository = new Repository(getApplication());
        setDateTime();
        txtDate.setOnClickListener(this);
        txtTime.setOnClickListener(this);
        bottomFab.setOnClickListener(this);
        getImage.setOnClickListener(this);
        getActivity.setOnClickListener(this);
        getmood.setOnClickListener(this);

    }

    public void setDateTime() {
        long datetime = System.currentTimeMillis();
        Day = DateFormat.getday(datetime);
        Year = DateFormat.getYear(datetime);
        Month = DateFormat.getMonth(datetime);
        Hour = DateFormat.getHour(datetime);
        Minute = DateFormat.getMinute(datetime);
        String date = Day + " - "
                + Month + " - " + Year;
        String time = DateFormat.getTime(datetime);
        txtDate.setText(date);
        txtTime.setText(time);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6666) {
            if (data != null) {
                uriList.clear();
                if (data.getClipData() != null) {
                    for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                        uriList.add(data.getClipData().getItemAt(i).getUri());
                    }
                    imageAdapter.setUris(uriList);
                } else {
                    uriList.add(data.getData());
                    imageAdapter.setUris(uriList);
                }
            }
        } else if (requestCode == 4444) {
            if (data != null) {
                if (data.getSerializableExtra("data") != null) {
                    activityID.clear();
                    tableList = (ArrayList<ActivityTable>) data.getSerializableExtra("data");
                    String activity = "";
                    for (int i = 0; i < tableList.size(); i++) {
                        activity = activity + "," + tableList.get(i).getActivity_name();
                        activityID.add("" + tableList.get(i).getActivity_id());
                    }
                    activityChoosed.setText(activity);
                }
            }
        } else if (requestCode == 5555) {
            if (data != null) {
                if (data.getSerializableExtra("data") != null) {
                    moodTable = (MoodTable) data.getSerializableExtra("data");
                    moodChoosed.setText(moodTable.getMood_name());
                }
            }
        }
    }


    public void hadleBottomSheet() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomFab.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            bottomFab.setImageDrawable(getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
        }
    }

    public void getDate() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month + 1;
                Day = dayOfMonth;
                txtDate.setText(Day + " - " + Month + " - " + Year);
            }
        }, 2100, 1, 1);
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        dialog.show();
    }

    public void getTime() {
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Minute = selectedMinute;
                Hour = selectedHour;
                if (selectedHour >= 12) {
                    String tim = selectedHour - 12 + ":" + selectedMinute + " PM";
                    txtTime.setText(tim);
                } else {
                    String tim = selectedHour + ":" + selectedMinute + " AM";
                    txtTime.setText(tim);
                }
            }
        }, 0, 0, false);//Yes 24 hour time
        mTimePicker.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottomFab:
                hadleBottomSheet();
                break;
            case R.id.getImage:
                getImages();
                break;
            case R.id.date:
                getDate();
                break;
            case R.id.time:
                getTime();
                break;
            case R.id.getActivity:
                getActivites();
                break;
            case R.id.getmood:
                getMoods();
                break;
        }
    }

    public void getMoods() {
        hadleBottomSheet();
        Intent intent = new Intent(this, ChooseMood.class);
        startActivityForResult(intent, 5555);
    }

    public void getActivites() {
        hadleBottomSheet();
        Intent intent = new Intent(this, ChooseActivity.class);
        startActivityForResult(intent, 4444);
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

    public void saveData() {
        String strtitle = Inputtitle.getText().toString();
        String strDesc = Inputdescription.getText().toString();

        if (strtitle.isEmpty()) {
            Inputtitle.setError("Set Title");
        } else if (strDesc.isEmpty()) {
            Inputdescription.setError("Set Description");
        } else if (moodTable == null) {
            Toast.makeText(this, "Choose The mood", Toast.LENGTH_SHORT).show();
        } else {
            NoteTable noteTable = new NoteTable();
            noteTable.setNote_title(strtitle);
            noteTable.setNote_description(strDesc);
            noteTable.setNote_mode_id(moodTable.getMood_id());
            String act = activityID.toString().replace("[", "").replace("]", "");
            noteTable.setNote_activity_id(act);
            noteTable.setNote_datetime(DateFormat.getDate(Minute, Hour, Day, Month, Year).getTime());
            repository.InsertNote(noteTable);
            for (Uri uri : uriList) {
                copyFile(RealPathUtils.getRealPathFromURI_API19(HowAreYouActivty.this, uri));
            }

        }


    }

    public void getImages() {
        hadleBottomSheet();
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 6666);
    }


    @Override
    protected void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
    }


    public void copyFile(String s) {

        try {

            File sourceLocation = new File(s);

            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath() + "/New App");
            dir.mkdirs();
            File targetLocation = new File(dir, sourceLocation.getName());

            if (sourceLocation.exists()) {

                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();
                Log.e("FileCopied", "Done");

            } else {
                Log.e("FileCopied", "Location not found");
            }

        } catch (java.io.IOException e) {
            Log.e("FileCopied", e.getMessage());
        }
    }

}
