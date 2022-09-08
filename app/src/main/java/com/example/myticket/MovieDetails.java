package com.example.myticket;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieDetails {
        private boolean adult;
        private String backdrop_path;
        private float budget;
        private String homepage;
        private float id;
        private String imdb_id;
        private String original_language;
        private String original_title;
        private String overview;
        private float popularity;
        private String poster_path;
        private String release_date;
        private float revenue;
        private float runtime;
        private String status;
        private String tagline;
        private String title;
        private boolean video;
        private float vote_average;
        private float vote_count;
        @SerializedName("genres")
        private ArrayList<Genre> genres;

        // Getter Methods
        class Genre {
            private String id;
            private String name;

            public String getId() {
                return id;
            }
            public String getName() {
                return name;
            }

        }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public boolean getAdult() {
            return adult;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }


        public float getBudget() {
            return budget;
        }

        public String getHomepage() {
            return homepage;
        }

        public float getId() {
            return id;
        }

        public String getImdb_id() {
            return imdb_id;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public String getOverview() {
            return overview;
        }

        public float getPopularity() {
            return popularity;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public String getRelease_date() {
            return release_date;
        }

        public float getRevenue() {
            return revenue;
        }

        public float getRuntime() {
            return runtime;
        }

        public String getStatus() {
            return status;
        }

        public String getTagline() {
            return tagline;
        }

        public String getTitle() {
            return title;
        }

        public boolean getVideo() {
            return video;
        }

        public float getVote_average() {
            return vote_average;
        }

        public float getVote_count() {
            return vote_count;
        }
}
