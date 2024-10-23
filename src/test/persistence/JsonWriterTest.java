package persistence;

import model.MenuItems;
import model.Restaurant;
import model.Review;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ArrayList<Restaurant> restaurants = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRestaurants() {
        try {
            ArrayList<Restaurant> restaurants = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRestaurants.json");
            writer.open();
            writer.write(restaurants);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRestaurants.json");
            restaurants = reader.read();
            assertEquals(0, restaurants.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRestaurants() {
        try {
            ArrayList<Restaurant> restaurants = new ArrayList<>();
            Restaurant restaurant1 = new Restaurant("Pasta Palace", "123 Noodle St.", "Italian");
            restaurant1.addMenuItem("Spaghetti", "Classic spaghetti with marinara sauce", 12.99, "Main Course");
            restaurant1.addReview(new Review("Alice", "Great pasta!", 5));
            restaurants.add(restaurant1);

            Restaurant restaurant2 = new Restaurant("Sushi World", "456 Fish Ave.", "Japanese");
            restaurant2.addMenuItem("Sushi Roll", "Fresh tuna sushi roll", 8.99, "Appetizer");
            restaurant2.addReview(new Review("Bob", "Amazing sushi!", 4));
            restaurants.add(restaurant2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRestaurants.json");
            writer.open();
            writer.write(restaurants);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRestaurants.json");
            restaurants = reader.read();
            assertEquals(2, restaurants.size());

            Restaurant parsedRestaurant1 = restaurants.get(0);
            assertEquals("Pasta Palace", parsedRestaurant1.getRestaurantName());
            assertEquals("123 Noodle St.", parsedRestaurant1.getRestaurantLocation());
            assertEquals("Italian", parsedRestaurant1.getCuisineType());
            assertEquals(1, parsedRestaurant1.getRestaurantMenu().getMenuItems().size());
            assertEquals(1, parsedRestaurant1.getRestaurantReviews().size());
    
            Restaurant parsedRestaurant2 = restaurants.get(1);
            assertEquals("Sushi World", parsedRestaurant2.getRestaurantName());
            assertEquals("456 Fish Ave.", parsedRestaurant2.getRestaurantLocation());
            assertEquals("Japanese", parsedRestaurant2.getCuisineType());
            assertEquals(1, parsedRestaurant2.getRestaurantMenu().getMenuItems().size());
            assertEquals(1, parsedRestaurant2.getRestaurantReviews().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
