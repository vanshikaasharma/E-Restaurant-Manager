package model;

import java.util.ArrayList;

// Represents a restaurant having an name, location, cuisine type, menu, 
//capacity of the restaurant and review of the restaurant 
public class Restaurant {

    private String restaurantName;                  //name of the restaurant
    private String restaurantLocation;              //location of the restaurant
    private String cuisineType;                     //the cuisine that is being served in the restaurant
    private Menu restaurantMenu;               //menu of the restaurant
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
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        this.cuisineType = cuisineType;
        this.capacity = 10; 
        this.restaurantMenu = new Menu(); 
        this.restaurantReviews = new ArrayList<>();
    }


    /* 
     * MODIFIES: this and Review
     * EFFECTS: adds a Review for the restaurant
     */
    public void addReview(Review review) {
        restaurantReviews.add(review);
    }

    /* 
     * MODIFIES: this and Menu
     * EFFECTS: adds a the item to the menu of the restaurant
     */
    public void addMenuItem(String itemName, String itemDescription, double itemPrice, String itemCategory) {
        MenuItems newItem = new MenuItems(itemName, itemDescription, itemPrice, itemCategory);
        restaurantMenu.addMenuItem(newItem);
    }

    /* 
     * MODIFIES: this and Menu
     * EFFECTS: removes the item from the menu of the restaurant
     */
    public void removeMenuItem(String itemName) {
        restaurantMenu.removeMenuItem(itemName);
    }

    /* 
     * MODIFIES: this and Menu
     * EFFECTS: updates the item in the menu of the restaurant
     */
    public void updateMenuItem(String name, String description, double price) {
        restaurantMenu.updateMenuItem(name, description, price);
    }

    /* 
     * EFFECTS: returns the items in the menu of the restaurant
     */
    public ArrayList<Menu> viewMenu() {
        ArrayList<Menu> menuList = new ArrayList<>();
        menuList.add(restaurantMenu);
        return menuList;
    }

    //getters
    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public Menu getRestaurantMenu() {
        return restaurantMenu;
    }

    public ArrayList<Review> getRestaurantReviews() {
        return restaurantReviews;
    }

    public int getCapacity() {
        return capacity;
    }


    //setters
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;

    }

    public void setLocation(String restauarantLocation) {
        this.restaurantLocation = restaurantLocation;
    }
    

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }
    

    public void setRestaurantMenu(Menu menu) {
        this.restaurantMenu = menu;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.restaurantReviews = reviews;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
