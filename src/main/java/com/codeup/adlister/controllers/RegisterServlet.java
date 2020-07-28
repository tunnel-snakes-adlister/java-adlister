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

        // validate input
        boolean inputHasErrors = username.trim().isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || (!password.equals(passwordConfirmation))
                || username.equalsIgnoreCase(DaoFactory.getUsersDao().checkIfUsernameTaken(username));

        if (username.trim().isEmpty()) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Please enter a username');");
            out.println("location='register';</script>");
        } else if (username.equalsIgnoreCase(DaoFactory.getUsersDao().checkIfUsernameTaken(username))) {
            out.println("<script type='text/javascript'>");
            out.println("alert('The username you entered is already taken');");
            out.println("location='register';</script>");
        } else if (email.isEmpty()) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Please enter an email');");
            out.println("location='register';</script>");
        } else if (password.isEmpty()) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Please enter a password');");
            out.println("location='register';</script>");
        } else if (!password.equals(passwordConfirmation)) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Your passwords don't match');");
            out.println("location='register';</script>");
        } else {
            User user = new User(username.trim(), email, password);
            DaoFactory.getUsersDao().insert(user);
            response.sendRedirect("/login");
        }

//        if (inputHasErrors) {
//            response.sendRedirect("/register");
//            return;
//        }

        // create and save a new user

    }
}
