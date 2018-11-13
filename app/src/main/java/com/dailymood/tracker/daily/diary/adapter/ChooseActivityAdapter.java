package com.dailymood.tracker.daily.diary.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dailymood.tracker.daily.diary.R;
import com.dailymood.tracker.daily.diary.database.table.ActivityTable;
import com.dailymood.tracker.daily.diary.util.DataHouse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<ActivityTable> activityTableList;
    public ChooseActivityListner activityListner;

    public ChooseActivityAdapter(Context context, ChooseActivityListner listner)
    {
        this.context = context;
        this.activityListner =listner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activity, viewGroup, false);
        return new ChooseActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ChooseActivityViewHolder holder = (ChooseActivityViewHolder) viewHolder;
        ActivityTable activityTable = activityTableList.get(i);
        String path = DataHouse.getActivityList().get(Integer.parseInt(activityTable.getActivity_image()));
        AssetManager assetManager = context.getAssets();
        try {
            InputStream istr = assetManager.open(path);
            Glide.with(context).load(BitmapFactory.decodeStream(istr))
                    .into(holder.activityImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.option.setImageDrawable(context.getDrawable(R.drawable.done));
        holder.option.setVisibility(View.GONE);
        holder.activityName.setText(activityTable.getActivity_name());

    }

    @Override
    public int getItemCount() {
        if (activityTableList != null) {
            return activityTableList.size();
        } else {
            return 0;
        }
    }



    public void setItems(List<ActivityTable> activityTableList) {
        this.activityTableList = activityTableList;
        notifyDataSetChanged();
    }

    public class ChooseActivityViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.activityImage)
        ImageView activityImage;
        @BindView(R.id.activityName)
        TextView activityName;
        @BindView(R.id.option)
        ImageView option;

        public ChooseActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    if (option.getVisibility() == View.GONE) {
                        activityListner.chooseClick(activityTableList.get(getLayoutPosition()),true);
                        option.setVisibility(View.VISIBLE);

                    } else {
                        activityListner.chooseClick(activityTableList.get(getLayoutPosition()),false);
                        option.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
    public interface  ChooseActivityListner {
        void chooseClick(ActivityTable activityTable,boolean types);
    }
}
