package com.gorshkov.shop.dao;

import com.gorshkov.shop.templater.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static String dbUrl;
    private static String dbUser;
    private static Properties properties;
    private static String dbPassword;

    public Connection getConnection() {
        try {
            properties = PropertiesReader.getProperties();
            dbUrl = properties.getProperty("dbUrl");
            dbUser = properties.getProperty("dbUser");
            dbPassword = properties.getProperty("dbPassword");
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException("Connection to the DB cannot be established with the current credentials", e);
        }
    }
}
