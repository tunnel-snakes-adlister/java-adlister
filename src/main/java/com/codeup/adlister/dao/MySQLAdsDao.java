package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public List<Ad> individualAd(String adId) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = " + adId);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        long next = 0;
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                next = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
        return next;
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    public List<Ad> userAds(User user) {
        PreparedStatement stmt = null;
        try {
            String insertQuery = "SELECT * FROM ads WHERE user_id = ?";
            stmt = connection.prepareStatement(insertQuery);
            stmt.setLong(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }


    public List<Ad> searchTitle(String search) {
        PreparedStatement stmt = null;
        try {
            String insertQuery = "SELECT * FROM ads WHERE title = ?";
            stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, search);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }

    }

    public List<Ad> searchCategory(String search) {
        PreparedStatement stmt = null;
        try {
            String insertQuery = "SELECT * FROM ads JOIN ad_category ON ads.id = ad_category.ad_id JOIN categories ON ad_category.category_id = categories.id WHERE name = ?";
            stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, search);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }

    }

    public boolean updateTitle(String updatedTitle, Ad ad) {
        String query = "UPDATE ads SET title = ? WHERE id = ?";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, updatedTitle);
            prepStmt.setLong(2, ad.getId());
            int updated = prepStmt.executeUpdate();
            return updated == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating title", e);
        }
    }

    public boolean updateDescription(String updatedDescription, Ad ad) {
        String query = "UPDATE ads SET description = ? WHERE id = ?";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, updatedDescription);
            prepStmt.setLong(2, ad.getId());
            int updated = prepStmt.executeUpdate();
            return updated == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Description", e);
        }
    }

    public Ad getAd(int id) {
        String query = "SELECT * FROM ads WHERE id = ?";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setLong(1, id);
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            return extractAd(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Error getting ad", throwables);
        }
    }

}
