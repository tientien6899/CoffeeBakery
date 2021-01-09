package com.example.coffeebakery.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeebakery.R;

import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.Holder> {
    Context context;
    private List<Poster> listPoster;

    public PosterAdapter(Context context, List<Poster> posterList){
        this.context = context;
        this.listPoster = posterList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_poster, parent, false);
        return new PosterAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Poster p = listPoster.get(position);
        holder.image.setImageResource(p.getImage());
    }

    @Override
    public int getItemCount() {
        return listPoster.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        public Holder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_Poster);
        }
    }
}
