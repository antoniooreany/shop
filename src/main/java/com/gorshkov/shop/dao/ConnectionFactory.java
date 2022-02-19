package com.gorshkov.shop.dao;

import com.gorshkov.shop.templater.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            Properties properties = PropertiesReader.getProperties();
            String dbUrl = properties.getProperty("dbUrl");
            String dbUser = properties.getProperty("dbUser");
            String dbPassword = properties.getProperty("dbPassword");
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException("Connection to the DB cannot be established with the current credentials", e);
        }
    }
}
