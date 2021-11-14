package com.controllers;

import java.io.IOException;
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

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        User user = Database.fetch(username);
        if (user != null) {
            String role = user.getRole();
            if (role.equals("Admin")) {
                Admin admin = (Admin) user;
                admin.login(password);
                String json = gson.toJson(new Response<User>(200, "Logged In", user));
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                res.getWriter().write(json);
            }
            if (role.equals("Guest")) {
                Guest guest = (Guest) user;
                guest.login(password);
                String json = gson.toJson(new Response<User>(200, "Logged In", user));
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                res.getWriter().write(json);

            }
        } else {
            this.sendResponse(req, res, "Incorrect username or password");
        }
    }

    private void sendResponse(HttpServletRequest req, HttpServletResponse res, String msg) throws IOException {
        String json = gson.toJson(new Response<Object>(400, msg, null));
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(json);
    }

}
