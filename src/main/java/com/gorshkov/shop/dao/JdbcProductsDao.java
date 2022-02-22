package com.gorshkov.shop.dao;

import com.gorshkov.shop.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductsDao implements ProductsDao {

    private static final String SELECT_ALL_FROM_DB = "SELECT * FROM db.products;";
    private static final String UPDATE = "UPDATE db.products SET id=";
    private static final String INSERT_INTO_DB_PRODUCTS_VALUES = "INSERT INTO db.products VALUES (";
    private static final String DELIMITER = ", ";
    private static final String CLOSE_BRACKET = ");";
    private static final String DELETE = "DELETE FROM db.products WHERE id=";

    private final DataSource dataSource;

    public JdbcProductsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Product> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_DB);) {
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                productList.add(new ProductRowMapper().mapRow(resultSet));
            }
            return productList;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot execute select", e);
        }
    }

    public void save(Product product) {
        ArrayList<String> fields = getFields(product);
        String insertQuery = createInsertQuery();
        executeInsertQuery(insertQuery, fields);
    }

    public void update(Product product) {
        ArrayList<String> fields = getFields(product);
        String updateQuery = createUpdateQuery();
        executeUpdateQuery(updateQuery, fields);
    }

    public void delete(int productId) {
        ArrayList<String> fields = new ArrayList<>();
        fields.add(String.valueOf(productId));
        String deleteQuery = createDeleteQuery();
        executeDeleteQuery(deleteQuery, fields);
    }

    @Override
    public Product findById(int id) {
        String findByIdQuery = createFindByIdQuery();
        ResultSet resultSet = executeFindByIdQuery(findByIdQuery, id);
        try {
//            resultSet.next();
            return new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"));
        } catch (SQLException e) {
            throw new RuntimeException("Cannot select product by id", e);
        }

//        try (Connection connection = connectionFactory.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            return new Product(resultSet.getInt("id"),
//                    resultSet.getString("name"),
//                    resultSet.getDouble("price"));
//        } catch (SQLException e) {
//            throw new RuntimeException("Cannot select product by id", e);
//        }
    }

    private ResultSet executeFindByIdQuery(String query, int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException("Cannot select product by id", e);
        }
    }

    private String createFindByIdQuery() {
        return "SELECT * FROM db.products WHERE id=?;";
    }

    private String createInsertQuery() {
        return INSERT_INTO_DB_PRODUCTS_VALUES
                + "?"
                + DELIMITER
                + "?"
                + DELIMITER
                + "?"
                + CLOSE_BRACKET;
    }

    private String createUpdateQuery() {
        return UPDATE + "?" +
                ", name=?" +
                ", price=?" +
                " WHERE id=?" +
                ";";
    }

    private String createDeleteQuery() {
        return DELETE + "?;";
    }

    private void executeUpdateQuery(String query, ArrayList<String> fields) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(fields.get(0)));
            preparedStatement.setString(2, fields.get(1));
            preparedStatement.setDouble(3, Double.parseDouble(fields.get(2)));
            preparedStatement.setInt(4, Integer.parseInt(fields.get(0)));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot update product", e);
        }
    }

    private void executeInsertQuery(String query, ArrayList<String> fields) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(fields.get(0)));
            preparedStatement.setString(2, fields.get(1));
            preparedStatement.setDouble(3, Double.parseDouble(fields.get(2)));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot insert product", e);
        }
    }

    private void executeDeleteQuery(String query, ArrayList<String> fields) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(fields.get(0)));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot delete product", e);
        }
    }

    private ArrayList<String> getFields(Product product) {
        int id = product.getId();
        String name = product.getName();
        double price = product.getPrice();
        ArrayList<String> fields = new ArrayList<>();
        fields.add(String.valueOf(id));
        fields.add(name);
        fields.add(String.valueOf(price));
        return fields;
    }
}
