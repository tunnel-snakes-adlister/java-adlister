package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdCategory;
import com.codeup.adlister.models.Category;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsCategoriesDao implements AdsCategories{
    private Connection connection;

    public MySQLAdsCategoriesDao(Config config) {
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
    public List<AdCategory> all() {
        List<AdCategory> adCats = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ad_category");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                adCats.add(new AdCategory(rs.getInt("ad_id"), rs.getInt("category_id")));
            }
            return adCats;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ad/categories.", e);
        }
    }

    @Override
    public boolean insert(AdCategory adCategory) {
        String insertQuery = "INSERT INTO ad_category(ad_id, category_id) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setLong(1, adCategory.getAdId());
            stmt.setLong(2, adCategory.getCategoryId());
            int updated = stmt.executeUpdate();
            return updated == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting an entry.", e);
        }
    }

    @Override
    public boolean update(AdCategory adCategory) {
        String query = "UPDATE ad_category SET category_id = ? WHERE ad_id = ?";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setLong(1, adCategory.getCategoryId());
            prepStmt.setLong(2, adCategory.getAdId());
            int updated = prepStmt.executeUpdate();
            return updated == 1;
        } catch (SQLException throwables) {
            throw new RuntimeException("Error updating ad_category table", throwables);
        }
    }

    @Override
    public boolean delete(int adId) {
        String query = "DELETE FROM ad_category WHERE ad_id = ?";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setLong(1, adId);
            int deleted = prepStmt.executeUpdate();
            return deleted == 1;
        } catch (SQLException throwables) {
            throw new RuntimeException("Error deleting ad_category relation row", throwables);
        }
    }
}
