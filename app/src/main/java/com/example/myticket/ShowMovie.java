package com.example.myticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowMovie extends AppCompatActivity {
    ImageView movie_image,p_activity;
    TextView movie_title,movie_rating,basic_d,m_desc;
    Button book_ticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie);
        movie_image=findViewById(R.id.movie_image);
        movie_title=findViewById(R.id.movie_title);
        basic_d=findViewById(R.id.basic_d);
        movie_rating=findViewById(R.id.movie_rating);
        p_activity=findViewById(R.id.p_activity);
        m_desc=findViewById(R.id.m_desc);
        book_ticket=findViewById(R.id.book_ticket);
        int id=(int)getIntent().getFloatExtra("m_id",0);

        //Back button
        p_activity.setOnClickListener(v -> ShowMovie.super.onBackPressed());

        //Get Internet information
        //Get title and id from Adapter
        String title=getIntent().getStringExtra("m_title");

            //Create Retrofit reference for getting api details....
            //Get id from Imdb API using Title
            RetrofitInterface retrofitInterface1;
            retrofitInterface1 = RetrofitInstance.getRetrofitInstanceImdb().create(RetrofitInterface.class);
            retrofitInterface1.getId(title).enqueue(new Callback<MovieId>() {
                @Override
                public void onResponse(Call<MovieId> call, Response<MovieId> response) {
                    MovieId mid = response.body();
                    ArrayList<MovieId.Ids> m = mid.getIds();
                    RetrofitInterface retrofitInterface2;
                    retrofitInterface2 = RetrofitInstance.getRetrofitInstanceImdb().create(RetrofitInterface.class);
                    try {
                        retrofitInterface2.getFullDetail(m.get(0).getId())
                                .enqueue(new Callback<FullDetails>() {
                                    @Override
                                    public void onResponse(Call<FullDetails> call, Response<FullDetails> response) {
                                        FullDetails fd = response.body();

                                        //Set all details into layout
                                        movie_title.setText(fd.getTitle());
                                        Picasso.get().load(fd.getImage())
                                                .resize(700, 350)
                                                .into(movie_image);
                                        if(fd.getImDbRatingVotes()!=null) {
                                            movie_rating.setText(fd.getImDbRating() + "  " + Math.floor(Integer.parseInt(fd.getImDbRatingVotes()) / 1000) + "K voting");
                                        }
                                        basic_d.setText(fd.getRuntimeStr() + "  " + fd.getGenres() + "  " + fd.getReleaseDate());
                                        m_desc.setText(fd.getPlot());
                                        m_desc.setEllipsize(TextUtils.TruncateAt.END);
                                        m_desc.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                int line = m_desc.getLineCount();
                                                if (line == 2) {
                                                    m_desc.setLines(5);
                                                }
                                                if (line == 5) {
                                                    m_desc.setLines(2);
                                                }
                                            }
                                        });
                                        if(fd.getReleaseDate()!=null) {
                                            String[] year = fd.getReleaseDate().split("-");
                                            int c_year = Calendar.getInstance().get(Calendar.YEAR);
                                            if (!year[0].equals(String.valueOf(c_year))) {
                                                book_ticket.setVisibility(View.INVISIBLE);
                                            } else {
                                                book_ticket.setVisibility(View.VISIBLE);
                                            }
                                        }
                                        //Get Actor detail and show it into recyclerView
                                        List<ActorList> aclist = new ArrayList<>(fd.getActorLists());
                                        ActorAdapter actorAdapter = new ActorAdapter(aclist, getApplicationContext());
                                        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                                        RecyclerView recyclerView = findViewById(R.id.rec_view_actor);
                                        recyclerView.setLayoutManager(lm);
                                        recyclerView.setAdapter(actorAdapter);

                                        //Get Similar Movies detail and show it into recyclerView
                                        List<Similars> similars = new ArrayList<>(fd.getSimilars());
                                        SimilarMoviesAdapter smAdapter = new SimilarMoviesAdapter(similars, getApplicationContext());
                                        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                                        //GridLayoutManager llm=new GridLayoutManager(getApplicationContext(),1,LinearLayoutManager.HORIZONTAL,false);
                                        RecyclerView smRecycler = findViewById(R.id.rec_view_similar_movie);
                                        smRecycler.setLayoutManager(llm);
                                        smRecycler.setAdapter(smAdapter);
                                        book_ticket.setOnClickListener(v -> {
                                            SharedPreferences sharedPreferences=getSharedPreferences("MyTicket", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor=sharedPreferences.edit();
                                            boolean loggedIn=sharedPreferences.getBoolean("LoggedIn",false);
                                            if(loggedIn==true){
                                                Intent i=new Intent(getApplicationContext(),SelectSeat.class);
                                                i.putExtra("m_title",title);
                                                startActivity(i);
                                                finish();
                                            }else{
                                                Intent i=new Intent(getApplicationContext(),Login.class);
                                                startActivity(i);
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(Call<FullDetails> call, Throwable t) {
                                        boolean connected = CheckInternet();
                                        if (!connected) {
                                            Toast.makeText(getApplicationContext(), "Please Turn on Internet", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    } catch (Exception e) {
                        NotFound notFound=new NotFound();
                        notFound.show(getSupportFragmentManager(),notFound.getTag());
                    }
                    //Get FullDetails about movie using IMDB API {id}
                }

                @Override
                public void onFailure(Call<MovieId> call, Throwable t) {
                    boolean connected = CheckInternet();
                    if (!connected) {
                        Toast.makeText(getApplicationContext(), "Please Turn on Internet", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
    public boolean CheckInternet(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }else{
            return false;
        }
    }
}