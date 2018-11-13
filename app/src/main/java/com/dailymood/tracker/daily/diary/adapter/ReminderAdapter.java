package com.dailymood.tracker.daily.diary.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.dailymood.tracker.daily.diary.R;
import com.dailymood.tracker.daily.diary.activity.AddReminderActivity;
import com.dailymood.tracker.daily.diary.database.Repository;
import com.dailymood.tracker.daily.diary.database.table.ReminderTable;
import com.dailymood.tracker.daily.diary.util.DateFormat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReminderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<ReminderTable> reminderList;

    public ReminderAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reminder, viewGroup, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ReminderTable reminderTable = reminderList.get(i);
        ReminderViewHolder holder = (ReminderViewHolder) viewHolder;
        holder.Title.setText(reminderTable.getReminder_title());
        holder.desc.setText(reminderTable.getReminder_description());
        holder.date.setText(DateFormat.getBriefDate(reminderTable.getReminder_datetime()));
        holder.time.setText(DateFormat.getTime(reminderTable.getReminder_datetime()));
    }

    @Override
    public int getItemCount() {
        if (reminderList != null) {
            return reminderList.size();
        } else {
            return 0;
        }
    }

    public void setItems(List<ReminderTable> items) {
        reminderList = items;
        notifyDataSetChanged();
    }

    public class ReminderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Title)
        TextView Title;
        @BindView(R.id.desc)
        TextView desc;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.option)
        ImageView option;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(context, option);
                    popup.getMenuInflater().inflate(R.menu.reminder_option, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            if (item.getItemId() == R.id.action_delete) {
                                Repository repository = new Repository(((Activity) context).getApplication());
                                repository.DeleteReminder(reminderList.get(getLayoutPosition()));
                            } else {
                                Intent intent = new Intent(context, AddReminderActivity.class);
                                intent.putExtra("data", reminderList.get(getLayoutPosition()));
                                context.startActivity(intent);
                            }
                            return false;
                        }
                    });

                    popup.show();

                }
            });
        }
    }
}
