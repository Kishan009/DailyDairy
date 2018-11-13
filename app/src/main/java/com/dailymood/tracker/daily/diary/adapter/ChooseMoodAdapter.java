package com.dailymood.tracker.daily.diary.adapter;

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
import com.dailymood.tracker.daily.diary.database.table.MoodTable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseMoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MoodTable> moodList;
    String[] emojiarray;
    ChooseMoodListner moodListner;

    public ChooseMoodAdapter(Context context, ChooseMoodListner moodListner) {
        this.context = context;
        this.moodListner = moodListner;
        emojiarray = context.getResources().getStringArray(R.array.emoji_array);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mood, viewGroup, false);
        return new ChooseMoodViewHolder(view);
    }

    public void setItems(List<MoodTable> moodTables) {
        moodList = moodTables;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ChooseMoodViewHolder holder = (ChooseMoodViewHolder) viewHolder;
        MoodTable moodTable = moodList.get(i);
        holder.moodname.setText(moodTable.getMood_name());
        holder.option.setVisibility(View.GONE);
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

    @Override
    public int getItemCount() {
        if (moodList != null)
            return moodList.size();
        else return 0;
    }

    public class ChooseMoodViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.moodname)
        TextView moodname;
        @BindView(R.id.emoji)
        ImageView emoji;
        @BindView(R.id.option)
        ImageView option;

        public ChooseMoodViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moodListner.moodClick(moodList.get(getLayoutPosition()));
                }
            });
        }
    }

    public interface ChooseMoodListner {
        void moodClick(MoodTable moodTable);
    }
}
