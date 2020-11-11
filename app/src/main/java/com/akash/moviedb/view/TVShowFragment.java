package com.akash.moviedb.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.akash.moviedb.R;
import com.akash.moviedb.adapter.MovieAdapter;
import com.akash.moviedb.adapter.TVShowAdapter;
import com.akash.moviedb.model.Movie;
import com.akash.moviedb.model.MovieDBResponse;
import com.akash.moviedb.model.TVShowsModel;
import com.akash.moviedb.model.TVShowsResponse;
import com.akash.moviedb.network.ApiInterface;
import com.akash.moviedb.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class TVShowFragment extends Fragment {
    ApiInterface apiInterface;
    List<TVShowsModel> tvShowsModels=new ArrayList<>();
    RecyclerView recyclerView;
    List<TVShowsModel> input;
    EditText mEditText;
    ImageButton mImageButtonSearch;
    ImageButton mImageButton;
    TVShowAdapter tvShowAdapter;
    private RecyclerView.Adapter mAdapter;

   View view;

    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_tvshows, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        apiInterface= RetrofitInstance.getService();
        init();


         input = new ArrayList<>();
        Random random=new Random();
        int page=random.nextInt(500)+1;
      Call<TVShowsResponse> call =apiInterface.getPopularTVShows(this.getString(R.string.API_KEY),page);
      call.enqueue(new Callback<TVShowsResponse>() {
          @Override
          public void onResponse(Call<TVShowsResponse> call, Response<TVShowsResponse> response) {
              TVShowsResponse response1=response.body();
              if(response1!=null && response1.getResults()!=null) {
              }
              tvShowsModels = (ArrayList<TVShowsModel>) response1.getResults();

              show();
              }


          @Override
          public void onFailure(Call<TVShowsResponse> call, Throwable t) {

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
                    Call<TVShowsResponse> call = apiInterface.getPopularTVShows(getContext().getString(R.string.API_KEY), Query);
                    call.enqueue(new Callback<TVShowsResponse>() {
                        @Override
                        public void onResponse(Call<TVShowsResponse> call, Response<TVShowsResponse> response) {
                            TVShowsResponse response1=response.body();
                            if(response1!=null && response1.getResults()!=null) {
                                tvShowsModels = (ArrayList<TVShowsModel>) response1.getResults();

                                show();
                            }


                        }

                        @Override
                        public void onFailure(Call<TVShowsResponse> call, Throwable t) {

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
        tvShowAdapter = new TVShowAdapter(getContext(), tvShowsModels);
        recyclerView = (RecyclerView) view.findViewById(R.id.tv_shows_recycler);
        recyclerView.setHasFixedSize(true);
 
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tvShowAdapter);
        recyclerView.setHasFixedSize(true);
        tvShowAdapter.notifyDataSetChanged();
    }
    private void init()
    {
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