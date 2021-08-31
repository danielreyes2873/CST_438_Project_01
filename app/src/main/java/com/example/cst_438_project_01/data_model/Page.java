package com.example.cst_438_project_01.data_model;

import java.util.ArrayList;

/**
 * This class allows us to take in a json string from the API and turn it into a POJO for use in our view classes
 *
 */
public class Page {
    private ArrayList<String> names;
    private ArrayList<String> categories;
    private ArrayList<String> descriptions;
    private ArrayList<String> commonLocations;
    private ArrayList<String> imageURL;
    private ArrayList<ArrayList<String>> drops;

    public void addItem(String jsonRecord) {
        /* Example Record
        {
              "data": {
                "category": "monsters",
                "common_locations": [
                  "Hyrule Field",
                  "East Necluda"
                ],
                "description": "This heavyweight species of monster can be found all over Hyrule. They're physically very strong, their legs along strong enough to resist the force of a bomb blast. They're much more dangerous than the Bokoblins. In fact, Moblins have been known to pick up Bokoblins and throw them as makeshift projectile weapons.",
                "drops": [
                  "moblin horn",
                  "moblin fang"
                ],
                "id": 108,
                "image": "https://botw-compendium.herokuapp.com/api/v2/entry/moblin/image",
                "name": "moblin"
              }
            }
         */
    }
}
