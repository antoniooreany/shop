package com.gorshkov.shop;

import com.gorshkov.shop.dao.ConnectionFactory;
import com.gorshkov.shop.dao.JdbcProductsDao;
import com.gorshkov.shop.service.ProductsService;
import com.gorshkov.shop.web.*;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.ArrayList;
import java.util.List;

public class Starter {
    public static void main(String[] args) throws Exception {
        List<String> tokens = new ArrayList<>();

        JdbcProductsDao jdbcProductsDao = new JdbcProductsDao(new ConnectionFactory());
        ProductsService productsService = new ProductsService(jdbcProductsDao);

        ProductsServlet productsServlet = new ProductsServlet(productsService, tokens);
        ProductsAddServlet productsAddServlet = new ProductsAddServlet(productsService);
        ProductsUpdateServlet productsUpdateServlet = new ProductsUpdateServlet(productsService);
        ProductsDeleteServlet productsDeleteServlet = new ProductsDeleteServlet(productsService);
        LoginServlet loginServlet = new LoginServlet(tokens);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(productsServlet), "/products");
        contextHandler.addServlet(new ServletHolder(productsAddServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(productsUpdateServlet), "/products/update/*");
        contextHandler.addServlet(new ServletHolder(productsDeleteServlet), "/products/delete");
        contextHandler.addServlet(new ServletHolder(loginServlet), "/login");


        Server server = new Server(3000);
        server.setHandler(contextHandler);

        server.start();
    }
}
