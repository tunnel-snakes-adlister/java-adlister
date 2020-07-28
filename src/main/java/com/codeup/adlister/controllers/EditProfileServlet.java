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

@WebServlet(name="controllers.EditProfileServlet", urlPatterns = "/edit-profile")
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/edit-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws NullPointerException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        String updatedUsername = request.getParameter("updatedUsername");
        String updatedEmail = request.getParameter("updatedEmail");
        String updatedPassword = request.getParameter("updatedPassword");
        String passwordConfirmation = request.getParameter("confirm_password");

        if(updatedUsername == null) {
            updatedUsername = user.getUsername();
        }else {
            DaoFactory.getUsersDao().updateUsername(updatedUsername, user);
            user.setUsername(updatedUsername);
            response.sendRedirect("/profile");
        }

        if(updatedEmail == null) {
            updatedEmail = user.getEmail();
        }else {
            DaoFactory.getUsersDao().updateEmail(updatedEmail, user);
            user.setEmail(updatedEmail);
            response.sendRedirect("/profile");
        }

        if(updatedPassword != null && updatedPassword.equals(passwordConfirmation)) {
            DaoFactory.getUsersDao().updatePassword(Password.hash(updatedPassword), user);
            response.sendRedirect("/profile");
        }
    }
}
