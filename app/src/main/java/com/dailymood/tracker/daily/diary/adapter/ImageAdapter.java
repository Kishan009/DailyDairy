package com.dailymood.tracker.daily.diary.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dailymood.tracker.daily.diary.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Uri> uriList;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, viewGroup, false);
        return new ImageViewGHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ImageViewGHolder holder = (ImageViewGHolder) viewHolder;
        Glide.with(context).load(uriList.get(i)).into(holder.imageView);
    }

    public void setUris(List<Uri> uris) {
        this.uriList = uris;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (uriList != null) {
            return uriList.size();
        } else {
            return 0;
        }

    }

    public class ImageViewGHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImageViewGHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
