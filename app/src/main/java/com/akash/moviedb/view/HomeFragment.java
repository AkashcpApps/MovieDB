package com.akash.moviedb.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.akash.moviedb.R;
import com.akash.moviedb.adapter.MovieAdapter;
import com.akash.moviedb.model.Movie;
import com.akash.moviedb.model.MovieDBResponse;
import com.akash.moviedb.network.ApiInterface;
import com.akash.moviedb.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends Fragment {
    ArrayList<Movie> movies=new ArrayList<>();

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    Random random;
    EditText mEditText;
    ImageButton mImageButtonSearch;
    ImageButton mImageButton;
    ApiInterface apiInterface;

View view;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
      getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        init();
        apiInterface= RetrofitInstance.getService();
        int page= random.nextInt(500)+1;

        Call<MovieDBResponse> call=apiInterface.getPopularMovies(this.getString(R.string.API_KEY),page);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse=response.body();
                if(movieDBResponse!=null && movieDBResponse.getMovies()!=null)
                {


                    movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                    show();


                }


            }


            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSpeechInput();
            }
        });
        mImageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String Query=  mEditText.getText().toString();
             if(!TextUtils.isEmpty(Query)) {
                 Call<MovieDBResponse> call = apiInterface.getMovieBySearch(getContext().getString(R.string.API_KEY), Query);
                 call.enqueue(new Callback<MovieDBResponse>() {
                     @Override
                     public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                         MovieDBResponse movieDBResponse = response.body();
                         if (movieDBResponse != null && movieDBResponse.getMovies() != null) {

                             movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                             show();

                         }

                     }

                     @Override
                     public void onFailure(Call<MovieDBResponse> call, Throwable t) {

                     }
                 });
             }
             else
             {
                 Toast.makeText(getContext(), "Please Enter Some Text for Searching", Toast.LENGTH_LONG).show();
             }

            }
        });


        return view;
    }
    private void show()
    {
        movieAdapter = new MovieAdapter(getContext(), movies);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setHasFixedSize(true);
        movieAdapter.notifyDataSetChanged();
    }
    private void init()
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        random=new Random();
        mImageButton=(ImageButton)view.findViewById(R.id.microphone);
        mEditText=(EditText)view.findViewById(R.id.search_bar);
        mImageButtonSearch=(ImageButton)view.findViewById(R.id.search_button);

    }
    public void getSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(getContext(), "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(getContext(), result.get(0), Toast.LENGTH_SHORT).show();
                    mEditText.setText(result.get(0));

                }
                break;
        }
    }
}
