package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            if (username.trim().isEmpty()) {
                request.setAttribute("usernameEmpty", "Please enter a username");
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } else if (DaoFactory.getUsersDao().checkIfUsernameTaken(username)) {
                request.setAttribute("usernameTaken", "The username you entered is already taken");
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } else if (email.isEmpty()) {
                request.setAttribute("emailEmpty", "Please enter an email");
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } else if (password.isEmpty()) {
                request.setAttribute("passwordEmpty", "Please enter a password");
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } else if (!password.equals(passwordConfirmation)) {
                request.setAttribute("passwordDifferent", "Passwords do not match");
                request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            } else {
                User user = new User(username.trim(), email, password);
                DaoFactory.getUsersDao().insert(user);
                response.sendRedirect("/login");
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
