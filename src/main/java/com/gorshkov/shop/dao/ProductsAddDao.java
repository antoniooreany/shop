package com.gorshkov.shop.dao;

import com.gorshkov.shop.templater.Connector;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringJoiner;

public class ProductsAddDao {

    private static final String DELIMITER = "', '";
    private static final String INSERT_INTO = "INSERT INTO db.products VALUES ('";
    private static final String CLOSE_BRACKET = "');";

    public void process(HttpServletRequest request, Map<String, Object> pageVariables) {

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

    public static boolean execute(String query) {
        try (Statement statement = Connector.getStatement();) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("The query is illegal", e);
        }
    }
}
