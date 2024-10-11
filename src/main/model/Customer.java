package model;

import java.util.ArrayList;

// represents a Customer with name, email and phone number
public class Customer {

    private String name;            //name of the customer

    /*
     * REQUIRES: name has a non-zero length and phonenumber has 10 digits
     * EFFECTS: the name of the customer is set to name;
     *          the email of the customer is set to email;
     *          the phone number is set to phoneNumber.
     */
    public Customer(String name) {
        
    }

     /* 
     * REQUIRES: the restaurant to exist in the list of restaurants;
     *           the numberOfGuests > 0;
     * MODIFIES: Resevation
     * EFFECTS: lets the customer make a reservatiionb at a restaurant
     */
    public void makeReservation(Restaurant restaurant, String customerName, String date, String time, int numberOfGuests) {
        //STUB
    }

    /* 
     * REQUIRES: the restaurant to exist in the list of restaurants;
     *           rating <= 5;
     * MODIFIES: Restaurant
     * EFFECTS: lets the customer leave a review
     */
    public void leaveReview(Restaurant restaurant, int rating, String comment) {
        //STUB
    }

    /* 
     * REQUIRES: the restaurant to exist in the list of restaurants;
     *           rating <= 5;
     * MODIFIES: Order
     * EFFECTS: lets the customer place a order
     */
    public void placeOrder(Restaurant restaurant, ArrayList<MenuItems> items) {
        //STUB
    }

    /* 
     * REQUIRES: the list of restaurants > 0;
     * EFFECTS: lets the customer view a list of restaurants
     */
    public void viewRestaurants() {
        //STUB
    }
    
    /* 
     * REQUIRES: the list of restaurants > 0;
     * EFFECTS: lets the customer view a list of restaurants
     */
    public void viewMenu(Restaurant restaurant) {
        //STUB
    }
    
    
    // Getters
    public String getName() {
        return "";
    }

    public String getEmail() {
        return "";
    }

    public String getPhoneNumber() {
        return "";
    }


    // Setters
    public void setName(String name) {
        //STUB
    }

    public void setEmail(String email) {
        //STUB
    }

    public void setPhoneNumber(String phoneNumber) {
        //STUB
    }
}
