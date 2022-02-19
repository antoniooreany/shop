package com.gorshkov.shop.dao;

import com.gorshkov.shop.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper {
    public Product mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .build();
    }
}
