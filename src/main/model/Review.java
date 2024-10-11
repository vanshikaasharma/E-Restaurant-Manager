package model;

// Represents a review from a customer with the review 
//comment, the rating of the restaurant, and the name of the customer
public class Review {
    private String reviewComment;       // the comment of the review
    private int rating;                 // rating of the restaurant out of 5
    private String customerName;        // the name of the customer giving the review

    /*
     * REQUIRES: rating must be between 1 and 5 inclusive, reviewComment has a non-zero length 
     * EFFECTS: the rating of the restaurant is set to rating;
     *          the comment in the review is set to reviewComment;
     *          the customer giving the review is set to customerName.
     */
    public Review(String customerName, String reviewComment, int rating) {
        this.customerName = customerName;
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

    public String getCustomerName() {
        return customerName;
    }

    // Setters
    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public void setRating(int rating) {
            this.rating = rating;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
