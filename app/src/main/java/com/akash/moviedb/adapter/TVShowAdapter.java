package com.akash.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.akash.moviedb.model.TVShowsModel;
import com.akash.moviedb.view.DetailActivity;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {

    private Context context;
    private List<TVShowsModel> movieArrayList;

    public TVShowAdapter(Context context, List<TVShowsModel> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }


    @NonNull
    @Override
    public TVShowAdapter.TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_shows_card_view, parent, false);
        return new TVShowAdapter.TVShowViewHolder(view);
    }


    public void onBindViewHolder(@NonNull TVShowAdapter.TVShowViewHolder holder, final int position) {

        holder.movieTitle.setText(movieArrayList.get(position).getOriginalName());
        double longValue = movieArrayList.get(position).getVoteAverage();
        holder.movie_rating.setRating(Float.parseFloat(String.format("%.1f", longValue / 2)));
        holder.overview.setText(movieArrayList.get(position).getOverview());


        String imagePath = "https://image.tmdb.org/t/p/w500" + movieArrayList.get(position).getPosterPath();

        Picasso.get()
                .load(imagePath)
                .fit()
                .placeholder(R.drawable.load)
                .into(holder.movieImage);

        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(context, ""+movieArrayList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image", "https://image.tmdb.org/t/p/w500" + movieArrayList.get(position).getPosterPath());
                intent.putExtra("title", movieArrayList.get(position).getTitle());
                intent.putExtra("overview", movieArrayList.get(position).getOverview());
                intent.putExtra("vote_average", movieArrayList.get(position).getVoteAverage());
                intent.putExtra("release_date", movieArrayList.get(position).getReleaseDate());
                intent.putExtra("original_language", movieArrayList.get(position).getOriginalLanguage());
                intent.putExtra("adult", movieArrayList.get(position).getAdult());
                intent.putExtra("popularity", movieArrayList.get(position).getPopularity());
                intent.putExtra("vote_count", movieArrayList.get(position).getVoteCount());
                context.startActivity(intent);
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }


    public class TVShowViewHolder extends RecyclerView.ViewHolder {


        public TextView movieTitle, overview;
        public RatingBar movie_rating;
        public ImageView movieImage;

        public TVShowViewHolder(View itemView) {
            super(itemView);

            movieImage = (ImageView) itemView.findViewById(R.id.tvshow_images);
            movie_rating = (RatingBar) itemView.findViewById(R.id.ratingBar);
            movieTitle = (TextView) itemView.findViewById(R.id.title);
            overview = (TextView) itemView.findViewById(R.id.overview);



        }
    }
}
