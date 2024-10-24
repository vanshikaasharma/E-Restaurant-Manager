package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomer {

    private Customer customer;
    private Restaurant r1; 
    private MenuItems menuItem; 

    @BeforeEach
    public void setUp() {
        customer = new Customer("Katie");
        r1 = new Restaurant("Cactus club", "765 Broadway", "Fusion");
        menuItem = new MenuItems("Pizza", "cheese pizza", 9.99, "Main Course");
    }
    

    @Test
    public void testMakeReservation() {
        customer.makeReservation(r1, "Katie", LocalDate.of(2024, 11, 15), LocalTime.of(19, 0), 4);
        ArrayList<Reservation> reservations = r1.getReservations();
        Reservation reservation = reservations.get(0);
        assertEquals("Katie", reservation.getCustomerName());
        assertEquals(LocalDate.of(2024, 11, 15), reservation.getReservationDate());
        assertEquals(LocalTime.of(19, 0), reservation.getReservationTime());
        assertEquals(4, reservation.getNumberOfGuests());
    }

    @Test
    public void testLeaveReview() {
        customer.leaveReview(r1, 5, "Amazing food and great service!");
        ArrayList<Review> reviews = r1.getRestaurantReviews();
        Review review = reviews.get(0);
        assertEquals("Katie", review.getCustomerName());
        assertEquals(5, review.getRating());
        assertEquals("Amazing food and great service!", review.getReviewComment());
    }

    @Test
    public void testPlaceOrder() {
        ArrayList<MenuItems> items = new ArrayList<>();
        items.add(menuItem);
        customer.placeOrder(r1, items);
        assertEquals("Cactus club",r1.getRestaurantName());
        assertEquals("765 Broadway",r1.getRestaurantLocation());
        assertEquals("Fusion",r1.getCuisineType());
        assertEquals("Pizza",menuItem.getItemName());
        assertEquals("cheese pizza",menuItem.getItemDescription());
        assertEquals(9.99,menuItem.getItemPrice());
        assertEquals("Main Course",menuItem.getItemCategory());

    }



    @Test
    public void testGetters() {
        assertEquals("Katie", customer.getName());
    }

    @Test
    public void testSetters() {
        customer.setName("Jude");
        assertEquals("Jude", customer.getName());
    }
}
