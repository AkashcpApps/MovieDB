package com.akash.moviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UpcomingMovieResponse implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<UpcomingModel> results = new ArrayList<>();
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    public final static Parcelable.Creator<UpcomingMovieResponse> CREATOR = new Creator<UpcomingMovieResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UpcomingMovieResponse createFromParcel(Parcel in) {
            return new UpcomingMovieResponse(in);
        }

        public UpcomingMovieResponse[] newArray(int size) {
            return (new UpcomingMovieResponse[size]);
        }

    }
            ;

    protected UpcomingMovieResponse(Parcel in) {
        in.readList(this.results, (UpcomingModel.class.getClassLoader()));
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public UpcomingMovieResponse() {
    }

    public List<UpcomingModel> getResults() {
        return results;
    }

    public void setResults(List<UpcomingModel> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }





    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return 0;
    }

}
