package com.gorshkov.shop.dao;

import com.gorshkov.shop.model.Connector;
import com.gorshkov.shop.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao {

    private final static String SELECT_ALL_FROM_DB = "SELECT * FROM db.products;";

    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        
        try (Statement statement = Connector.getStatement();) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_DB);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                productList.add(new Product(id, name, price));
            }
        } catch (SQLException e) {
            throw new RuntimeException("The SQL query is incorrect", e);
        }
        return productList;
    }
}
