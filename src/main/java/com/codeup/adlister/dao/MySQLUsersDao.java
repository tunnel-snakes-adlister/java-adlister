package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public boolean checkIfUsernameTaken(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
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

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        PreparedStatement prepStmt = null;
        try {
            prepStmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
            return users;
        } catch (SQLException throwables) {
            throw new RuntimeException("Error retrieving all users.", throwables);
        }
    }

    @Override
    public User findUser(int userId) {
        String query = "SELECT * FROM users WHERE id = ? LIMIT 1";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setLong(1, userId);
            ResultSet rs = prepStmt.executeQuery();
            return extractUser(rs);
        } catch (SQLException throwables) {
            throw new RuntimeException("Error finding user", throwables);
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        User user = findUser(userId);

        List<Ad> ads = DaoFactory.getAdsDao().userAds(user);

        for(Ad ad : ads) {
            DaoFactory.getAdsDao().deleteAd((int) ad.getId());
        }

        String query = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(query);
            prepStmt.setLong(1, user.getId());
            int deleted = prepStmt.executeUpdate();
            return deleted == 1;
        } catch (SQLException throwables) {
            throw new RuntimeException("Error deleting user", throwables);
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
