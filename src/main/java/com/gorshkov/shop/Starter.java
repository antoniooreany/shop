package com.gorshkov.shop;

import com.gorshkov.shop.dao.JdbcProductsDao;
import com.gorshkov.shop.service.ProductsService;
import com.gorshkov.shop.web.ProductsAddServlet;
import com.gorshkov.shop.web.ProductsDeleteServlet;
import com.gorshkov.shop.web.ProductsServlet;
import com.gorshkov.shop.web.ProductsUpdateServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {


        JdbcProductsDao jdbcProductsDao = new JdbcProductsDao();
        ProductsService productsService = new ProductsService(jdbcProductsDao);

        ProductsServlet productsServlet = new ProductsServlet(productsService);
        ProductsAddServlet productsAddServlet = new ProductsAddServlet(productsService);
        ProductsUpdateServlet productsUpdateServlet = new ProductsUpdateServlet(productsService);
        ProductsDeleteServlet productsDeleteServlet = new ProductsDeleteServlet(productsService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(productsServlet), "/products");
        contextHandler.addServlet(new ServletHolder(productsAddServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(productsUpdateServlet), "/products/update");
        contextHandler.addServlet(new ServletHolder(productsDeleteServlet), "/products/delete");

        Server server = new Server(3000);
        server.setHandler(contextHandler);

        server.start();
    }
}
