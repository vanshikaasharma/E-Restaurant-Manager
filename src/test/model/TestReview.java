package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

// Unit tests for the Review class
class TestReview {

    private Review review;
    private Customer c1;

    @BeforeEach
    void setUp() {
        c1 = new Customer("Caleb", "caleb@gmail.com");
        review = new Review(c1, "Great food and service!", 5);
    }

    @Test
    void testConstructor() {
        assertEquals("Caleb", review.getCustomer().getName());
        assertEquals("caleb@gmail.com", review.getCustomer().getEmail());
        assertEquals("Great food and service!", review.getReviewComment());
        assertEquals(5, review.getRating());
    }

    // Test the getter for reviewComment
    @Test
    void testGetReviewComment() {
        assertEquals("Great food and service!", review.getReviewComment());
    }

    // Test the setter for reviewComment
    @Test
    void testSetReviewComment() {
        review.setReviewComment("Average experience.");
        assertEquals("Average experience.", review.getReviewComment());
    }

    // Test the getter for rating
    @Test
    void testGetRating() {
        assertEquals(5, review.getRating());
    }

    // Test the setter for rating
    @Test
    void testSetRating() {
        review.setRating(3);
        assertEquals(3, review.getRating());
    }

    @Test
    void testGetCustomer() {
        assertEquals(c1, review.getCustomer());
    }

    @Test
    void testSetCustomer() {
        Customer c2 = new Customer("Jane Smith", "jane.smith@hotmail.com");
        review.setCustomer(c2);
        assertEquals(c2, review.getCustomer());
    }

    @Test
    void testDeleteReview() {
        review.deleteReview();
        assertEquals("", review.getReviewComment());
        assertEquals(0, review.getRating());
    }

    @Test
    void testToJson() {
        JSONObject json = review.toJson();
        JSONObject customerJson = json.getJSONObject("customer");

        assertEquals("Caleb", customerJson.getString("name"));
        assertEquals("caleb@gmail.com", customerJson.getString("email"));
        assertEquals("Great food and service!", json.getString("reviewComment"));
        assertEquals(5, json.getInt("rating"));
    }

}
