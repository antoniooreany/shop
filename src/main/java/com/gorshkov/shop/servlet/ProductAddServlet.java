package com.gorshkov.shop.servlet;

import com.gorshkov.shop.main.Connector;
import com.gorshkov.shop.main.Product;
import com.gorshkov.shop.main.ProductAddService;
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

public class ProductAddServlet extends HttpServlet {

    private final ProductAddService productAddService;

    public ProductAddServlet(ProductAddService productAddService) {
        this.productAddService = productAddService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("message", "");

        response.getWriter().println(PageGenerator.instance().getPage("productAdd.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

//        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

//        String message = request.getParameter("message");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        response.setContentType("text/html;charset=utf-8");

//        if (message == null || message.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        } else {
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
//        pageVariables.put("message", message == null ? "" : message);
        pageVariables.put("id", id == null ? "" : id);
        pageVariables.put("name", name == null ? "" : name);
        pageVariables.put("price", price == null ? "" : price);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("productAdd.html", pageVariables);
        response.getWriter()
                .println(page);

        String idValue = (String) pageVariables.get("id");
        String nameValue = (String) pageVariables.get("name");
        String priceValue = (String) pageVariables.get("price");

        ArrayList<String> fields = new ArrayList<>();
        fields.add(idValue);
        fields.add(nameValue);
        fields.add(priceValue);

        String insertQuery = new QueryCreator().createInsertQuery(fields);
        System.out.println(insertQuery);

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

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }


}
