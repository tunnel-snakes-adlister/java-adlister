package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="controllers.AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (DaoFactory.getUsersDao().isAdmin(user)) {
            request.setAttribute("ads", DaoFactory.getAdsDao().all());
            request.setAttribute("users", DaoFactory.getUsersDao().allUsers());
            request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }else {
            response.sendRedirect("/ads");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

}
