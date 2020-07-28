package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
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
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    public String checkIfUsernameTaken(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString("username");
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public User findByUserId(String id) {
        String query = "SELECT * FROM users WHERE id IN (SELECT user_id FROM ads WHERE id = ?) LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, id);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password")
        );
    }

    @Override
    public boolean isAdmin(User user) {
        String query = "SELECT is_admin FROM users WHERE id = ?";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setLong(1, user.getId());
            ResultSet rs = prepStmt.executeQuery();
            rs.next();
            if (rs.getLong("is_admin") == 1) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding user with matching ID");
        }
    }

    @Override
    public boolean updateUsername(String updatedUsername, User user) {
        String query = "UPDATE users SET username = ? WHERE id = ?";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, updatedUsername);
            prepStmt.setLong(2, user.getId());
            int updated = prepStmt.executeUpdate();
            return updated == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating username", e);
        }
    }

    @Override
    public boolean updateEmail(String updatedEmail, User user) {
        String query = "UPDATE users SET email = ? WHERE id = ?";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, updatedEmail);
            prepStmt.setLong(2, user.getId());
            int updated = prepStmt.executeUpdate();
            return updated == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating email", e);
        }
    }

    @Override
    public boolean updatePassword(String updatedPassword, User user) {
        String query = "UPDATE users SET password = ? WHERE id = ?";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setString(1, updatedPassword);
            prepStmt.setLong(2, user.getId());
            int updated = prepStmt.executeUpdate();
            return updated == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating username", e);
        }
    }

}
