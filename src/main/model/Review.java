package model;

// Represents a review from a customer with the review 
//desciption and the rating of the restaurant
public class Review {
    private String reviewComment;       // the comment of their review
    private int rating;                 // rating of the restaurant out of 5
    private Customer customer;          // the customer giving the review

    /*
     * REQUIRES: reviewDescription has a non-zero length 
     * EFFECTS: the rating of the restaurant is set to rating;
     *          the comment in the review is given reviewComment;
     *          the customer giving the review is given by customer.
     */
    public Review(String reviewComment, int rating, Customer customer) {
        this.reviewComment = reviewComment;
        this.rating = rating;
        this.customer = customer;
    }

    /* 
     * MODIFIES: this
     * EFFECTS: deletes the review given by the customer
     */
    public void deleteReview(){
        //STUB
    }


    // Getters
    public String getReviewComment() {
        return "";
    }

    public int getRating() {
        return 0;
    }

    public Customer getCustomer() {
        return null;
    }

    // Setters
    public void setReviewComment(String reviewText) {
        //STUB
    }

    public void setRating(int rating) {
        //STUB
    }

    public void setCustomer(Customer customer) {
        //STUB
    }
}


