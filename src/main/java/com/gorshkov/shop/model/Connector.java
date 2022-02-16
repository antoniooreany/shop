package com.gorshkov.shop.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
    private final static String URL = "jdbc:mysql://localhost:3306";
    private final static String USER = "user";
    private final static String PASSWORD = "7777777";

    public static Statement getStatement() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Connection to the DB cannot be established with the current credentials", e);
        }
    }
}
