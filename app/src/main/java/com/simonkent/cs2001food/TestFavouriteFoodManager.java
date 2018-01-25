package com.simonkent.cs2001food;

/**
 * Created by simonkent on 08/11/2016.
 * This is a test implementation which just returns a hard-coded string
 */
public class TestFavouriteFoodManager implements FavouriteFoodManager {

    @Override
    public String getFavouriteFood() {
        return "Rhubarb";
    }
}
