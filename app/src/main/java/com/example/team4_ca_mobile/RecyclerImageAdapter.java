package com.example.team4_ca_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerImageAdapter extends RecyclerView.Adapter<RecyclerImageAdapter.ViewHolder> {

    private ArrayList<RecyclerImage> recyclerImages;

    public RecyclerImageAdapter(ArrayList<RecyclerImage> recyclerImages) {
        this.recyclerImages = recyclerImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerImage recyclerImage = recyclerImages.get(position);

        Picasso.get().load(recyclerImage.getImageURL()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        if(recyclerImages != null) {
            return recyclerImages.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final ImageView image;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            image = view.findViewById(R.id.singleImage);
        }
    }
}
