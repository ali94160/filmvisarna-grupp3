package com.company.models;

import org.dizitart.no2.objects.Id;

public class Ticket {
    @Id
    private String id;
    private int price;
    private String seat;
    private Salon salon;
    private int childPrice = 65;
    private int seniorPrice = 75;
    private Show show;

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

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
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

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
