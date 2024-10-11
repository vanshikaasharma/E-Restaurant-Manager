package model;

import java.util.ArrayList;

// Represents a restaurant having an name, location, cuisine type, menu, 
//capacity of the restaurant and review of the restaurant 
public class Restaurant {

    private String restaurantName;                  //name of the restaurant
    private String restaurantLocation;              //location of the restaurant
    private String cuisineType;                     //the cuisine that is being served in the restaurant
    private MenuItems restaurantMenu;               //menu of the restaurant
    private int capacity;                           //capacity of the restaurant
    private ArrayList<Review> restaurantReviews;     //reviews of the restaurant
   

    /*
     * REQUIRES: restaurant, cuisine, has a non-zero length 
     * EFFECTS: name of the restaurant is set to restaurantName; location of 
     *          the restaurant is set to restaurantLocation; the cuisine of 
     *          the restaurant is cuisineType; the capacity of the restaurant
     *          is set to 10; an ArrayList of 
     *          restaurantMenu is instantiated; an ArrayList of 
     *          restaurantReview is instantiated;
     */
    public Restaurant(String restaurantName, String restaurantLocation, String cuisineType) {
  
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
    public void addMenuItem(String itemName, String itemDescription, double itemPrice, String itemCategory) {
        //STUB
    }

    /* 
     * MODIFIES: this and Menu
     * EFFECTS: removes the item from the menu of the restaurant
     */
    public void removeMenuItem(String itemName) {
        //STUB
    }

    /* 
     * MODIFIES: this and Menu
     * EFFECTS: updates the item in the menu of the restaurant
     */
    public void updateMenuItem(String name, String description, double price) {
        //STUB
    }

    /* 
     * EFFECTS: returns the items in the menu of the restaurant
     */
    public ArrayList<MenuItems> viewMenu() {
        return null;
    }

    /* 
     * EFFECTS: returns the reviews the restaurant has recieved
     */
    public ArrayList<Review> viewReviews() {
        return null;
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

    public MenuItems getRestaurantMenu() {
        return null;
    }

    public ArrayList<Review> getRestaurantReviews() {
        return null;
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

    public void setRestaurantMenu(MenuItems menu) {
        //STUB
    }

    public void setReviews(ArrayList<Review> reviews) {
        //STUB
    }

    public void setCapacity(int capacity) {
        //STUB
    }

}
