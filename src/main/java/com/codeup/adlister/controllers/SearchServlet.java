package com.codeup.adlister.controllers;
import com.codeup.adlister.dao.Ads;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name="controllers.SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String search = request.getParameter("search");
        List<Ad> ads = DaoFactory.getAdsDao().searchTitle(search);
        List<Ad> ads2 = DaoFactory.getAdsDao().searchCategory(search);
        if (ads == null) {
            response.sendRedirect("/searchfail");
            return;
        }else {

            if (request.getParameter("sort").equals("sort1")) {
                request.getSession().setAttribute("ads", ads);
                response.sendRedirect("/search");
            } else if (request.getParameter("sort").equals( "cat")){
                request.getSession().setAttribute("ads",ads2);
                response.sendRedirect("/search");

            }
        }
//        boolean validAttempt = Password.check(password, user.getPassword());

//        if (validAttempt) {
//            request.getSession().setAttribute("user", user);
////            response.sendRedirect(request.getHeader("referer"));
//            response.sendRedirect("/profile");
//        } else {
//            response.sendRedirect("/login");
//        }
    }
}
