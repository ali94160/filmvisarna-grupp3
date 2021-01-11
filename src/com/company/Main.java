package com.company;

import com.company.models.User;
import express.Express;

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

        app.post("rest/user", (req, res) ->{
            var user = req.body(User.class);
            var createdUser = collection("User").save(user);
            res.json(createdUser);
        });

        app.post("rest/user/:id", (req, res) ->{

        });

        app.listen(4000);
    }
}
