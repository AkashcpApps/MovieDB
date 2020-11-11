package com.akash.moviedb.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.akash.moviedb.R;
import com.akash.moviedb.adapter.MovieAdapter;
import com.akash.moviedb.adapter.TVShowAdapter;
import com.akash.moviedb.adapter.UpcomingAdapter;
import com.akash.moviedb.model.Movie;
import com.akash.moviedb.model.MovieDBResponse;
import com.akash.moviedb.model.UpcomingModel;
import com.akash.moviedb.model.UpcomingMovieResponse;
import com.akash.moviedb.network.ApiInterface;
import com.akash.moviedb.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaggeredGridUpcommingFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    Random random;
    EditText mEditText;
    ImageButton mImageButtonSearch;
    ImageButton mImageButton;
    ApiInterface apiInterface;
    UpcomingAdapter upcomingAdapter;
    List<UpcomingModel> movies = new ArrayList<>();



    public StaggeredGridUpcommingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_staggered_grid_upcomming, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.staggered_grid_view) ;

        apiInterface = RetrofitInstance.getService();
         random=new Random();
        int page=random.nextInt(10)+1;
        Toast.makeText(getContext(), ""+page, Toast.LENGTH_SHORT).show();
        Call<UpcomingMovieResponse> call = apiInterface.getUpcomingMovies(this.getString(R.string.API_KEY),page);
        call.enqueue(new Callback<UpcomingMovieResponse>() {
            @Override
            public void onResponse(Call<UpcomingMovieResponse> call, Response<UpcomingMovieResponse> response) {
                UpcomingMovieResponse movieDBResponse = response.body();
                if (movieDBResponse != null && movieDBResponse.getResults() != null) {


                    movies = (ArrayList<UpcomingModel>) movieDBResponse.getResults();
                    show();


                }

            }

            @Override
            public void onFailure(Call<UpcomingMovieResponse> call, Throwable t) {

            }
        });


        return view;
    }
    private void show()
    {


        upcomingAdapter = new UpcomingAdapter(getContext(), movies);
        recyclerView.setHasFixedSize(true);

       // StaggeredGridLayoutManager layoutManager= new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(upcomingAdapter);
        recyclerView.setHasFixedSize(true);
        upcomingAdapter.notifyDataSetChanged();
    }
}