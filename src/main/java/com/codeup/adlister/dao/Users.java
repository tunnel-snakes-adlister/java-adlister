package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    User findByUserId(String id);
    Long insert(User user);
    boolean isAdmin(User user);
    Long editProfile(User user);
}
