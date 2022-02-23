package com.gorshkov.shop;

import com.gorshkov.shop.dao.ConnectionFactory;
import com.gorshkov.shop.dao.JdbcProductsDao;
import com.gorshkov.shop.service.ProductsService;
import com.gorshkov.shop.web.security.SecurityFilter;
import com.gorshkov.shop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.EnumSet;
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

        SecurityFilter securityFilter = new SecurityFilter(tokens);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(productsServlet), "/products");

        contextHandler.addServlet(new ServletHolder(productsAddServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(productsUpdateServlet), "/products/update/*");
        contextHandler.addServlet(new ServletHolder(productsDeleteServlet), "/products/delete");

        contextHandler.addServlet(new ServletHolder(loginServlet), "/login");

        contextHandler.addFilter(new FilterHolder(securityFilter), "/products/add", EnumSet.of(DispatcherType.REQUEST)); //TODO another pathSpecs
        contextHandler.addFilter(new FilterHolder(securityFilter), "/products/update/*", EnumSet.of(DispatcherType.REQUEST)); //TODO another pathSpecs
        contextHandler.addFilter(new FilterHolder(securityFilter), "/products/delete", EnumSet.of(DispatcherType.REQUEST)); //TODO another pathSpecs

        Server server = new Server(3000);
        server.setHandler(contextHandler);

        server.start();
    }
}
