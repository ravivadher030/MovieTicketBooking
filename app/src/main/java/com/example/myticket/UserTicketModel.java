package com.example.myticket;

import java.util.ArrayList;
import java.util.List;

public class UserTicketModel {
    String userName;
    float price;
    List<Integer> seats=new ArrayList<>();
    String date;
    String movieName;


    public UserTicketModel(String userName,String movieName, float price, List<Integer> seats, String date) {
        this.userName = userName;
        this.movieName = movieName;
        this.price = price;
        this.seats = seats;
        this.date = date;
    }
    public String getMovieName() {
        return movieName;
    }

    public String getUserName() {
        return userName;
    }

    public float getPrice() {
        return price;
    }

    public List<Integer> getSeatNo() {
        return seats;
    }

    public String getDate() {
        return date;
    }
}
