package com.example.myticket;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);

        RetrofitInterface retrofitInterface;
        retrofitInterface=RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        try {
            retrofitInterface.getMovie("d6b135e383cffc1b9ea920f5d4f2b1e7", "1").enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                    List<Model> movieLists = new ArrayList<>(response.body().getMovies());
                    Adapter ad = new Adapter(movieLists, getContext());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    RecyclerView recyclerView = v.findViewById(R.id.rec_view);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(ad);
                }

                @Override
                public void onFailure(Call<MovieList> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage() + " ", Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            getChildFragmentManager().beginTransaction().replace(R.id.container,new NotFound()).commit();
        }
        return v;
    }
}