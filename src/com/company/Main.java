package com.company;
import static org.dizitart.no2.FindOptions.*;
import static org.dizitart.no2.objects.filters.ObjectFilters.*;
import com.company.models.User;
import com.company.models.Show;
import com.company.models.Salon;
import com.company.models.Movie;
import com.company.models.Ticket;

import express.Express;
import express.database.CollectionOptions;

import java.util.List;

import static express.database.Database.collection;

public class Main {

    public static void main(String[] args) {
        // write your code here
        var app = new Express();
        app.enableCollections(CollectionOptions.ENABLE_WATCHER); // makes the collections reactive

        new Auth(app);
        new Cinema(app);

        app.listen(4000);
    }

}
