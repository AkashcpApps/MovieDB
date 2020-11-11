package com.akash.moviedb.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akash.moviedb.R;
import com.akash.moviedb.adapter.Horizontal_Movie_Adapter;
import com.akash.moviedb.adapter.Horizontal_TV_Shows_Adapter;
import com.akash.moviedb.adapter.TVShowAdapter;
import com.akash.moviedb.model.Movie;
import com.akash.moviedb.model.MovieDBResponse;
import com.akash.moviedb.model.TVShowsModel;
import com.akash.moviedb.model.TVShowsResponse;
import com.akash.moviedb.network.ApiInterface;
import com.akash.moviedb.network.RetrofitInstance;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ActionTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.TouchListener;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PopularFragment extends Fragment {

View view;
RecyclerView recyclerView;
    public PopularFragment() {
        // Required empty public constructor
    }

    List<Movie> movies=new ArrayList<>();
    ArrayList<SlideModel> imageList=new ArrayList<>();
    List<TVShowsModel> tvShowsModels=new ArrayList<>();

    ImageSlider imageSlider;

    ApiInterface apiInterface;
    Horizontal_Movie_Adapter horizontal_movie_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_popular, container, false);
        imageSlider=(ImageSlider)view.findViewById(R.id.image_slider);


        imageSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), ""+view.getPointerIcon(), Toast.LENGTH_SHORT).show();
            }
        });

        apiInterface= RetrofitInstance.getService();
        Random random=new Random();
        int page=random.nextInt(500)+1;
        Call<MovieDBResponse> call=apiInterface.getPopularMovies(this.getString(R.string.API_KEY),page);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse=response.body();
                if(movieDBResponse!=null && movieDBResponse.getMovies()!=null)
                {


                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    for(int i=14;i<20;i++)
                    {
                        imageList.add(new SlideModel("https://image.tmdb.org/t/p/w500"+movies.get(i).getPosterPath(),movies.get(i).getTitle(), ScaleTypes.FIT));
                        Log.i("value","*******"+"https://image.tmdb.org/t/p/w500"+movies.get(i).getPosterPath());
                    }
                    show();

                    imageSlider.setImageList(imageList, ScaleTypes.FIT) ;// for all images

                    imageSlider.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                    imageSlider.startSliding(2000) ;// with new period



                }


            }


            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
         random=new Random();
        int page1=random.nextInt(500)+1;
        Call<TVShowsResponse> call1 =apiInterface.getPopularTVShows(this.getString(R.string.API_KEY),page1);
        call1.enqueue(new Callback<TVShowsResponse>() {
            @Override
            public void onResponse(Call<TVShowsResponse> call, Response<TVShowsResponse> response) {
                TVShowsResponse response1=response.body();
                if(response1!=null && response1.getResults()!=null) {
                }
                tvShowsModels = (ArrayList<TVShowsModel>) response1.getResults();

                show1();
            }


            @Override
            public void onFailure(Call<TVShowsResponse> call, Throwable t) {

            }
        });


        return view;
    }
    private void show()
    {
        horizontal_movie_adapter = new Horizontal_Movie_Adapter(getContext(), movies);
        recyclerView = (RecyclerView) view.findViewById(R.id.top);

      LinearLayoutManager  layoutManager = new  LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(horizontal_movie_adapter);
       recyclerView.setHasFixedSize(true);
        horizontal_movie_adapter.notifyDataSetChanged();
    }

    private void show1()
    {
        Horizontal_TV_Shows_Adapter horizontal_movie_adapter = new Horizontal_TV_Shows_Adapter(getContext(), tvShowsModels);
        recyclerView = (RecyclerView) view.findViewById(R.id.bottom);

        LinearLayoutManager  layoutManager = new  LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(horizontal_movie_adapter);
         recyclerView.setHasFixedSize(true);
        horizontal_movie_adapter.notifyDataSetChanged();
    }
}