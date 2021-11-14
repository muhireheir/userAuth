package com.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Admin;
import com.app.Guest;
import com.db.Database;
import com.google.gson.Gson;

@WebServlet("/register")
@MultipartConfig
public class Register extends HttpServlet {
    LinkedHashMap<String, String> resp;

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

        if (role.equals("Admin")) {
            resp = new LinkedHashMap<>();
            Admin user = new Admin();
            user.register(firstName, lastName, username, password, email, sex, phoneNumber, age, role);
            Database.insert(user.getUsername(), user);
            resp.put("message", "new Admin user Created!");
            this.sendResponse(req, res, HttpServletResponse.SC_CREATED);
        } else if (role.equals("Guest")) {
            resp = new LinkedHashMap<>();
            Guest user = new Guest();
            user.register(firstName, lastName, username, password, email, sex, phoneNumber, age, role);
            Database.insert(user.getUsername(), user);
            resp.put("message", "new Guest user Created!");
            this.sendResponse(req, res, HttpServletResponse.SC_CREATED);
        } else {
            resp = new LinkedHashMap<>();
            resp.put("message", "Invalid user type");
            this.sendResponse(req, res, HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    private void sendResponse(HttpServletRequest req, HttpServletResponse res, int status) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(this.resp);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(json);
    }

}
