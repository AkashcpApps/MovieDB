package com.akash.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akash.moviedb.R;
import com.akash.moviedb.model.UpcomingModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcommingModelHolder> {

    private Context context;
    private List<UpcomingModel> movieArrayList;

    public UpcomingAdapter(Context context, List<UpcomingModel> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }


    @NonNull
    @Override
    public UpcomingAdapter.UpcommingModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_card_view, parent, false);
        return new UpcomingAdapter.UpcommingModelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcommingModelHolder holder, int position) {
        holder.movieTitle.setText(movieArrayList.get(position).getOriginalTitle());

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


    public class UpcommingModelHolder extends RecyclerView.ViewHolder {


        public TextView movieTitle;
        public ImageView movieImage;

        public UpcommingModelHolder(View itemView) {
            super(itemView);

            movieImage = (ImageView) itemView.findViewById(R.id.imageView);
            movieTitle = (TextView) itemView.findViewById(R.id.name);




        }
    }
}
