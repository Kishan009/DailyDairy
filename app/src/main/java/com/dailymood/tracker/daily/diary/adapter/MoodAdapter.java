package com.dailymood.tracker.daily.diary.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dailymood.tracker.daily.diary.R;
import com.dailymood.tracker.daily.diary.database.Repository;
import com.dailymood.tracker.daily.diary.database.table.MoodTable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MoodTable> moodList;
    String[] emojiarray;
    public MoodAdapter(Context context) {
        this.context = context;
        emojiarray = context.getResources().getStringArray(R.array.emoji_array);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mood, viewGroup, false);
        return new MoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MoodViewHolder holder = (MoodViewHolder) viewHolder;
        MoodTable moodTable = moodList.get(i);
        holder.moodname.setText(moodTable.getMood_name());
        String path = emojiarray[Integer.parseInt(moodTable.getMood_image())];
        AssetManager assetManager = context.getAssets();
        try {
            InputStream istr = assetManager.open(path);
            Glide.with(context).load(BitmapFactory.decodeStream(istr))
                    .into(holder.emoji);
            Log.e("MoodTable", moodTable.getMood_color());
            holder.emoji.setColorFilter(Color.parseColor(moodTable.getMood_color()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setItems(List<MoodTable> moodTables)
    {
        moodList = moodTables;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (moodList != null)
            return moodList.size();
        else return 0;
    }

    public class MoodViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.moodname)
        TextView moodname;
        @BindView(R.id.emoji)
        ImageView emoji;
        @BindView(R.id.option)
        ImageView option;

        public MoodViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Repository repository = new Repository(((Activity) context).getApplication());
                    if (moodList != null) {
                        repository.DeleteMood(moodList.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}
