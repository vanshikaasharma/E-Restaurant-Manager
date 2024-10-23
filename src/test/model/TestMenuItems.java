package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

// Test class for MenuItems
public class TestMenuItems {

    @Test
    public void testConstructor() {
        // Test creating a new MenuItems object
        MenuItems item = new MenuItems("Pizza", "Delicious cheese pizza", 12.99, "Main Course");

        assertEquals("Pizza", item.getItemName());
        assertEquals("Delicious cheese pizza", item.getItemDescription());
        assertEquals(12.99, item.getItemPrice());
        assertEquals("Main Course", item.getItemCategory());
    }

    @Test
    public void testGetItemName() {
        // Test getting the name of the item
        MenuItems item = new MenuItems("Burger", "Beef burger", 8.99, "Main Course");
        assertEquals("Burger", item.getItemName());
    }

    @Test
    public void testGetItemDescription() {
        // Test getting the description of the item
        MenuItems item = new MenuItems("Pasta", "Creamy Alfredo pasta", 10.99, "Main Course");
        assertEquals("Creamy Alfredo pasta", item.getItemDescription());
    }

    @Test
    public void testGetItemPrice() {
        // Test getting the price of the item
        MenuItems item = new MenuItems("Sushi", "Fresh salmon sushi", 14.99, "Appetizer");
        assertEquals(14.99, item.getItemPrice());
    }

    @Test
    public void testGetItemCategory() {
        // Test getting the category of the item
        MenuItems item = new MenuItems("Cake", "Chocolate cake", 5.99, "Dessert");
        assertEquals("Dessert", item.getItemCategory());
    }

    @Test
    public void testSetItemName() {
        // Test setting the name of the item
        MenuItems item = new MenuItems("Pasta", "Delicious pasta", 11.99, "Main Course");
        item.setItemName("Lasagna");
        assertEquals("Lasagna", item.getItemName());
    }

    @Test
    public void testSetItemDescription() {
        // Test setting the description of the item
        MenuItems item = new MenuItems("Sandwich", "Cheese sandwich", 6.99, "Main Course");
        item.setItemDescription("Grilled cheese sandwich");
        assertEquals("Grilled cheese sandwich", item.getItemDescription());
    }

    @Test
    public void testSetItemPrice() {
        // Test setting the price of the item
        MenuItems item = new MenuItems("Salad", "Caesar salad", 7.99, "Appetizer");
        item.setItemPrice(8.99);
        assertEquals(8.99, item.getItemPrice());
    }

    @Test
    public void testSetItemCategory() {
        // Test setting the category of the item
        MenuItems item = new MenuItems("Ice Cream", "Vanilla ice cream", 4.99, "Dessert");
        item.setItemCategory("Snack");
        assertEquals("Snack", item.getItemCategory());
    }

    @Test
    void testToJson() {
        MenuItems menuItem = new MenuItems("Pasta", "Creamy Alfredo Pasta", 12.99, "Main Course");
        JSONObject json = menuItem.toJson();

        assertEquals("Pasta", json.getString("itemName"));
        assertEquals("Creamy Alfredo Pasta", json.getString("description"));
        assertEquals(12.99, json.getDouble("price"));
        assertEquals("Main Course", json.getString("category"));
    }
}
