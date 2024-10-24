package persistence;

import model.MenuItems;
import model.Reservation;
import model.Restaurant;
import model.Review;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

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
            testRestaurantDetails(restaurants.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    private void testRestaurantDetails(Restaurant restaurant) {
        assertEquals("Sushi World", restaurant.getRestaurantName());
        assertEquals("456 Ocean Blvd", restaurant.getRestaurantLocation());
        assertEquals("Japanese", restaurant.getCuisineType());

        testMenuItems(restaurant);
        testReviews(restaurant);
        testReservations(restaurant);
    }

    private void testMenuItems(Restaurant restaurant) {
        ArrayList<MenuItems> menuItems = restaurant.viewMenu();
        assertEquals(1, menuItems.size());
        MenuItems menuItem = menuItems.get(0);
        assertEquals("Sushi Platter", menuItem.getItemName());
        assertEquals("Assorted sushi", menuItem.getItemDescription());
        assertEquals(25.99, menuItem.getItemPrice());
        assertEquals("Main", menuItem.getItemCategory());
    }

    private void testReviews(Restaurant restaurant) {
        ArrayList<Review> reviews = restaurant.getRestaurantReviews();
        assertEquals(1, reviews.size());
        Review review = reviews.get(0);
        assertEquals("Mariana", review.getCustomer());
        assertEquals("Amazing sushi!", review.getReviewComment());
        assertEquals(5, review.getRating());
    }

    private void testReservations(Restaurant restaurant) {
        ArrayList<Reservation> reservations = restaurant.getReservations();
        assertEquals(1, reservations.size());
        Reservation reservation = reservations.get(0);
        assertEquals("Maria", reservation.getCustomer());

        LocalDate expectedDate = LocalDate.of(2024, 11, 23);
        LocalTime expectedTime = LocalTime.of(18, 30);
    
        assertEquals(expectedDate, reservation.getReservationDate());
        assertEquals(expectedTime, reservation.getReservationTime());
        assertEquals(4, reservation.getNumberOfGuests());
    }
    

}
