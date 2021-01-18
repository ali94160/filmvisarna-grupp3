package com.company;
import static org.dizitart.no2.FindOptions.*;
import static org.dizitart.no2.objects.filters.ObjectFilters.*;
import com.company.models.User;
import com.company.models.Show;
import com.company.models.Salon;
import com.company.models.Movie;
import com.company.models.Ticket;

import express.Express;

import java.util.List;

import static express.database.Database.collection;

public class Main {

    public static void main(String[] args) {
        // write your code here
        var app = new Express();
        app.enableCollections();

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

        app.get("/rest/user",(req, res) ->{
            var user = collection("User").find();
            res.json(user);
        });

        app.get("/rest/ticket",(req, res) ->{
            var ticket = collection("Ticket").find();
            res.json(ticket);
        });

        app.post("rest/user", (req, res) ->{
            var user = req.body(User.class);
            var createdUser = collection("User").save(user);
            res.json(createdUser);
        });

        app.get("/rest/show/get-salon/:id",(req, res) ->{
            //get salon with showId
            var id = req.params("id");
            Show show = collection("Show").findById(id);
            Salon salon = collection("Salon").findById(show.getSalonId());

            System.out.println("found " + salon);
            //res.json(show);
            res.json(salon);
        });

        app.get("/rest/salon/get-show/:id",(req, res) ->{
            //get shows with salonId
            var id = req.params("id");
            // fetching the salon
            Salon salon = collection("Salon").findById(id);
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
            // get show from ticket
            var id = req.params("id");
            Ticket ticket = collection("Ticket").findById(id);
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
            // fetch and filter shows with matching salonId
            List<Ticket> tickets = collection("Ticket").find(eq("userId", user.getId()));
            if(tickets != null) {
                res.json(tickets);
            }
            else{
                res.send("Tickets not found");
            }
        });

        app.put("/rest/show/increase-seats/:id/:amount", (req, res) -> {
            var id = req.params("id");
            Show list = collection("Show").findById(id);
            String amount = req.params("amount");

            list.increaseSeatsTaken(amount);
            collection("Show").updateById(id, list);
            System.out.println(list);
            res.json(list);
        });

        app.post("rest/ticket", (req, res) ->{
            var ticket = req.body(Ticket.class);
            var createdTicket = collection("Ticket").save(ticket);
            res.json(createdTicket);
        });

        app.put("rest/show", (req, res) ->{
            var show = req.body(Show.class);
            if(show.getId() == null){
                res.send("No id provided");
            }
            var savedShow = collection("Show").save(show);
            res.json(savedShow);
        });

        app.listen(4000);
    }

}
