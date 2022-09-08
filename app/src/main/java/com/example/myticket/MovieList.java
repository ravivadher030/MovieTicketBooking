package com.example.myticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    @SerializedName("results")
    List<Model> m;
    public List<Model> getMovies(){
        return m;
    }
    private List<Model> model;
}
