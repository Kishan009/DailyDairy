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
import java.io.IOException;
import java.io.InputStream;

public class EmojiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context context;
    String[] emojiarray;
    EmojiListner emojiListner;

    public EmojiAdapter(Context context, String[] emojiarray, EmojiListner emojiListner) {
        this.context = context;
        this.emojiarray = emojiarray;
        this.emojiListner = emojiListner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(com.dailymood.tracker.daily.diary.R.layout.item_emoji, viewGroup, false);
        return new EmojiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String path = emojiarray[i];
        EmojiViewHolder holder = (EmojiViewHolder) viewHolder;
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
        return emojiarray.length;
    }

    public class EmojiViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public EmojiViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(com.dailymood.tracker.daily.diary.R.id.emojiView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emojiListner.emojiClick(getLayoutPosition());
                }
            });
        }
    }

    public interface EmojiListner {
        void emojiClick(int postion);
    }
}