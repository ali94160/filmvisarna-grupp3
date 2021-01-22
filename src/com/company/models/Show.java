package com.company.models;

import express.database.Model;
import org.dizitart.no2.objects.Id;

import java.util.ArrayList;
import java.util.Arrays;

@Model
public class Show {
    @Id
    private String id;
    private Long timeStamp;
    private ArrayList<Integer> seatsTaken;
    private String movieId;
    private String salonId;

    public void increaseSeatsTaken(String seats){

        String[] bookedSeats = seats.split(",");
        System.out.println(Arrays.toString(bookedSeats));
        if(this.seatsTaken == null){
            this.seatsTaken = new ArrayList<Integer>();
        }
        for(int i = 0; i < bookedSeats.length; i++) {
            this.seatsTaken.add(Integer.parseInt(bookedSeats[i]));
        }
        System.out.println(this.seatsTaken);
    }

    @Override
    public String toString() {
        return "Show{" +
                "id='" + id + '\'' +
                ", timeStamp=" + timeStamp +
                ", seatsTaken=" + seatsTaken +
                ", movieId='" + movieId + '\'' +
                ", salonId='" + salonId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ArrayList<Integer> getSeatsTaken() {
        return seatsTaken;
    }

    public void setSeatsTaken(ArrayList<Integer> seatsTaken) {
        this.seatsTaken = seatsTaken;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
    }
}
