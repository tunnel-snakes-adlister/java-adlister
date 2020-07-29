package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "controllers.DeleteAdServlet", urlPatterns = "/delete-ad/*")
public class DeleteAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        String adId = request.getPathInfo().substring(1);
        DaoFactory.getAdsDao().deleteAd(parseInt(adId));
        response.sendRedirect("/profile");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
