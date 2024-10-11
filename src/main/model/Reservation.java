package model;

//Represents a reservation made having details of the date,
//time, number of guests, and the customer who is making the booking
public class Reservation {
    
    private String reservationDate;            //the date of the reservation
    private String reservationTime;            //the time of the reservation
    private int numberOfGuests;                //the number of guests for the reservation
    private boolean isReserved;                //the table is reserved
    private String customerName;               //the name of the customer making the reservation

    /*
     * REQUIRES: date and time have a non-zero length and numberOfGuests > 0
     * EFFECTS: the date of the reservation is set to date;
     *          the time of the reservation is set to time;
     *          the number of guests for the reservation is set to numberOfGuests;
     *          the name of the customer is set to customerName.
     */
    public Reservation(String customerName, String date, String time, int numberOfGuests) {
        this.customerName = customerName;
        this.reservationDate = date;
        this.reservationTime = time;
        this.numberOfGuests = numberOfGuests;
        this.isReserved = true;  // Set to true when a reservation is created
    }

    /* 
     * REQUIRES: the reservation to exist
     * MODIFIES: this
     * EFFECTS: lets the customer modify reservation 
     */
    public void modifyReservation(String date, String time, int newNumberOfGuests) {
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

    // Getters
    public String getReservationDate() {
        return reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean getIsReserved() {
        return isReserved;
    }

    // Setters
    public void setReservationDate(String date) {
        this.reservationDate = date;
    }

    public void setReservationTime(String time) {
        this.reservationTime = time;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }
}
