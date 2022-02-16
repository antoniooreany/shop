package com.gorshkov.shop.model;

import com.gorshkov.shop.model.Connector;

import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutor {
    public static boolean execute(String query) {
        try (Statement statement = Connector.getStatement();) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("The query is illegal", e);
        }
    }
}
