package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import java.util.ArrayList;
import java.util.List;

public class ListAdsDao implements Ads {
    private List<Ad> ads;


    public List<Ad> all() {
        if (ads == null) {
            ads = generateAds();
        }
        return ads;
    }

    public Long insert(Ad ad) {
        // make sure we have ads
        if (ads == null) {
            ads = generateAds();
        }
        // we'll assign an "id" here based on the size of the ads list
        // really the dao would handle this
        ad.setId((long) ads.size());
        ads.add(ad);
        return ad.getId();
    }

    @Override
    public List<Ad> individualAd(String adId) {
        return null;
    }

    @Override
    public List<Ad> userAds(User user) {
        return null;
    }

    @Override
    public List<Ad> searchTitle(String search) {
        return null;
    }

    @Override
    public List<Ad> searchCategory(String search) {
        return null;
    }

    public List<Ad> search(String Search) {
        return null;
    }

    public boolean updateTitle(String updatedTitle, Ad ad){return false;}

    public boolean updateDescription(String updatedDescription, Ad ad){return false;}

    @Override
    public Ad getAd(int id) {
        return null;
    }

    @Override
    public boolean deleteAd(int id) {
        return false;
    }

    private List<Ad> generateAds() {
        List<Ad> ads = new ArrayList<>();
        ads.add(new Ad(
            1,
            1,
            "playstation for sale",
            "This is a slightly used playstation"
        ));
        ads.add(new Ad(
            2,
            1,
            "Super Nintendo",
            "Get your game on with this old-school classic!"
        ));
        ads.add(new Ad(
            3,
            2,
            "Junior Java Developer Position",
            "Minimum 7 years of experience required. You will be working in the scripting language for Java, JavaScript"
        ));
        ads.add(new Ad(
            4,
            2,
            "JavaScript Developer needed",
            "Must have strong Java skills"
        ));
        return ads;
    }
}
