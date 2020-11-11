package com.akash.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.moviedb.R;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    ImageView header;
    TextView title,movie_Adult,overview,movie_Language,movie_Popularity,movie_VoteCount,releasedate;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        header=(ImageView)findViewById(R.id.header);
     Toolbar toolbar=(Toolbar)findViewById(R.id.anim_toolbar);
          title=(TextView)findViewById(R.id.movie_title);
        movie_Adult=(TextView)findViewById(R.id.movie_Adult);
        overview=(TextView)findViewById(R.id.movie_Overview);
        releasedate=(TextView)findViewById(R.id.movie_Releasedate);
        movie_Language=(TextView)findViewById(R.id.movie_Language);
        movie_Popularity=(TextView)findViewById(R.id.movie_Popularity);
        movie_VoteCount=(TextView)findViewById(R.id.movie_VoteCount);
        ratingBar=(RatingBar)findViewById(R.id.movie_rating);

        try {

            Intent intent = getIntent();
            String image = intent.getStringExtra("image");
            String title1=intent.getStringExtra("title");
            String overview1=intent.getStringExtra("overview");
            String release_date1=intent.getStringExtra("release_date");
            String original_language1=intent.getStringExtra("original_language");
            boolean adult1=intent.getBooleanExtra("adult",false);
            double vote_average1=intent.getDoubleExtra("vote_average",0.0);
            double popularity1=intent.getDoubleExtra("popularity",0.0);
            int vote_count1=intent.getIntExtra("vote_count",0);



            Picasso.get()
                    .load(image)
                    .fit()
                    .placeholder(R.drawable.load)
                    .into(header);
            title.setText(title1);
            releasedate.setText("Release Date: "+release_date1);
            movie_Adult.setText("Adult: "+adult1);
            overview.setText("Overview: "+overview1);
            movie_Language.setText("Language: "+original_language1);
            movie_VoteCount.setText("VoteCount: "+vote_count1);
            movie_Popularity.setText("Popularity: "+popularity1);
           ratingBar.setRating(Float.parseFloat(String.format("%.1f", vote_average1/2)));




        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}