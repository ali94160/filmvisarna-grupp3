package com.company;

import com.company.models.User;
import com.company.utilities.HashPassword;
import express.Express;
import org.dizitart.no2.objects.filters.ObjectFilters;

import static express.database.Database.collection;

public class Auth {
    private Express app;

    public Auth(Express app){
        this.app = app;
        initAuth();
    }

    private void initAuth(){
        String secretSalt = "onfvLNFgreentea@haha.se%&/VHJVAWD&%AWDbd!Ö";

        app.post("/api/register",(req, res) -> {
            if(req.session("current-user") != null) {
                res.send("Already logged in");
                return;
            }

            var user = req.body(User.class);

            // validate if email already exist in one of the existing users
            User userInColl = collection("User").findOne(ObjectFilters.eq("email", user.getEmail()));
            if(userInColl != null){
                res.send("User already in use");
                return;
            }
            // Hash password for higher security
            String hashedPassword = HashPassword.hash(user.getPassword() + secretSalt);
            user.setPassword(hashedPassword);
            collection("User").save(user);

            // Set password to null so it does not leak to hacker
            // remember to add this before all res methods
            user.setPassword(null);

            // save user in the session of the client
            req.session("current-user", user);
            res.json(user);
        });

        app.post("/api/login", (req, res) -> {
            if(req.session("current-user") != null) {
                res.send("Already logged in");
                return;
            }

            var user = req.body(User.class);

            User userInColl = collection("User").findOne(ObjectFilters.eq("email", user.getEmail()));
            if(userInColl == null){
                // No user with matching email
                res.send("Bad credentials");
                return;
            }

            // validate password
            if(HashPassword.match(user.getPassword() + secretSalt, userInColl.getPassword())) {
                // correct -> login
                userInColl.setPassword(null);
                req.session("current-user", userInColl);
                res.json(userInColl);
            } else {
                // wrong password
                res.send("Bad credentials");
            }

        });

        app.get("/api/whoami", (req, res) ->{
            User user = req.session("current-user");
            res.json(user);
        });

        app.get("/api/logout", (req, res) ->{
            req.session("current-user", null);
            res.send("Logged out");
        });
    }
}
