package model;

import org.json.JSONObject;

// Represents a review from a customer with the review 
//comment, the rating of the restaurant, and the name of the customer
public class Review {
    private String reviewComment;         // the comment of the review
    private int rating;                   // rating of the restaurant out of 5
    private Customer customer;        // the customer giving the review

    /*
     * REQUIRES: rating must be between 1 and 5 inclusive, reviewComment has a non-zero length 
     * EFFECTS: the rating of the restaurant is set to rating;
     *          the comment in the review is set to reviewComment;
     *          the customer giving the review is set to customer.
     */
    public Review(Customer customer, String reviewComment, int rating) {
        this.customer = customer;
        this.reviewComment = reviewComment;
        this.rating = rating;

    }

    /* 
     * MODIFIES: this
     * EFFECTS: deletes the review by clearing the comment and setting rating to 0
     */
    public void deleteReview() {
        this.reviewComment = "";
        this.rating = 0;
    }

    // Getters
    public String getReviewComment() {
        return reviewComment;
    }

    public int getRating() {
        return rating;
    }

    public Customer getCustomer() {
        return customer;
    }

    // Setters
    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // EFFECTS: returns this review as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("customer", customer.toJson());
        json.put("reviewComment", reviewComment);
        json.put("rating", rating);
        return json;
    }
}
