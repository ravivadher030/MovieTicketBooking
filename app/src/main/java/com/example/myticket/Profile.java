package com.example.myticket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.internal.InternalTokenProvider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth mAuth;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        //FirebaseUser user=mAuth.getCurrentUser();
        TextView user_login,username;
        ImageView user_profile;
        LinearLayout help,gift_card,offer,purchase;
        user_profile=v.findViewById(R.id.user_profile);
        help=v.findViewById(R.id.help);
        gift_card=v.findViewById(R.id.gift_card);
        offer=v.findViewById(R.id.offer);
        purchase=v.findViewById(R.id.purchase);
        user_login=v.findViewById(R.id.user_login);
        username=v.findViewById(R.id.username);
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("MyTicket", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        boolean loggedIn=sharedPreferences.getBoolean("LoggedIn",false);
        if(loggedIn==true){
            user_login.setText("Logout");
        }else{
            user_login.setText("Login/Register");
        }
        user_login.setOnClickListener(V->{
            if(loggedIn==true){
                FirebaseAuth.getInstance().signOut();
                editor.clear();
                editor.commit();
                Intent i=new Intent(getContext(),MainActivity.class);
                startActivity(i);
            }else{
                Intent i=new Intent(getContext(),Login.class);
                startActivity(i);
            }
        });
        purchase.setOnClickListener(V->{
            if(loggedIn==false){
                Intent i=new Intent(getContext(),Login.class);
                startActivity(i);
            }else{
                Intent i=new Intent(getContext(),PurchaseHistory.class);
                startActivity(i);
            }
        });
        offer.setOnClickListener(V->{
            if(loggedIn==false){
                Toast.makeText(getContext(), "Please Login First", Toast.LENGTH_SHORT).show();
            }
        });
        gift_card.setOnClickListener(V->{
            if(loggedIn==false){
                Toast.makeText(getContext(), "Please Login First", Toast.LENGTH_SHORT).show();
            }
        });
        help.setOnClickListener(V->{
                Intent i=new Intent(getContext(),Help.class);
                startActivity(i);
        });
        user_profile.setOnClickListener(V->{
            if(loggedIn==false){
                Toast.makeText(getContext(), "Please Login First", Toast.LENGTH_SHORT).show();
            }else{
                Intent i= new Intent(getContext(),UserProfile.class);
                startActivity(i);
            }
        });
        return v;
    }
}