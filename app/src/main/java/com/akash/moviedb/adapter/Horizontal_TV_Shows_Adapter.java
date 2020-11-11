package com.akash.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akash.moviedb.R;
import com.akash.moviedb.model.TVShowsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Horizontal_TV_Shows_Adapter  extends RecyclerView.Adapter<Horizontal_TV_Shows_Adapter.Horizontal_TV_Shows_AdapterHolder> {

    private Context context;
    private List<TVShowsModel> movieArrayList;

    public Horizontal_TV_Shows_Adapter(Context context, List<TVShowsModel> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }


    @NonNull
    @Override
    public Horizontal_TV_Shows_Adapter.Horizontal_TV_Shows_AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.box_cardview, parent, false);
        return new Horizontal_TV_Shows_Adapter.Horizontal_TV_Shows_AdapterHolder(view);
    }


    public void onBindViewHolder(@NonNull Horizontal_TV_Shows_Adapter.Horizontal_TV_Shows_AdapterHolder holder, final int position) {

        holder.movieTitle.setText(movieArrayList.get(position).getName());
        String imagePath = "https://image.tmdb.org/t/p/w500" + movieArrayList.get(position).getPosterPath();

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


    public class Horizontal_TV_Shows_AdapterHolder extends RecyclerView.ViewHolder {


        public TextView movieTitle;
        public ImageView movieImage;

        public Horizontal_TV_Shows_AdapterHolder(View itemView) {
            super(itemView);

            movieImage = (ImageView) itemView.findViewById(R.id.image_views);

            movieTitle = (TextView) itemView.findViewById(R.id.movie_names);


        }
    }
}
