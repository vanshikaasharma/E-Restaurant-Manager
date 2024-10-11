package model;

import java.util.ArrayList;
import java.util.List;

// Represents a restaurant having an name, location, cuisine type, menu, 
// the working hours, capacity of the restaurant, review of the restaurant and
// if there is delivery and dine in possible
public class Restaurant {

    private String restaurantName;                  //name of the restaurant
    private String restaurantLocation;              //location of the restaurant
    private String cuisineType;                     //the cuisine that is being served in the restaurant
    private ArrayList<Menu> restaurantMenu;         //menu of the restaurant
    private int openingHours;                        //the time the restaurant is open 
    private int closingHours;                        //the time the restaurant is closed
    private int capacity;                           //capacity of the restaurant
    private ArrayList<Review> restaurantReviews;     //reviews of the restaurant
    private boolean isDeliveryAvailable;            //delivery is available or not
    private boolean isDineInAvailable;              //dine-in is available is available or not

    /*
     * REQUIRES: restaurant, cuisine, has a non-zero length 
     * EFFECTS: name of the restaurant is set to restaurantName; location of 
     *          the restaurant is set to restaurantLocation; the cuisine of 
     *          the restaurant is cuisineType; the capacity of the restaurant
     *          is set to 10; the restaurant is set to be available for 
     *          dine-in, making isDineInAvailable = true; an ArrayList of 
     *          restaurantMenu is instantiated; an ArrayList of 
     *          restaurantReview is instantiated; the opening hour of the
     *          restaurant is set to 0 (12 AM);the closing hour of the
     *          restaurant is set to 0 (12 AM);
     */
    public Restaurant(String restaurantName, String restaurantLocation, String cuisineType) {
        //STUB
    }


    /* 
     * MODIFIES: this and Review
     * EFFECTS: adds a Review for the restaurant
     */
    public void addReview(Review review) {
        //STUB
    }

    /* 
     * MODIFIES: this and Menu
     * EFFECTS: adds a the item to the menu of the restaurant
     */
    public void addMenuItem(Menu item, int price) {
        //STUB
    }

    /* 
     * MODIFIES: this and Menu
     * EFFECTS: adds a the item to the menu of the restaurant
     */
    public void removeMenuItem(Menu item) {
        //STUB
    }

    //getters
    public String getRestaurantName() {
        return "";
    }

    public String getRestaurantLocation() {
        return "";
    }

    public String getCuisineType() {
        return "";
    }

    public List<Menu> getMenuItems() {
        return null;
    }

    public List<Review> getRestaurantReviews() {
        return null;
    }

    public boolean isDeliveryAvailable() {
        return false;
    }

    public boolean isDineInAvailable() {
        return false;
    }

    public int getOpeningHours() {
        return 0;
    }

    public int getCLosingHours() {
        return 0;
    }

    public int getCapacity() {
        return 0;
    }

    //setters
    public void setRestaurantName(String restaurantName) {
        //STUB
    }

    public void setLocation(String restauarantLocation) {
        //STUB
    }

    public void setCuisineType(String cuisineType) {
        //STUB
    }

    public void setRestaurantMenu(List<Menu> menuItems) {
        //STUB
    }

    public void setReviews(List<Review> review) {
        //STUB
    }

}
