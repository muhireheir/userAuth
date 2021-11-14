package com.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.app.*;
import com.db.Database;
import com.dtos.User;
import com.google.gson.Gson;

@WebServlet("/login")
@MultipartConfig
public class Login extends HttpServlet {
    LinkedHashMap<String, String> resp;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        User user = Database.fetch(username);
        if (user != null) {
            String role = user.getRole();

        } else {

            resp.put("message", "user not found");
            this.sendResponse(req, res, HttpServletResponse.SC_CREATED);
        }

        // if (role.equals("Admin")) {
        // // Admin admin = (Admin) user;
        // // admin.login(password);
        // }
        // if (role.equals("Guest")) {
        // // Guest guest = (Guest) user;
        // // guest.login(password);
        // }

    }

    private void sendResponse(HttpServletRequest req, HttpServletResponse res, int status) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(this.resp);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(json);
    }

}
