package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrderFood  {

    private OrderFood order;
    private MenuItems item1;
    private MenuItems item2;

    @BeforeEach
    public void setUp() {
        order = new OrderFood();
        item1 = new MenuItems("Pizza", "Cheese Pizza", 10.99, "Main Course");
        item2 = new MenuItems("Soda", "Coke", 1.99, "Beverage");
    }

    @Test
    public void testAddItem() {
        order.addItem(item1);
        assertTrue(order.getOrderItems().contains(item1));
    }

    @Test
    public void testRemoveItem() {
        order.addItem(item1);
        order.removeItem(item1);
        assertFalse(order.getOrderItems().contains(item1));
    }

    @Test
    public void testGetOrderItems() {
        order.addItem(item1);
        order.addItem(item2);
        ArrayList<MenuItems> items = order.getOrderItems();
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    @Test
    public void testGetTotalPrice() {
        order.addItem(item1);
        order.addItem(item2);
        double expectedPrice = item1.getItemPrice() + item2.getItemPrice();
        assertEquals(expectedPrice, order.getTotalPrice());
    }

    @Test
    public void testSetRestaurantName() {
        String restaurantName = "Hello Italy";
        order.setRestaurantName(restaurantName);
        assertEquals(restaurantName, order.getRestaurantName());
    }

    @Test
    public void testSetCustomerName() {
        String customerName = "Kevin";
        order.setCustomerName(customerName);
        assertEquals(customerName, order.getCustomerName());
    }

    @Test
    public void testSetDeliveryAddress() {
        String deliveryAddress = "123 Main Mall";
        order.setDeliveryAddress(deliveryAddress);
        assertEquals(deliveryAddress, order.getDeliveryAddress());
    }
}
