package model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.json.JSONObject;

//Represents a reservation made having details of the date,
//time, number of guests, and the customer who is making the booking
public class Reservation {

    private LocalDate reservationDate; // the date of the reservation
    private LocalTime reservationTime; // the time of the reservation
    private int numberOfGuests; // the number of guests for the reservation
    private boolean isReserved; // the table is reserved
    private Customer customer; // the name of the customer making the reservation

    /*
     * REQUIRES: date and time have a non-zero length and numberOfGuests > 0
     * EFFECTS: the date of the reservation is set to date;
     * the time of the reservation is set to time;
     * the number of guests for the reservation is set to numberOfGuests;
     * the customer is set to customer.
     */
    public Reservation(Customer customer, LocalDate date, LocalTime time, int numberOfGuests) {
        this.customer = customer;
        this.reservationDate = date;
        this.reservationTime = time;
        this.numberOfGuests = numberOfGuests;
        this.isReserved = true; 
    }

    /*
     * REQUIRES: the reservation to exist
     * MODIFIES: this
     * EFFECTS: lets the customer modify reservation
     */
    public void modifyReservation(LocalDate date, LocalTime time, int newNumberOfGuests) {
        this.reservationDate = date;
        this.reservationTime = time;
        this.numberOfGuests = newNumberOfGuests;
    }

    /*
     * REQUIRES: the reservation to exist
     * MODIFIES: this
     * EFFECTS: lets the customer cancel the reservation
     */
    public void cancelReservation() {
        this.isReserved = false;
    }

     // EFFECTS: returns reservation as a JSON object
     public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("customer", customer);
        json.put("reservationDate", reservationDate.toString()); 
        json.put("reservationTime", reservationTime.toString()); 
        json.put("numberOfGuests", numberOfGuests);
        return json;
    }

    // Getters
    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean getIsReserved() {
        return isReserved;
    }

    // Setters
    public void setReservationDate(LocalDate date) {
        this.reservationDate = date;
    }

    public void setReservationTime(LocalTime time) {
        this.reservationTime = time;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }
}
