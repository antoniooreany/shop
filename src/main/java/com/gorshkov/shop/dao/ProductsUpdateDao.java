package com.gorshkov.shop.dao;

import com.gorshkov.shop.model.Connector;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class ProductsUpdateDao {

    private static final String UPDATE = "UPDATE db.products SET id='";

//    UPDATE table_name
//    SET id = id, name = name, price = price
//    WHERE condition;

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

    public static boolean execute(String query) {
        try (Statement statement = Connector.getStatement();) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("The query is illegal", e);
        }
    }
}
