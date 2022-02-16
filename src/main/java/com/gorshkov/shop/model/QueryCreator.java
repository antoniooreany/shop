package com.gorshkov.shop.model;

import java.util.ArrayList;
import java.util.StringJoiner;

public class QueryCreator {
    public static String createInsertQuery(ArrayList<String> fields) {
        StringJoiner joiner = new StringJoiner("', '",
                "INSERT INTO db.products VALUES ('", "');");
        for (String field : fields) {
            joiner.add(field);
        }
        return joiner.toString();
    }

}
