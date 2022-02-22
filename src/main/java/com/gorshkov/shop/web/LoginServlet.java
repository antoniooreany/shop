package com.gorshkov.shop.web;

import com.gorshkov.shop.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private final List<String> tokens;

    public LoginServlet(List<String> tokens) {
        this.tokens = tokens;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageGenerator.writePage(response.getWriter(),"login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("login");
        String password = request.getParameter("password");
        if (email != null && password != null) {
            String token = UUID.randomUUID().toString();
            tokens.add(token);
            Cookie cookie = new Cookie("user-token", token);
            response.addCookie(cookie);
        }
        response.sendRedirect("/products");
    }
}
