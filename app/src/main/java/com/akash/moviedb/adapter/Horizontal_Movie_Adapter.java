package com.akash.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akash.moviedb.R;
import com.akash.moviedb.model.Movie;
import com.akash.moviedb.view.DetailActivity;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Horizontal_Movie_Adapter extends RecyclerView.Adapter<Horizontal_Movie_Adapter.Horizontal_Movie_AdapterHolder>{
    private Context context;
    private List<Movie> movieArrayList;

    public Horizontal_Movie_Adapter(Context context, List<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }
    @Override
    @NonNull
    public Horizontal_Movie_Adapter.Horizontal_Movie_AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.box_cardview,parent,false);
        return new Horizontal_Movie_Adapter.Horizontal_Movie_AdapterHolder(view);
    }

@Override
    public void onBindViewHolder(@NonNull Horizontal_Movie_Adapter.Horizontal_Movie_AdapterHolder holder, final int position) {

        holder.movieTitle.setText(movieArrayList.get(position).getOriginalTitle());

        String imagePath="https://image.tmdb.org/t/p/w500"+movieArrayList.get(position).getPosterPath();

        Picasso.get()
                .load(imagePath)
                .fit()
                .placeholder(R.drawable.load)
                .into(holder.movieImage);





    }
    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }


    public class Horizontal_Movie_AdapterHolder extends RecyclerView.ViewHolder {


        public TextView movieTitle;
        public ImageView movieImage;


        public Horizontal_Movie_AdapterHolder(View itemView) {
            super(itemView);

            movieImage = (ImageView) itemView.findViewById(R.id.image_views);

            movieTitle = (TextView) itemView.findViewById(R.id.movie_names);




        }
    }
}
