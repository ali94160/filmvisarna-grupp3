package com.company;

import express.Express;

public class Main {

    public static void main(String[] args) {
        // write your code here
        var app = new Express();
        app.enableCollections();
        app.get("/hello", (req, res) -> {
            res.send("<h1>Hello world</h1>");
        });

        app.listen(4000);
    }
}
