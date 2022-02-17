package com.gorshkov.shop.dao;

import com.gorshkov.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class JdbcProductsDao implements ProductDao {

    private static final String SELECT_ALL_FROM_DB = "SELECT * FROM db.products;";
    private static final String UPDATE = "UPDATE db.products SET id='";
    private static final String DELIMITER = "', '";
    private static final String INSERT_INTO = "INSERT INTO db.products VALUES ('";
    private static final String CLOSE_BRACKET = "');";
    private static final String DELETE = "DELETE FROM db.products WHERE id='";

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_DB);
            while (resultSet.next()) {
                productList.add(ProductRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("The SQL query is incorrect", e);
        }
        return productList;
    }

    public void processAdd(HttpServletRequest request, Map<String, Object> pageVariables) {
        ArrayList<String> fields = getFields(request, pageVariables);
        String insertQuery = createInsertQuery(fields);
        executeQuery(insertQuery);
    }

    public void processUpdate(HttpServletRequest request, Map<String, Object> pageVariables) {
        ArrayList<String> fields = getFields(request, pageVariables);
        String updateQuery = createUpdateQuery(fields);
        executeQuery(updateQuery);
    }

    public void processDelete(HttpServletRequest request, Map<String, Object> pageVariables) {
        String id = request.getParameter("id");
        pageVariables.put("id", id == null ? "" : id);
        ArrayList<String> fields = new ArrayList<>();
        fields.add(id);
        String updateQuery = createDeleteQuery(fields);
        executeQuery(updateQuery);
    }

    private String createInsertQuery(ArrayList<String> fields) {
        StringJoiner joiner = new StringJoiner(DELIMITER,
                INSERT_INTO, CLOSE_BRACKET);
        for (String field : fields) {
            joiner.add(field);
        }
        return joiner.toString();
    }

    private String createUpdateQuery(ArrayList<String> fields) {
        return UPDATE + fields.get(0) +
                "', name='" + fields.get(1) +
                "', price='" + fields.get(2) +
                "' WHERE id='" +
                fields.get(0) +
                "';";
    }

    private String createDeleteQuery(ArrayList<String> fields) {
        return DELETE + fields.get(0) + "';";
    }

    private void executeQuery(String query) {
        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("The query is illegal", e);
        }
    }

    private ArrayList<String> getFields(HttpServletRequest request, Map<String, Object> pageVariables) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        pageVariables.put("id", id == null ? "" : id);
        pageVariables.put("name", name == null ? "" : name);
        pageVariables.put("price", price == null ? "" : price);

        ArrayList<String> fields = new ArrayList<>();
        fields.add(id);
        fields.add(name);
        fields.add(price);
        return fields;
    }
}
