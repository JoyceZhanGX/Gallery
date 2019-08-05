package com.example.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<String> imageUrls;
    private Context context;

    public ImageAdapter(Context context, ArrayList<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;

    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * gets the image url from adapter and passes to Glide API to load the image
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Glide.with(context).load(imageUrls.get(i)).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imageView);
        }
    }
}