package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = DaoFactory.getUsersDao().findByUsername(username);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

//        boolean validAttempt = Password.check(password, user.getPassword());

        if (user == null) {
            out.println("<script type='text/javascript'>");
            out.println("alert('No user exists with that username');");
            out.println("location='login';</script>");
//            response.sendRedirect("/login");
//            return;
        } else if (!Password.check(password, user.getPassword())) {
            out.println("<script type='text/javascript'>");
            out.println("alert('The password you entered is incorrect');");
            out.println("location='login';</script>");
        } else if (Password.check(password, user.getPassword())) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/profile");
        }


//        if (validAttempt) {
//            request.getSession().setAttribute("user", user);
//            response.sendRedirect("/profile");
//        } else {
//            response.sendRedirect("/login");
//        }
    }
}
