package model;

import java.util.ArrayList;

// Represents a Menu containing  a list menuItems
class Menu {
    private ArrayList<MenuItems> menuItems;  //contains all the menu items of a restaurant

    /* 
    * EFFECTS: creates an arraylist of menuItems
    */
    public Menu() {
       //STUB
    }

    /* 
     * MODIFIES: this
     * EFFECTS: adds a the item to the menu
     */
    public void addMenuItem(MenuItems item) {
        //STUB
    }

    /* 
     * MODIFIES: this
     * EFFECTS: removes the item from the menu of the restaurant
     */
    public void removeMenuItem(String name) {
        //STUB
    }

    /* 
     * MODIFIES: this and Menu
     * EFFECTS: updates the item in the menu of the restaurant
     */
    public void updateMenuItem(String name, String description, double price) {
        //STUB
    }

    // Getters
    public ArrayList<MenuItems> getMenuItems() {
        return null;
    }

    //Setters
    public void setMenuItems(ArrayList<MenuItems> menuItems) {
        //STUB
    }
}
