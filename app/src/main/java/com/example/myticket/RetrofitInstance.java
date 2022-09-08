package com.example.myticket;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit, retrofit1, retrofit2;
    private static final String URL = "https://api.themoviedb.org/";
    private static final String IMDB_URL = "https://imdb-api.com/en/API/";
    private static final String BUZZ = "https://newsapi.org/v2/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstanceImdb() {
        if (retrofit1 == null) {
            retrofit1 = new Retrofit.Builder().baseUrl(IMDB_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit1;
    }

    public static Retrofit getBuzzInstance() {
        if (retrofit2 == null) {
            retrofit2=new Retrofit.Builder().baseUrl(BUZZ)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }
}
