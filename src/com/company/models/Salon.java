package com.company.models;

import express.database.Model;
import org.dizitart.no2.objects.Id;

@Model
public class Salon {
    @Id
    private String id;
    private String name;
    private int seats;
    private int[] seatsPerRow;
    private Show[] shows;

}
