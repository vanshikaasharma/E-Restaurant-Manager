package model;

//Represents a reservation made having details of the date,
//time, number of guests, and the customer who is making the booking
public class Reservation {
    
    private String reservationDate;            //the date of the reservation
    private String reservationTime;            //the time of the reservation
    private int numberOfGuests;                //the number of guests for the reservation
    private boolean isReserved;                //the table is reserved
    private String customerName;               //the name of the customer making the resevation
    /*
     * REQUIRES: date and time has a non-zero length and numberOfGuests > 0
     * EFFECTS: the date of the reservation is set to date;
     *          the time of the reservation is set to time;
     *          the number of guests for the reservation is set to numberOfGuests;
     *          the name of the customer is set to customerName.
     */
    public Reservation( String customerName, String date, String time, int numberOfGuests) {
        //STUB
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


    // Getters
    public String getReservationDate() {
        return "";
    }

    public String getReservationTime() {
        return "";
    }

    public int getNumberOfGuests() {
        return 0;
    }

    public String getCustomerName() {
        return "";
    }

    public boolean getIsReserved() {
        return false;
    }

    // Setters
    public void setReservationDate(String date) {
        //STUB
    }

    public void setReservationTime(String time) {
        //STUB
    }

    public void setNumberOfGuests(int numberOfGuests) {
        //STUB
    }

    public void setCustomerName(String customerName) {
        //STUB
    }

    public void setIsReserved() {
        //STUB
    }

}
