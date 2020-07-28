package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories{
    private Connection connection;

    public MySQLCategoriesDao(Config config) {
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
    public List<Category> all() {
        List<Category> cats = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cats.add(new Category(rs.getLong("id"), rs.getString("name")));
            }
            return cats;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all categories.", e);
        }
    }

    @Override
    public Long insert(Category category) {
        String insertQuery = "INSERT INTO categories(id, name) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, category.getId());
            stmt.setString(2, category.getName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new category.", e);
        }
    }

    @Override
    public String whichCategory(String adId) {
        String selectQuery = "SELECT name FROM categories\n" +
                "JOIN ad_category ac on categories.id = ac.category_id\n" +
                "JOIN ads a on ac.ad_id = a.id\n" +
                "WHERE a.id = " + adId;
        String category = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(selectQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                category = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

}
