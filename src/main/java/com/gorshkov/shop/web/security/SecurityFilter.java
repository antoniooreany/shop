package com.gorshkov.shop.web.security;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SecurityFilter implements javax.servlet.Filter {

    private List<String> tokens;

    public SecurityFilter(List<String> tokens) {
        this.tokens = tokens;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        Cookie[] cookies = httpServletRequest.getCookies();
        boolean isValid = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user-token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (tokens.contains(token)) {
                        isValid = true;
                    }
                    break;
                }
            }
        }
        if (!isValid) {
            httpServletResponse.sendRedirect("/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
