package com.gorshkov.shop.dao;

import com.gorshkov.shop.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper {
    public static Product mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getObject("id", Integer.class);
        String name = resultSet.getObject("name", String.class);
        double price = resultSet.getObject("price", Double.class);
        return new Product(id, name, price);
    }
}
