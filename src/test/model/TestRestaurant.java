package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRestaurant {

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant("Old Spagetti Factory", "Kelowna", "Italian");
    }

    @Test
    void testConstructor() {
        assertEquals("Old Spagetti Factory", restaurant.getRestaurantName());
        assertEquals("Kelowna", restaurant.getRestaurantLocation());
        assertEquals("Italian", restaurant.getCuisineType());
        assertNotNull(restaurant.getRestaurantMenu());
        assertEquals(10, restaurant.getCapacity());
        assertTrue(restaurant.isDineInAvailable());
        assertFalse(restaurant.isDeliveryAvailable());
        assertEquals(0, restaurant.getOpeningHours());
        assertEquals(0, restaurant.getCLosingHours());
        assertNotNull(restaurant.getRestaurantReviews());
    }

}
