package com.dailymood.tracker.daily.diary.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.dailymood.tracker.daily.diary.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ActivityImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context context;
    List<String> activitList;
    ActivityListner activityListner;
    public ActivityImageAdapter(Context context, List<String> activitList, ActivityListner activityListner)
    {
        this.context = context;
        this.activitList = activitList;
        this.activityListner =activityListner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_emoji, viewGroup, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String path = activitList.get(i);
        ActivityViewHolder holder = (ActivityViewHolder) viewHolder;
        AssetManager assetManager = context.getAssets();
        try {
            InputStream istr = assetManager.open(path);
            Glide.with(context).load(BitmapFactory.decodeStream(istr))
                    .into(holder.imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return activitList.size();
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.emojiView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activityListner.activityClick(getLayoutPosition());
                }
            });
        }
    }
    public interface ActivityListner
    {
        void activityClick(int Position);
    }
}
