package com.company;

import com.company.models.*;
import express.Express;

import java.util.List;

import static express.database.Database.collection;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class Cinema {
    private Express app;

    public Cinema(Express app){
        this.app = app;
        initCinema();
    }

    public void initCinema(){
        app.get("/rest/movie", (req, res) -> {
            var movie = collection("Movie").find();
            res.json(movie);
        });

        app.get("/rest/movie/:id", (req, res) -> {
            var id = req.params("id");
            var movie = collection("Movie").findById(id);
            res.json(movie);
        });

        app.get("/rest/salon",(req, res) ->{
            var salon = collection("Salon").find();
            res.json(salon);
        });
        app.get("/rest/show",(req, res) ->{
            var show = collection("Show").find();
            res.json(show);
        });

        app.get("/rest/ticket",(req, res) ->{
            var ticket = collection("Ticket").find();
            res.json(ticket);
        });

        app.get("/rest/show/get-salon/:id",(req, res) ->{
            //get salon with showId
            var id = req.params("id");
            Show show = collection("Show").findById(id);
            if(show == null){
                res.send("Show not found");
                return;
            }
            Salon salon = collection("Salon").findById(show.getSalonId());
            res.json(salon);
        });

        app.get("/rest/salon/get-show/:id",(req, res) ->{
            //get shows with salonId
            var id = req.params("id");
            // fetching the salon
            Salon salon = collection("Salon").findById(id);
            if(salon == null){
                res.send("Salon not found");
                return;
            }
            // fetch and filter shows with matching salonId
            List<Show> show = collection("Show").find(eq("salonId", salon.getId()));
            if(show != null) {
                res.json(show);
            }
            else{
                res.send("Shows not found");
            }
        });

        app.get("/rest/movie/get-show/:id",(req, res) ->{
            //get shows by movieId
            var id = req.params("id");
            // fetching the salon
            Movie movie = collection("Movie").findById(id);
            if(movie == null){
                res.send("Movie not found");
                return;
            }
            // fetch and filter shows with matching movieId
            List<Show> show = collection("Show").find(eq("movieId", movie.getId()));

            if(show != null) {
                res.json(show);
            }
            else{
                res.send("Shows not found");
            }
        });

        app.get("/rest/ticket/get-show/:id", (req, res) -> {
            // get show from ticketId
            var id = req.params("id");
            Ticket ticket = collection("Ticket").findById(id);
            if(ticket == null){
                res.send("Ticket not found");
                return;
            }
            Show show = collection("Show").findById(ticket.getShowId());
            if(show != null) {
                res.json(show);
            }
            else{
                res.send("Show not found");
            }
        });

        app.get("/rest/user/get-ticket/:id", (req, res) -> {
            //get tickets from userId
            var id = req.params("id");
            // fetching the salon
            User user = collection("User").findById(id);
            if(user == null){
                res.send("User not found");
                return;
            }
            // fetch and filter shows with matching salonId
            List<Ticket> tickets = collection("Ticket").find(eq("userId", user.getId()));
            if(tickets != null) {
                res.json(tickets);
            }
            else{
                res.send("Tickets not found");
            }
        });

        app.put("/rest/show/increase-seats/:id/:seats", (req, res) -> {
            // id = showId, seats = string of seat numbers
            var id = req.params("id");
            Show list = collection("Show").findById(id);
            if(list == null){
                res.send("Show not found");
                return;
            }
            var seats = req.params("seats");

            list.increaseSeatsTaken(seats);
            collection("Show").updateById(id, list);
            res.json(list);
        });

        app.post("rest/ticket", (req, res) ->{
            var ticket = req.body(Ticket.class);
            var createdTicket = collection("Ticket").save(ticket);
            res.json(createdTicket);
        });

    }
}
