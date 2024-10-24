package model;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONObject;

// represents a Customer with name
public class Customer {

    private String name;    // name of the customer
    private String email;   //email of the customer
    
    /*
     * REQUIRES: name has a non-zero length
     * EFFECTS: the name of the customer is set to name;
     * 
     */
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /*
     * REQUIRES: the restaurant to exist in the list of restaurants;
     * the numberOfGuests > 0;
     * MODIFIES: Resevation
     * EFFECTS: lets the customer make a reservatiionb at a restaurant
     */
    public void makeReservation(Restaurant restaurant, Customer customer, LocalDate date, LocalTime time,
            int numberOfGuests) {
        Reservation newReservation = new Reservation(customer, date, time, numberOfGuests);
        restaurant.addReservation(newReservation);
    }

    /*
     * REQUIRES: the restaurant to exist in the list of restaurants;
     * rating <= 5;
     * MODIFIES: Restaurant
     * EFFECTS: lets the customer leave a review
     */
    public void leaveReview(Restaurant restaurant, int rating, String comment) {
        Review newReview = new Review(this, comment, rating);
        restaurant.addReview(newReview);
    }

    /*
     * REQUIRES: the restaurant to exist in the list of restaurants;
     * rating <= 5;
     * MODIFIES: Order
     * EFFECTS: lets the customer place a order
     */
    public void placeOrder(Restaurant restaurant, ArrayList<MenuItems> items) {
        OrderFood order = new OrderFood();
        order.setCustomer(this);
        order.setRestaurantName(restaurant.getRestaurantName());
        order.setOrderItems(items);
        restaurant.addOrder(order);
    }


    // Getters
    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // EFFECTS: returns this customer as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        return json;
    }

    @Override
    public String toString() {
        return "Customer: " + name + " (" + email + ")";
    }
}
