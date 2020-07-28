package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.AdCategory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (request.getParameter("title").isEmpty()) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Please enter a title');");
            out.println("location='/ads/create';</script>");
        } else if (request.getParameter("description").isEmpty()) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Please enter a description');");
            out.println("location='/ads/create';</script>");
        } else {
            Ad ad = new Ad(
                    user.getId(),
                    request.getParameter("title"),
                    request.getParameter("description")
            );
            long adId = DaoFactory.getAdsDao().insert(ad);
            AdCategory adCategory = new AdCategory(
                    adId,
                    parseLong(request.getParameter("category"))
            );
            DaoFactory.getAdsCategoriesDao().insert(adCategory);
            response.sendRedirect("/ads");
        }
    }
}
