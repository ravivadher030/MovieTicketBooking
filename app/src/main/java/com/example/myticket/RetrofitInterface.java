package com.example.myticket;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface RetrofitInterface {
    @GET("3/movie/now_playing?d6b135e383cffc1b9ea920f5d4f2b1e7&page=1&region=in")
    Call<MovieList> getMovie(
            @Query("api_key") String key,
            @Query("page") String page
    );

    @GET("Search/k_qv9sz0h0/{expression}")
    Call<MovieId> getId(
            @Path("expression") String title
    );

    @GET("Title/k_qv9sz0h0/{id}")
    Call<FullDetails> getFullDetail(
            @Path("id") String id
    );

    @GET("top-headlines?country=in&category=entertainment&apikey=74f5507654dd4553bc6fe3f63defbe2e")
    Call<GetBuzz> getBuzz();
}
