package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRestaurant {

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant("Old Spaghetti Factory", "Kelowna", "Italian");
    }

    @Test
    void testConstructor() {
        assertEquals("Old Spaghetti Factory", restaurant.getRestaurantName());
        assertEquals("Kelowna", restaurant.getRestaurantLocation());
        assertEquals("Italian", restaurant.getCuisineType());
        assertNotNull(restaurant.getRestaurantMenu());
        assertEquals(10, restaurant.getCapacity());
        assertNotNull(restaurant.getRestaurantReviews());
    }

    @Test
    void testAddMenuItem() {
        restaurant.addMenuItem("Spaghetti", "red sauce spaghetti with meatballs", 12.99, "Main Course");
        ArrayList<MenuItems> menuItems = restaurant.viewMenu();
        assertEquals(1, menuItems.size());
        assertEquals("Spaghetti", menuItems.get(0).getItemName());
    }

    @Test
    void testMultipleAddMenuItem() {
        restaurant.addMenuItem("Spaghetti", "red sauce spaghetti with meatballs", 12.99, "Main Course");
        restaurant.addMenuItem("Cheese pizza", "Pan pizza with Mozzarella and pizza sauce", 14.99, "Main Course");
        ArrayList<MenuItems> menuItems = restaurant.viewMenu();
        assertEquals(2, menuItems.size());
        assertEquals("Spaghetti", menuItems.get(0).getItemName());
        assertEquals("Cheese pizza", menuItems.get(1).getItemName());
    }

    @Test
    void testAddReview() {
        Review review = new Review("John", "Great place! Love the food", 5);
        restaurant.addReview(review);
        ArrayList<Review> reviews = restaurant.getRestaurantReviews();
        assertEquals(1, reviews.size());
        assertEquals("Great place! Love the food", reviews.get(0).getReviewComment());
        assertEquals(5, reviews.get(0).getRating());
    }

    @Test
    void testRemoveMenuItem() {
        restaurant.addMenuItem("Pizza", "Cheesy pizza", 13.99, "Main Course");
        restaurant.removeMenuItem("Pizza");
        ArrayList<MenuItems> menuItems = restaurant.viewMenu();
        assertTrue(menuItems.isEmpty());
    }

    @Test
    void testUpdateMenuItem() {
        restaurant.addMenuItem("Salad", "Fresh Southwest salad", 5.99, "Appetizer");
        restaurant.updateMenuItem("Salad", "Fresh Caesar salad", 6.99);
        ArrayList<MenuItems> menuItems = restaurant.viewMenu();
        assertEquals("Fresh Caesar salad", menuItems.get(0).getItemDescription());
        assertEquals(6.99, menuItems.get(0).getItemPrice());
    }

    @Test
    void testViewMenu() {
        restaurant.addMenuItem("Soup", "Warm vegetable soup", 4.99, "Appetizer");
        ArrayList<MenuItems> menuItems = restaurant.viewMenu();
        assertEquals(1, menuItems.size());
        assertEquals("Soup", menuItems.get(0).getItemName());
    }


    @Test
    void testSettersAndGetters() {
        restaurant.setRestaurantName("Sushi Bar");
        assertEquals("Sushi Bar", restaurant.getRestaurantName());

        restaurant.setLocation("Vancouver");
        assertEquals("Vancouver", restaurant.getRestaurantLocation());

        restaurant.setCuisineType("Japanese");
        assertEquals("Japanese", restaurant.getCuisineType());

        restaurant.setCapacity(50);
        assertEquals(50, restaurant.getCapacity());
    }
}
