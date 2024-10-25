package persistence;

import model.Customer;
import model.Reservation;
import model.Restaurant;
import model.Review;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
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
    void testWriterSingleRestaurant() {
        try {
            ArrayList<Restaurant> restaurants = new ArrayList<>();
            Restaurant restaurant1 = new Restaurant("Pasta Palace", "123 Noodle St.", "Italian");
            restaurant1.addMenuItem("Spaghetti", "Classic spaghetti with marinara sauce", 12.99, "Main Course");
            Customer customer1 = new Customer("Alice", "Alice@email.com");
            restaurant1.addReview(new Review(customer1, "Great pasta!", 5));
            Customer customer2 = new Customer("Maria", "MariaeVon@email.com");
            restaurant1.addReservation(new Reservation(customer2, LocalDate.of(2024, 11, 23), LocalTime.of(18, 30), 4));
            restaurants.add(restaurant1);

            JsonWriter writer = new JsonWriter("./data/testWriterSingleRestaurant.json");
            writer.open();
            writer.write(restaurants);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSingleRestaurant.json");
            restaurants = reader.read();
            assertEquals(1, restaurants.size());

            Restaurant parsedRestaurant1 = restaurants.get(0);
            assertEquals("Pasta Palace", parsedRestaurant1.getRestaurantName());
            assertEquals("123 Noodle St.", parsedRestaurant1.getRestaurantLocation());
            assertEquals("Italian", parsedRestaurant1.getCuisineType());
            assertEquals(1, parsedRestaurant1.getRestaurantMenu().getMenuItems().size());
            assertEquals(1, parsedRestaurant1.getRestaurantReviews().size());

            ArrayList<Reservation> reservations = parsedRestaurant1.getReservations();
            assertEquals(1, reservations.size());
            Reservation reservation = reservations.get(0);
            assertEquals(LocalDate.of(2024, 11, 23), reservation.getReservationDate());
            assertEquals(LocalTime.of(18, 30), reservation.getReservationTime());
            assertEquals(4, reservation.getNumberOfGuests());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterMultipleRestaurants() {
        try {
            ArrayList<Restaurant> restaurants = new ArrayList<>();
            Restaurant restaurant1 = new Restaurant("Pasta Palace", "123 Noodle St.", "Italian");
            restaurant1.addMenuItem("Spaghetti", "Classic spaghetti with marinara sauce", 12.99, "Main Course");
            Customer customer1 = new Customer("Alice", "Alice@email.com");
            restaurant1.addReview(new Review(customer1, "Great pasta!", 5));
            restaurant1.addReservation(new Reservation(customer1, LocalDate.of(2024, 11, 23), LocalTime.of(18, 30), 0));
            restaurants.add(restaurant1);

            Restaurant restaurant2 = new Restaurant("Sushi World", "456 Fish Ave.", "Japanese");
            restaurant2.addMenuItem("Sushi Roll", "Fresh tuna sushi roll", 8.99, "Appetizer");
            Customer customer2 = new Customer("Bob", "Bobb@email.com");
            restaurant2.addReview(new Review(customer2, "Amazing sushi!", 4));
            restaurants.add(restaurant2);

            JsonWriter writer = new JsonWriter("./data/testWriterMultipleRestaurants.json");
            writer.open();
            writer.write(restaurants);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterMultipleRestaurants.json");
            restaurants = reader.read();
            assertEquals(2, restaurants.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testParsedRestaurantDetails() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterMultipleRestaurants.json");
            ArrayList<Restaurant> restaurants = reader.read();

            Restaurant parsedRestaurant1 = restaurants.get(0);
            assertEquals("Pasta Palace", parsedRestaurant1.getRestaurantName());
            assertEquals("123 Noodle St.", parsedRestaurant1.getRestaurantLocation());
            assertEquals("Italian", parsedRestaurant1.getCuisineType());

            Restaurant parsedRestaurant2 = restaurants.get(1);
            assertEquals("Sushi World", parsedRestaurant2.getRestaurantName());
            assertEquals("456 Fish Ave.", parsedRestaurant2.getRestaurantLocation());
            assertEquals("Japanese", parsedRestaurant2.getCuisineType());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
