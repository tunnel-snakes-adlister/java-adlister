package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndividualAdServlet", urlPatterns = "/ads/individual-ad/*")
public class IndividualAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getPathInfo().substring(1);
        request.setAttribute("ad", DaoFactory.getAdsDao().individualAd(id));
        request.getRequestDispatcher("/WEB-INF/ads/individual-ad.jsp").forward(request, response);
    }
}
