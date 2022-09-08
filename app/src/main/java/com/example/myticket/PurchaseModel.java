package com.example.myticket;

import java.util.ArrayList;
import java.util.List;

public class PurchaseModel {
    String movieName;
    String date;
    float price;
    List<Integer> seatNo=new ArrayList<>();


    /*public PurchaseModel(String title, String date, float price, List<Integer> ticketNo) {
        this.title = title;
        this.date = date;
        this.price = price;
        this.ticketNo = ticketNo;
    }*/

    public String getMovieName() {
        return movieName;
    }

    public String getDate() {
        return date;
    }

    public float getPrice() {
        return price;
    }

    public List<Integer> getSeatNo() {
        return seatNo;
    }
}
