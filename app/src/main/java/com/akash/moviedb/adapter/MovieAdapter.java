package com.akash.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akash.moviedb.R;
import com.akash.moviedb.model.Movie;
import com.akash.moviedb.view.DetailActivity;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private Context context;
    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {

        holder.movieTitle.setText(movieArrayList.get(position).getOriginalTitle());
        double longValue = movieArrayList.get(position).getVoteAverage();
        holder.movie_rating.setRating( Float.parseFloat(String.format("%.1f", longValue/2)));
        holder.date.setText(movieArrayList.get(position).getReleaseDate());


        String imagePath="https://image.tmdb.org/t/p/w500"+movieArrayList.get(position).getPosterPath();

        Picasso.get()
                .load(imagePath)
                .fit()
                .placeholder(R.drawable.load)
                .into(holder.movieImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(context, ""+movieArrayList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image", "https://image.tmdb.org/t/p/w500"+movieArrayList.get(position).getPosterPath());
                intent.putExtra("title",movieArrayList.get(position).getTitle());
                intent.putExtra("overview",movieArrayList.get(position).getOverview());
                intent.putExtra("vote_average",movieArrayList.get(position).getVoteAverage());
                intent.putExtra("release_date",movieArrayList.get(position).getReleaseDate());
                intent.putExtra("original_language",movieArrayList.get(position).getOriginalLanguage());
                intent.putExtra("adult",movieArrayList.get(position).getAdult());
                intent.putExtra("popularity",movieArrayList.get(position).getPopularity());
                intent.putExtra("vote_count",movieArrayList.get(position).getVoteCount());
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {


        public TextView movieTitle, date;
      public   RatingBar movie_rating;
        public ImageView movieImage;
        public MaterialCardView cardView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            movieImage = (ImageView) itemView.findViewById(R.id.movie_image);
            movie_rating=(RatingBar)itemView.findViewById(R.id.ratingBar) ;
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            date = (TextView) itemView.findViewById(R.id.date);
            cardView=(MaterialCardView)itemView.findViewById(R.id.card_view);



        }
    }
}