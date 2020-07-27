package com.codeup.adlister.dao;

import com.codeup.adlister.models.Category;

import java.util.List;

public interface Categories {
    // get a a list of all the categories
    List<Category> all();
    // insert a new category and return the new category's id
    Long insert(Category category);
    // returns category of an individual ad
    String whichCategory(String adId);
}
