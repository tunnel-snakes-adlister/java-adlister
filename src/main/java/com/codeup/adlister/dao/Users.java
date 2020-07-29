package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    boolean checkIfUsernameTaken(String username);
    User findByUserId(String id);
    Long insert(User user);
    boolean isAdmin(User user);
    boolean updateUsername(String updatedUsername, User user);
    boolean updateEmail(String updatedEmail, User user);
    boolean updatePassword(String updatedPassword, User user);
    List<User> allUsers();
    boolean deleteUser(String userId);
}
