package model;

import org.json.JSONObject;

// Represents the Menu items having an item name, item description, item price and
// the category of the item
public class MenuItems {

    private String itemName;            // name of the item
    private String itemDescription;     // description of the item
    private double itemPrice;           // price of the item
    private String itemCategory;        // category of the item (e.g., appetizer, main course, dessert)

    /*
    * REQUIRES: itemPrice > 0 and the item name has a non-zero length 
    * EFFECTS: name of the item is set to itemName; description of 
    *          the item is set to itemDescription; the item price is
    *          given by itemPrice; the category in which the item belongs
    *          is set to itemCategory.
    */
    public MenuItems(String itemName, String itemDescription, double itemPrice, String itemCategory) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
    }

    // Getters
    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    // Setters
    public void setItemName(String name) {
        this.itemName = name;
    }

    public void setItemDescription(String description) {
        this.itemDescription = description;
    }

    public void setItemPrice(double price) {
        this.itemPrice = price;
    }

    public void setItemCategory(String category) {
        this.itemCategory = category;
    }

    // EFFECTS: returns this menu item as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("itemName", itemName);
        json.put("description", itemDescription);
        json.put("price", itemPrice);
        json.put("category", itemCategory);
        return json;
    }
}
