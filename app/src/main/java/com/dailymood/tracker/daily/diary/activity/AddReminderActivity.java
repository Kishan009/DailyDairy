package com.dailymood.tracker.daily.diary.activity;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dailymood.tracker.daily.diary.R;
import com.dailymood.tracker.daily.diary.database.Repository;
import com.dailymood.tracker.daily.diary.database.table.ReminderTable;
import com.dailymood.tracker.daily.diary.util.DateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddReminderActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.remindertitle)
    EditText RreminderTitle;
    @BindView(R.id.reminderdesc)
    EditText RreminderDesc;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.savebtn)
    LinearLayout savebtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    int Month, Day, Year, Hour, Minute;
    ReminderTable reminderTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        savebtn.setOnClickListener(this);

        if (getIntent().hasExtra("data")) {
            reminderTable = (ReminderTable) getIntent().getSerializableExtra("data");
            RreminderTitle.setText(reminderTable.getReminder_title());
            RreminderDesc.setText(reminderTable.getReminder_description());
            long timestamp = reminderTable.getReminder_datetime();
            String strDate = DateFormat.getday(timestamp) + " - " +
                    DateFormat.getMonth(timestamp) + " - " +
                    DateFormat.getYear(timestamp);
            date.setText(strDate);
            String strtime = DateFormat.getTime(timestamp);
            time.setText(strtime);
            Month = DateFormat.getMonth(timestamp);
            Day = DateFormat.getday(timestamp);
            Year = DateFormat.getYear(timestamp);
            Hour = DateFormat.getHour(timestamp);
            Minute = DateFormat.getMinute(timestamp);

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date:
                bringDate();
                break;

            case R.id.time:
                bringtime();
                break;
            case R.id.savebtn:
                submit();
                break;
        }
    }

    public void submit() {
        String title = RreminderTitle.getText().toString().trim();
        String desc = RreminderDesc.getText().toString().trim();
        if (title.isEmpty()) {
            RreminderTitle.setError("Enter Title");
        } else if (desc.isEmpty()) {
            RreminderDesc.setError("Enter Description");
        } else if (Year == 0 || Minute == 0) {
            Toast.makeText(this, "Select Date And Time", Toast.LENGTH_SHORT).show();

        } else {

            Repository repository = new Repository(getApplication());

            if (reminderTable != null) {
                reminderTable.setReminder_title(title);
                reminderTable.setReminder_description(desc);
                reminderTable.setReminder_datetime(DateFormat.getDate(Minute, Hour, Day, Month, Year).getTime());
                repository.UpdateReminder(reminderTable);
                Toast.makeText(this, "Reminder Updated", Toast.LENGTH_SHORT).show();
            } else {
                ReminderTable reminderTable2 = new ReminderTable();
                reminderTable2.setReminder_title(title);
                reminderTable2.setReminder_description(desc);
                reminderTable2.setReminder_datetime(DateFormat.getDate(Minute, Hour, Day, Month, Year).getTime());
                repository.InsertReminder(reminderTable2);
                Toast.makeText(this, "Reminder Added", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void bringDate() {

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year = year;
                Month = month + 1;
                Day = dayOfMonth;
                date.setText(Day + " - " + Month + " - " + Year);
            }
        }, 2018, 1, 1);

        dialog.show();
    }


    public void bringtime() {

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Minute = selectedMinute;
                Hour = selectedHour;

                if (selectedHour >= 12) {
                    String tim = selectedHour - 12 + ":" + selectedMinute + " PM";
                    time.setText(tim);
                } else {

                    String tim = selectedHour + ":" + selectedMinute + " AM";
                    time.setText(tim);
                }
            }
        }, 0, 0, false);//Yes 24 hour time
        mTimePicker.show();
    }
}
