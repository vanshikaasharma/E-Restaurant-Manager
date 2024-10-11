package model;

// represents a Customer with name, email and phone number
public class Customer {

    private String name;            //name of the customer
    private String email;           // email of the customer
    private String phoneNumber;     //phone number of the customer

    /*
     * REQUIRES: name has a non-zero length and phonenumber has 10 digits
     * EFFECTS: the name of the customer is set to name;
     *          the email of the customer is set to email;
     *          the phone number is set to phoneNumber.
     */
    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /* 
     * REQUIRES: the restaurant to exist in the list of restaurants;
     *           rating <= 5;
     * MODIFIES: this and Restaurant
     * EFFECTS: lets the customer leave a review
     */
    public Review leaveReview(Restaurant restaurant, int rating, String comment) {
        return null;
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
