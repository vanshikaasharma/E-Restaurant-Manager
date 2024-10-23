package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

// Unit tests for the Review class
class TestReview {

    private Review review;

    @BeforeEach
    void setUp() {
        // Set up an initial review object before each test
        review = new Review("Caleb", "Great food and service!", 5);
    }

    // Test the constructor and initialization of the Review object
    @Test
    void testConstructor() {
        assertEquals("Caleb", review.getCustomerName());
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
    void testGetCustomerName() {
        assertEquals("Caleb", review.getCustomerName());
    }

    @Test
    void testSetCustomerName() {
        review.setCustomerName("Jane Smith");
        assertEquals("Jane Smith", review.getCustomerName());
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

        assertEquals("Caleb", json.getString("customerName"));
        assertEquals("Great food and service!", json.getString("reviewComment"));
        assertEquals(5, json.getInt("rating"));
    }

    

}

