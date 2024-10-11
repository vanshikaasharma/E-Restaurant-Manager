package model;

// Represents a review from a customer with the review 
//comment, the rating of the restaurant and the name of the customer
public class Review {
    private String reviewComment;       // the comment of their review
    private int rating;                 // rating of the restaurant out of 5
    private String customerName;        // the name customer giving the review

    /*
     * REQUIRES: reviewDescription has a non-zero length 
     * EFFECTS: the rating of the restaurant is set to rating;
     *          the comment in the review is given reviewComment;
     *          the customer giving the review is given by customerName.
     */
    public Review(String customerName, String reviewComment, int rating) {
        //STUB
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

    public String getCustomerName() {
        return "";
    }

    // Setters
    public void setReviewComment(String reviewText) {
        //STUB
    }

    public void setRating(int rating) {
        //STUB
    }

    public void setCustomerName(String customer) {
        //STUB
    }
}


