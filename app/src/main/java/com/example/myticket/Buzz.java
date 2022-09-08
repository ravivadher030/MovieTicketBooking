package com.example.myticket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Buzz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buzz extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Buzz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Buzz.
     */
    // TODO: Rename and change types and number of parameters
    public static Buzz newInstance(String param1, String param2) {
        Buzz fragment = new Buzz();
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
        // Inflate the layout for this fragment
        RecyclerView rec_view;
        View v=inflater.inflate(R.layout.fragment_buzz, container, false);
        rec_view=v.findViewById(R.id.rec_view);
        RetrofitInterface anInterface;
        anInterface=RetrofitInstance.getBuzzInstance().create(RetrofitInterface.class);
        anInterface.getBuzz().enqueue(new Callback<GetBuzz>() {
            @Override
            public void onResponse(Call<GetBuzz> call, Response<GetBuzz> response) {
                GetBuzz buzzes=response.body();
                List<GetBuzzList> buzzLists=new ArrayList<>(buzzes.getBuzzLists);
                BuzzAdapter adapter=new BuzzAdapter(buzzLists,getContext());
                LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                rec_view.setAdapter(adapter);
                rec_view.setLayoutManager(manager);

            }

            @Override
            public void onFailure(Call<GetBuzz> call, Throwable t) {

            }
        });
        return v;
    }
}