package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import org.json.JSONArray;
import org.json.JSONObject;
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
        Customer customer = new Customer("John", "John@email.com");
        Review review = new Review(customer, "Great place! Love the food", 5);
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
    public void testAddReservation() {
        Customer customer = new Customer("Peggy", "peggy@email.com");
        Reservation reservation = new Reservation(customer, LocalDate.of(2024, 11, 15), LocalTime.of(19, 0), 4);
        restaurant.addReservation(reservation);
        assertEquals(1, restaurant.getReservations().size());
        assertEquals(customer, restaurant.getReservations().get(0).getCustomer());
    }

    @Test
    void testFindMenuItem() {
        restaurant.addMenuItem("Spaghetti", "red sauce spaghetti with meatballs", 12.99, "Main Course");
        MenuItems foundItem1 = restaurant.findMenuItem("Spaghetti");
        MenuItems foundItem2 = restaurant.findMenuItem("Pizza");
        assertNull(foundItem2);
        assertNotNull(foundItem1);
        assertEquals("Spaghetti", foundItem1.getItemName());
        assertEquals(12.99, foundItem1.getItemPrice());
    }

    @Test
    public void testAddOrder() {
        MenuItems menuItem = new MenuItems("Tea", "Boba Tea", 12.50, "Beverages");
        ArrayList<MenuItems> items = new ArrayList<>();
        items.add(menuItem);
        OrderFood order = new OrderFood();
        Customer customer = new Customer("Manny", "Manny@email.com");
        order.setCustomer(customer);
        order.setDeliveryAddress("123 Main mall");
        order.setRestaurantName("TeaDot");
        order.setOrderItems(items);
        restaurant.addOrder(order);
        assertEquals(1, restaurant.getOrders().size());
        assertEquals(customer, restaurant.getOrders().get(0).getCustomer());
        assertEquals(1, restaurant.getOrders().get(0).getOrderItems().size());
        assertEquals(12.50, restaurant.getOrders().get(0).getTotalPrice());
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

    @Test
    void testSetRestaurantMenu() {
        Menu newMenu = new Menu();
        newMenu.addMenuItem(new MenuItems("Burger", "Beef burger with cheese", 10.99, "Main Course"));
        restaurant.setRestaurantMenu(newMenu);
        assertEquals(1, restaurant.getRestaurantMenu().getMenuItems().size());
        assertEquals("Burger", restaurant.getRestaurantMenu().getMenuItems().get(0).getItemName());
    }

    @Test
    void testSetReviews() {
        ArrayList<Review> newReviews = new ArrayList<>();
        Customer customer = new Customer("Alice", "Alice@email.com");
        newReviews.add(new Review(customer, "Amazing experience!", 5));
        restaurant.setReviews(newReviews);
        assertEquals(1, restaurant.getRestaurantReviews().size());
        assertEquals("Amazing experience!", restaurant.getRestaurantReviews().get(0).getReviewComment());
    }

    @Test
    void testSetReservations() {
        ArrayList<Reservation> newReservations = new ArrayList<>();
        Customer customer = new Customer("Bob", "BobB@email.com");
        newReservations.add(new Reservation(customer, LocalDate.of(2024, 11, 01), LocalTime.of(18, 30), 2));
        restaurant.setReservation(newReservations);
        assertEquals(1, restaurant.getReservations().size());
        assertEquals(customer, restaurant.getReservations().get(0).getCustomer());
    }

    @Test
    void testSetOrders() {
        ArrayList<OrderFood> newOrders = new ArrayList<>();
        MenuItems item = new MenuItems("Latte", "Café Latte", 4.50, "Beverages");
        ArrayList<MenuItems> items = new ArrayList<>();
        items.add(item);

        Customer customer = new Customer("Charlie", "Charlie@hotmail.com");
        OrderFood order = new OrderFood();
        order.setCustomer(customer);
        order.setDeliveryAddress("789 Oak Street");
        order.setRestaurantName("Coffee House");
        order.setOrderItems(items);
        newOrders.add(order);

        restaurant.setOrders(newOrders);
        assertEquals(1, restaurant.getOrders().size());
        assertEquals(customer, restaurant.getOrders().get(0).getCustomer());
        assertEquals(1, restaurant.getOrders().get(0).getOrderItems().size());
        assertEquals(4.50, restaurant.getOrders().get(0).getTotalPrice());
    }

    @Test
    void testToJson() {
        restaurant.addMenuItem("Pasta", "Creamy Alfredo Pasta", 12.99, "Main Course");
        Customer customer = new Customer("Caleb", "Caleb@email.com");
        restaurant.addReview(new Review(customer, "Great food and service!", 5));

        JSONObject json = restaurant.toJson();

        assertEquals("Old Spaghetti Factory", json.getString("restaurantName"));
        assertEquals("Kelowna", json.getString("restaurantLocation"));
        assertEquals("Italian", json.getString("cuisineType"));

        JSONArray menuItemsJson = json.getJSONArray("menuItems");
        assertEquals(1, menuItemsJson.length());
        JSONObject menuItemJson = menuItemsJson.getJSONObject(0);
        assertEquals("Pasta", menuItemJson.getString("itemName"));
        assertEquals("Creamy Alfredo Pasta", menuItemJson.getString("description"));
        assertEquals(12.99, menuItemJson.getDouble("price"));
        assertEquals("Main Course", menuItemJson.getString("category"));

        JSONArray reviewsJson = json.getJSONArray("reviews");
        assertEquals(1, reviewsJson.length());
        JSONObject reviewJson = reviewsJson.getJSONObject(0);

        assertEquals("Great food and service!", reviewJson.getString("reviewComment"));
        assertEquals(5, reviewJson.getInt("rating"));
    }

}
