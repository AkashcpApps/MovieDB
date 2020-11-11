package com.akash.moviedb.network;

import com.akash.moviedb.model.MovieDBResponse;
import com.akash.moviedb.model.TVShowsResponse;
import com.akash.moviedb.model.UpcomingModel;
import com.akash.moviedb.model.UpcomingMovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String api_key,@Query("page") int page);

    @GET("search/movie")
    Call<MovieDBResponse> getMovieBySearch(@Query("api_key") String api_key,@Query("query") String query);

    @GET("tv/popular")
    Call<TVShowsResponse> getPopularTVShows(@Query("api_key") String api_key,@Query("page") int page);

    @GET("search/tv")
    Call<TVShowsResponse> getPopularTVShows(@Query("api_key") String api_key,@Query("query") String query);

    @GET("movie/upcoming")
    Call<UpcomingMovieResponse> getUpcomingMovies(@Query("api_key") String api_key,@Query("page") int page);

}
