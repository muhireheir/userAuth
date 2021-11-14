package com.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Admin;
import com.app.Guest;
import com.db.Database;
import com.dtos.User;
import com.google.gson.Gson;

@WebServlet("/register")
@MultipartConfig
public class Register extends HttpServlet {
    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String username = req.getParameter("username");
        String role = req.getParameter("role");
        int age = Integer.parseInt(req.getParameter("age"));
        String sex = req.getParameter("sex");
        String password = req.getParameter("password");
        User user = null;
        if (role.equals("Admin")) {
            user = new Admin();
        } else {
            user = new Guest();
        }
        try {
            user.register(firstName, lastName, username, password, email, sex, phoneNumber, age, role);
            Database.insert(user.getUsername(), user);
            Gson gson = new Gson();
            String json = gson.toJson(new Response<User>(200, "new  user was Created!", user));
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.getWriter().write(json);
        } catch (Exception e) {
            gson = new Gson();
            this.sendResponse(req, res, e.getMessage());
        }

    }

    private void sendResponse(HttpServletRequest req, HttpServletResponse res, String msg) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(new Response<Object>(400, msg, null));
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(json);
    }
}
