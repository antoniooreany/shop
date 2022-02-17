package com.gorshkov.shop.dao;

import com.gorshkov.shop.templater.Connector;
import com.gorshkov.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class ProductsDao {

    private final static String SELECT_ALL_FROM_DB = "SELECT * FROM db.products;";

    private static final String DELIMITER = "', '";
    private static final String INSERT_INTO = "INSERT INTO db.products VALUES ('";
    private static final String CLOSE_BRACKET = "');";

    private static final String DELETE = "DELETE FROM db.products WHERE id='";

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

    public void processAdd(HttpServletRequest request, Map<String, Object> pageVariables) {

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

        String insertQuery = createInsertQuery(fields);
        boolean execute = execute(insertQuery);
    }

    public static String createInsertQuery(ArrayList<String> fields) {
        StringJoiner joiner = new StringJoiner(DELIMITER,
                INSERT_INTO, CLOSE_BRACKET);
        for (String field : fields) {
            joiner.add(field);
        }
        return joiner.toString();
    }

    private static final String UPDATE = "UPDATE db.products SET id='";

    public void processUpdate(HttpServletRequest request, Map<String, Object> pageVariables) {

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

        String updateQuery = createUpdateQuery(fields);
        boolean execute = execute(updateQuery);
    }

    public static String createUpdateQuery(ArrayList<String> fields) {
        return UPDATE + fields.get(0) +
                "', name='" + fields.get(1) +
                "', price='" + fields.get(2) +
                "' WHERE id='" +
                fields.get(0) +
                "';";
    }



    public void processDelete(HttpServletRequest request, Map<String, Object> pageVariables) {

        String id = request.getParameter("id");

        pageVariables.put("id", id == null ? "" : id);

        ArrayList<String> fields = new ArrayList<>();
        fields.add(id);

        String updateQuery = createDeleteQuery(fields);
        boolean execute = execute(updateQuery);
    }

    public static String createDeleteQuery(ArrayList<String> fields) {
        return DELETE + fields.get(0) + "';";
    }

    public static boolean execute(String query) {
        try (Statement statement = Connector.getStatement();) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("The query is illegal", e);
        }
    }
}
