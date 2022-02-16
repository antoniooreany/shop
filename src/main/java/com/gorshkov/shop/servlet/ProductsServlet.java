package com.gorshkov.shop.servlet;

import com.gorshkov.shop.main.Connector;
import com.gorshkov.shop.main.Product;
import com.gorshkov.shop.main.ProductService;
import com.gorshkov.shop.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {

    private final ProductService productService;

    public ProductsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = null; //todo to DB
        try {
            productList = findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setStatus(HttpServletResponse.SC_OK);

        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("products", productList);
        String page = pageGenerator.getPage("products.html", pageVariables);
        response.getWriter().println(page);
    }

    private List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();

        try (Statement statement = Connector.getStatement();) {
            String query = "SELECT * FROM db.products;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                productList.add(new Product(id, name, price));
            }
        }
        return productList;
    }
}
