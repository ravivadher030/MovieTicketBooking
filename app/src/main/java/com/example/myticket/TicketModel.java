package com.example.myticket;

import java.util.ArrayList;
import java.util.List;

public class TicketModel {
    String title;
    String date;
    List<Integer> ticket = new ArrayList<>();

    public TicketModel(String title, List<Integer> ticket, String date) {
        this.title = title;
        this.ticket = ticket;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getTicket() {
        return ticket;
    }

    public String getDate() {
        return date;
    }
}
