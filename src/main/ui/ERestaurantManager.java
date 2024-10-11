package ui;

import model.Restaurant;
import java.util.ArrayList;

// Represents the E Restaurant Mangager that manages the restaurants
public class ERestaurantManager {

    private ArrayList<Restaurant> restaurants;   //the list of restaurants on the E restaurant manager

    /*
     * EFFECTS: creates a list of restaurants
     */
    public ERestaurantManager() {
        restaurants = new ArrayList<>();
    }

    /*
     * MOFIFIES: this
     * EFFECTS: adds restaurants to the ArrayList
     */
    public void addRestaurant(Restaurant restaurant) {
        //STUB
    }

    //getters
    public ArrayList<Restaurant> getRestaurants() {
        return null;
    }
}
