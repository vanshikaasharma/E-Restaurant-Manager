package model;

import java.util.ArrayList;

// Represents a Menu containing a list of menu items
public class Menu {
    private ArrayList<MenuItems> menuItems;  // Contains all the menu items of a restaurant

    /* 
     * EFFECTS: creates an empty arraylist of menuItems
     */
    public Menu() {
        menuItems = new ArrayList<>();
    }

    /* 
     * MODIFIES: this
     * EFFECTS: adds the item to the menu
     */
    public void addMenuItem(MenuItems item) {
        menuItems.add(item);
    }

    /* 
     * MODIFIES: this
     * EFFECTS: removes the item from the menu by name (if it exists)
     */
    public void removeMenuItem(String name) {
        menuItems.removeIf(item -> item.getItemName().equals(name));
    }

    /* 
     * MODIFIES: this
     * EFFECTS: updates the menu item by name if it exists, changing its description and price
     */
    public void updateMenuItem(String name, String description, double price) {
        for (MenuItems item : menuItems) {
            if (item.getItemName().equals(name)) {
                item.setItemDescription(description);
                item.setItemPrice(price);
                break;
            }
        }
    }

    // Getters
    public ArrayList<MenuItems> getMenuItems() {
        return menuItems;
    }

    // Setters
    public void setMenuItems(ArrayList<MenuItems> menuItems) {
        this.menuItems = menuItems;
    }
}
