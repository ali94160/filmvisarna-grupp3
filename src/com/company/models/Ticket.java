package com.company.models;

import org.dizitart.no2.objects.Id;

public class Ticket {
    @Id
    private String id;
    private int price;
    private String date;
    private String time;
    private String seat;
    private int childPrice = 65;
    private int seniorPrice = 75;
    private String userId;
    private String showId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public int getSeniorPrice() {
        return seniorPrice;
    }

    public void setSeniorPrice(int seniorPrice) {
        this.seniorPrice = seniorPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }
}
