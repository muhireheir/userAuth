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
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        User user = Database.fetch(username);
        if (user != null) {
            String role = user.getRole();
            if (role.equals("Admin")) {
                Admin admin = (Admin) user;

                this.resp = new LinkedHashMap<>();
                this.resp.put("message", admin.login(password));
                this.sendResponse(req, res, HttpServletResponse.SC_UNAUTHORIZED);
            }
            if (role.equals("Guest")) {
                Guest guest = (Guest) user;
                this.resp = new LinkedHashMap<>();
                this.resp.put("message", guest.login(password));
                this.sendResponse(req, res, HttpServletResponse.SC_UNAUTHORIZED);

            }

        } else {
            this.resp = new LinkedHashMap<>();
            this.resp.put("message", "Incorrect username or password");
            this.sendResponse(req, res, HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void sendResponse(HttpServletRequest req, HttpServletResponse res, int status) throws IOException {
        String json = gson.toJson(this.resp);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.setStatus(status);
        res.getWriter().write(json);
    }

}
