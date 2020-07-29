package com.codeup.adlister.dao;

import com.codeup.adlister.models.AdCategory;
import com.codeup.adlister.models.Category;

import java.util.List;

public interface AdsCategories {
    // get a a list of all the adcategories
    List<AdCategory> all();
    // insert a new adcategory and return the new ad_id/category's id
    boolean insert(AdCategory adCategory);
    //update category - ad relation after a user updated ad
    boolean update(AdCategory adCategory);

    boolean delete(int adId);
}
