package com.example.myticket;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FullDetails {
    private String id;
    private String title;
    private String type;
    private String year;
    private String image;
    private String releaseDate;
    private String runtimeMins;
    private String runtimeStr;
    private String plot;
    private String plotLocal;
    private boolean plotLocalIsRtl;
    private String directors;
    private String writers;
    private String stars;
    private String fullCast = null;
    private String genres;
    private String companies;
    private String countries;
    private String languages;
    private String contentRating;
    private String imDbRating;
    private String imDbRatingVotes;
    private String metacriticRating;
    private String ratings = null;
    private String wikipedia = null;
    private String posters = null;
    private String images = null;
    private String trailer = null;
    private String tagline = null;
    private String tvSeriesInfo = null;
    private String tvEpisodeInfo = null;
    private String errorMessage = null;
    @SerializedName("actorList")
    private ArrayList<ActorList> actorLists;

    @SerializedName("similars")
    private ArrayList<Similars> similars;


    public ArrayList<ActorList> getActorLists() {
        return actorLists;
    }

    public ArrayList<Similars> getSimilars() {
        return similars;
    }

    // Getter Methods
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getType() {
        return type;
    }
    public String getYear() {
        return year;
    }
    public String getImage() {
        return image;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public String getRuntimeMins() {
        return runtimeMins;
    }
    public String getRuntimeStr() {
        return runtimeStr;
    }
    public String getPlot() {
        return plot;
    }
    public String getPlotLocal() {
        return plotLocal;
    }
    public boolean getPlotLocalIsRtl() {
        return plotLocalIsRtl;
    }
    public String getDirectors() {
        return directors;
    }
    public String getWriters() {
        return writers;
    }
    public String getStars() {
        return stars;
    }
    public String getFullCast() {
        return fullCast;
    }
    public String getGenres() {
        return genres;
    }
    public String getCompanies() {
        return companies;
    }
    public String getCountries() {
        return countries;
    }
    public String getLanguages() {
        return languages;
    }
    public String getContentRating() {
        return contentRating;
    }
    public String getImDbRating() {
        return imDbRating;
    }
    public String getImDbRatingVotes() {
        return imDbRatingVotes;
    }
    public String getMetacriticRating() {
        return metacriticRating;
    }
    public String getRatings() {
        return ratings;
    }
    public String getWikipedia() {
        return wikipedia;
    }
    public String getPosters() {
        return posters;
    }
    public String getImages() {
        return images;
    }
    public String getTrailer() {
        return trailer;
    }
    public String getTagline() {
        return tagline;
    }
    public String getTvSeriesInfo() {
        return tvSeriesInfo;
    }
    public String getTvEpisodeInfo() {
        return tvEpisodeInfo;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
    class ActorList{
        private String id;
        private String image;
        private String name;
        private String asCharacter;

        public String getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getAsCharacter() {
            return asCharacter;
        }
    }
    class Similars{
        private String id;
        private String title;
        private String image;
        private String imDbRating;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }
            public String getImDbRating() {
            return imDbRating;
        }
    }
        // Setter Methods


