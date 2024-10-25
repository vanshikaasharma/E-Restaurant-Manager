package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a restaurant having an name, location, cuisine type, menu, 
//capacity of the restaurant and review of the restaurant 
public class Restaurant {

    private String restaurantName; // name of the restaurant
    private String restaurantLocation; // location of the restaurant
    private String cuisineType; // the cuisine that is being served in the restaurant
    private Menu restaurantMenu; // menu of the restaurant
    private int capacity; // capacity of the restaurant
    private ArrayList<Review> restaurantReviews; // reviews of the restaurant
    private ArrayList<Reservation> reservations; // reservations made in the restaurant
    private ArrayList<OrderFood> orders; // orders made in the restaurant

    /*
     * REQUIRES: restaurant, cuisine, has a non-zero length
     * EFFECTS: name of the restaurant is set to restaurantName; location of
     * the restaurant is set to restaurantLocation; the cuisine of
     * the restaurant is cuisineType; the capacity of the restaurant
     * is set to 10; an ArrayList of
     * restaurantMenu is instantiated; an ArrayList of
     * restaurantReview is instantiated;
     */
    public Restaurant(String restaurantName, String restaurantLocation, String cuisineType) {
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        this.cuisineType = cuisineType;
        this.capacity = 10;
        this.restaurantMenu = new Menu();
        this.restaurantReviews = new ArrayList<>();

        this.reservations = new ArrayList<>();
        this.orders = new ArrayList<>();
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
     * MODIFIES: this and Reservation
     * EFFECTS: adds a reservation for the restaurant
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds a new order to the list of orders
     */
    public void addOrder(OrderFood order) {
        orders.add(order);
    }

    /*
     * EFFECTS: searches the restaurant's menu for an item with the given name
     * and returns the MenuItem if found, or null if not found.
     */
    public MenuItems findMenuItem(String itemName) {
        for (MenuItems item : restaurantMenu.getMenuItems()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    /*
     * EFFECTS: returns the items in the menu of the restaurant
     */
    public ArrayList<MenuItems> viewMenu() {
        return restaurantMenu.getMenuItems(); // return the actual menu items, not the entire menu
    }

    // Getters
    public String getRestaurantName() {
        return this.restaurantName;
    }

    public String getRestaurantLocation() {
        return this.restaurantLocation;
    }

    public String getCuisineType() {
        return this.cuisineType;
    }

    public Menu getRestaurantMenu() {
        return this.restaurantMenu;
    }

    public ArrayList<Review> getRestaurantReviews() {
        return this.restaurantReviews;
    }

    public ArrayList<OrderFood> getOrders() {
        return this.orders;
    }

    public ArrayList<Reservation> getReservations() {
        return this.reservations;
    }

    public int getCapacity() {
        return this.capacity;
    }

    // Setters
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setLocation(String restaurantLocation) {
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

    public void setReservation(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setOrders(ArrayList<OrderFood> orders) {
        this.orders = orders;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // EFFECTS: returns this as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("restaurantName", restaurantName);
        json.put("restaurantLocation", restaurantLocation);
        json.put("cuisineType", cuisineType);
        json.put("menuItems", menuItemsToJson());
        json.put("reviews", reviewsToJson());
        json.put("reservations", reservationsToJson());
        return json;
    }

    // EFFECTS: returns menu items as a JSON array
    protected JSONArray menuItemsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (MenuItems item : restaurantMenu.getMenuItems()) {
            jsonArray.put(item.toJson()); 
        }
        return jsonArray;
    }

    // EFFECTS: returns reviews as a JSON array
    protected JSONArray reviewsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Review review : restaurantReviews) {
            jsonArray.put(review.toJson()); 
        }
        return jsonArray;
    }

    // EFFECTS: returns reservations as a JSON array
    protected JSONArray reservationsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Reservation reservation : reservations) {
            jsonArray.put(reservation.toJson());
        }
        return jsonArray;
    }
}
