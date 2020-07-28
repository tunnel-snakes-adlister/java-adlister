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

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/edit-ad/*")
public class EditAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        String adId = request.getPathInfo().substring(1);
        Ad ad = DaoFactory.getAdsDao().getAd((parseInt(adId)));
        request.getSession().setAttribute("ad", ad);

        request.setAttribute("ad", DaoFactory.getAdsDao().individualAd(adId));
        request.setAttribute("user", DaoFactory.getUsersDao().findByUserId(adId));
        request.setAttribute("category", DaoFactory.getCategoriesDao().whichCategory(adId));
        request.setAttribute("categories", DaoFactory.getCategoriesDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");

        Ad ad = (Ad) request.getSession().getAttribute("ad");


        String updatedTitle = request.getParameter("updatedTitle");
        String updatedDescription = request.getParameter("updatedDescription");
        System.out.println(ad.getTitle());
        System.out.println(ad.getDescription());
        //handle if any entry is empty
        //pass new values to DAO factory and method to update ad

        if(updatedTitle.isEmpty()) {
            updatedTitle = ad.getTitle();
        }else {
            DaoFactory.getAdsDao().updateTitle(updatedTitle, ad);
            ad.setTitle(updatedTitle);
        }

        if(updatedDescription.isEmpty()) {
//            System.out.println(ad.getDescription());
            DaoFactory.getAdsDao().updateDescription(ad.getDescription(), ad);
        }else {
            DaoFactory.getAdsDao().updateDescription(updatedDescription, ad);
            ad.setDescription(updatedDescription);
        }

        //need to add an update method in MYSQLAdsCategoriesDao to update table with new category
        AdCategory adCategory = new AdCategory(
                ad.getId(),
                parseLong(request.getParameter("category"))
        );
        DaoFactory.getAdsCategoriesDao().update(adCategory);
//
//        System.out.println(ad.getTitle());
//        System.out.println(ad.getDescription());

        response.sendRedirect("/profile");



    }
}
