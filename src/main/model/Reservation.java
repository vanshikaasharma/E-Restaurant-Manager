package model;

//Represents a reservation made having details of the date,
//time, number of guests, and the restaurant in which the booking is to be made
public class Reservation {
    
    private String date;            //the date of the reservation
    private String time;            //the time of the reservation
    private int numberOfGuests;     //the number of guests for the reservation
    private Restaurant restaurant;  //the restaurant in which reservation is being made
    private boolean isReserved;     //the table is reserved

    /*
     * REQUIRES: date and time has a non-zero length and numberOfGuests > 0
     * EFFECTS: the date of the reservation is set to date;
     *          the time of the reservation is set to time;
     *          the number of guests for the reservation is set to numberOfGuests
     *          the restaurant in which the resevation is being made is set to restaurant.
     */
    public Reservation( String date, String time, int numberOfGuests, Restaurant restaurant) {
        this.date = date;
        this.time = time;
        this.numberOfGuests = numberOfGuests;
        this.restaurant = restaurant;
    }

    /* 
     * REQUIRES: the reservation to exist
     * MODIFIES: this
     * EFFECTS: lets the customer modify reservation 
     */
    public void modifyReservation(String date, String time, int newNumberOfGuests) {
        //STUB
    }

    /* 
     * REQUIRES: the reservation to exist
     * MODIFIES: this
     * EFFECTS: lets the customer cancel the resevation
     */
    public void cancelReservation() {
        //STUB
    }

    /* 
     * REQUIRES: the reservation to exist
     * MODIFIES: this
     * EFFECTS: lets the customer view the reservation they made
     */
    public void viewReservationDetails() {
        //STUB
    }
    
    
    

    // Getters
    public String getDate() {
        return "";
    }

    public String getTime() {
        return "";
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public boolean getIsReserved() {
        return false;
    }

    // Setters
    public void setDate(String date) {
        //STUB
    }

    public void setTime(String time) {
        //STUB
    }

    public void setNumberOfGuests(int numberOfGuests) {
        //STUB
    }

    public void setRestaurant(Restaurant restaurant) {
        //STUB
    }

    public void setIsReserved() {
        //STUB
    }

}
