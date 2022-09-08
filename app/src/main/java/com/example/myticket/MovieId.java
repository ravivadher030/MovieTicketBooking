package com.example.myticket;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieId {
    @SerializedName("results")
    private ArrayList<Ids> ids;
    class Ids{
        private String id;

        public String getId() {
            return id;
        }
    }
    public ArrayList<Ids> getIds() {
        return ids;
    }
}

