package com.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.validators.*;

@WebFilter("/register")
public class RegisterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;

        try {
            if (request.getParameter("role") == null) {
                throw new IllegalArgumentException("Role is not valid");
            }
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.addHeader("Access-Control-Allow-Headers", "*");
            res.addHeader("Access-Control-Allow-Methods", "*");
            ValidateInput.role(request.getParameter("role"));
            ValidateInput.isAlphabeticWithSpace("first name", request.getParameter("firstName"));
            ValidateInput.isAlphabeticWithSpace("last name", request.getParameter("lastName"));
            ValidateInput.isAlphanumeric("username", request.getParameter("username"));
            ValidateInput.isNumeric("phone number", request.getParameter("phoneNumber"));
            ValidateInput.isEmail("email", request.getParameter("email"));
            ValidateInput.isNumeric("age", request.getParameter("age"));
            ValidateInput.isAlphabetic("sex", request.getParameter("sex"));
            ValidateInput.isPassword("password", request.getParameter("password"));
            ValidateInput.userExist(request.getParameter("username"), request.getParameter("email"));
            chain.doFilter(request, response);

        } catch (Exception e) {
            Gson gson = new Gson();
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().println(gson.toJson(new Response<Object>(400, e.getMessage(), null)));
        }
    }

    @Override
    public void destroy() {

    }

}
