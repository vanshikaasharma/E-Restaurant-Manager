package persistence;

import model.MenuItems;
import model.Restaurant;
import model.Review;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testReaderEmptyRestaurantList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRestaurantList.json");
        try {
            ArrayList<Restaurant> restaurants = reader.read();
            assertEquals(0, restaurants.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralRestaurantList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRestaurantList.json");
        try {
            ArrayList<Restaurant> restaurants = reader.read();
            assertEquals(1, restaurants.size());

            // Test Restaurant 
            Restaurant restaurant = restaurants.get(0);
            assertEquals("Sushi World", restaurant.getRestaurantName());
            assertEquals("456 Ocean Blvd", restaurant.getRestaurantLocation());
            assertEquals("Japanese", restaurant.getCuisineType());

            // Test Menu Items
            ArrayList<MenuItems> menuItems = restaurant.viewMenu();
            assertEquals(1, menuItems.size());
            MenuItems menuItem = menuItems.get(0);
            assertEquals("Sushi Platter", menuItem.getItemName());
            assertEquals("Assorted sushi", menuItem.getItemDescription());
            assertEquals(25.99, menuItem.getItemPrice());
            assertEquals("Main", menuItem.getItemCategory());

            // Test Reviews
            ArrayList<Review> reviews = restaurant.getRestaurantReviews();
            assertEquals(1, reviews.size());
            Review review = reviews.get(0);
            assertEquals("Mariana", review.getCustomerName());
            assertEquals("Amazing sushi!", review.getReviewComment());
            assertEquals(5, review.getRating());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
