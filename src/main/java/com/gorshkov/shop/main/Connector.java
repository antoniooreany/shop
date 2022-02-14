package com.gorshkov.shop.main;

import java.sql.*;

public class Connector {
    private final static String URL = "jdbc:mysql://localhost:3306";
    private final static String USER = "user";
    private final static String PASSWORD = "7777777";

    public static Statement getStatement() {
        Statement statement;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);){
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Connection to the DB cannot be established with the current credentials", e);
        }
        return statement;
    }
}
