package com.example.myticket;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetBuzz {
        @SerializedName("articles")
        ArrayList<GetBuzzList> getBuzzLists;
}
class GetBuzzList{
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    // Getter Methods
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }
}