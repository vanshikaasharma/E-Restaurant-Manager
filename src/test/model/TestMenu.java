package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class TestMenu {

    private Menu menu;
    private MenuItems item1;
    private MenuItems item2;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
        item1 = new MenuItems("CheeseBurger", "cheeseburger with tomato and fries", 9.99, "Main Course");
        item2 = new MenuItems("Salad", "Fresh chicken ceaser salad", 5.99, "Appetizer");
    }

    @Test
    public void testAddMenuItem() {
        menu.addMenuItem(item1);
        assertEquals(1, menu.getMenuItems().size());
        assertTrue(menu.getMenuItems().contains(item1));

        menu.addMenuItem(item2);
        assertEquals(2, menu.getMenuItems().size());
        assertTrue(menu.getMenuItems().contains(item2));
    }

    @Test
    public void testRemoveMenuItem() {
        menu.addMenuItem(item1);
        menu.addMenuItem(item2);
        assertEquals(2, menu.getMenuItems().size());

        menu.removeMenuItem("CheeseBurger");
        assertEquals(1, menu.getMenuItems().size());
        assertFalse(menu.getMenuItems().contains(item1));

        menu.removeMenuItem("Salad");
        assertEquals(0, menu.getMenuItems().size());
        assertFalse(menu.getMenuItems().contains(item2));
    }

    @Test
    public void testUpdateMenuItem() {

        menu.addMenuItem(item1);
        menu.updateMenuItem("CheeseBurger", "Double cheeseburger", 11.99, "main course");
        MenuItems updatedItem = menu.getMenuItems().get(0);

        assertEquals("Double cheeseburger", updatedItem.getItemDescription());
        assertEquals(11.99, updatedItem.getItemPrice());

        // Test trying to update a non-existent item
        menu.updateMenuItem("Pizza", "Pepperoni Pizza", 12.9, "appetizer"); // No Pizza in the menu
        assertEquals(1, menu.getMenuItems().size()); // Size should remain 1
        assertEquals("CheeseBurger", menu.getMenuItems().get(0).getItemName()); // Still the same item

        // Test updating when the menu is empty
        Menu emptyMenu = new Menu();
        emptyMenu.updateMenuItem("NonExistentItem", "Description", 5.99, "no category");
        assertTrue(emptyMenu.getMenuItems().isEmpty());

    }

    @Test
    public void testGetMenuItems() {
        assertTrue(menu.getMenuItems().isEmpty());

        menu.addMenuItem(item1);
        menu.addMenuItem(item2);

        ArrayList<MenuItems> menuItems = menu.getMenuItems();
        assertEquals(2, menuItems.size());
        assertEquals(item1, menuItems.get(0));
        assertEquals(item2, menuItems.get(1));
    }

    // Test for setMenuItems
    @Test
    public void testSetMenuItems() {
        ArrayList<MenuItems> newMenu = new ArrayList<>();
        newMenu.add(item1);
        newMenu.add(item2);

        menu.setMenuItems(newMenu);
        assertEquals(2, menu.getMenuItems().size());
        assertTrue(menu.getMenuItems().contains(item1));
        assertTrue(menu.getMenuItems().contains(item2));
    }
}
