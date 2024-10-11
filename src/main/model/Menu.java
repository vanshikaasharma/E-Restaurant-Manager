package model;

// Represents a Menu having an item name, item description, item price and
// the category of the item
public class Menu {

        private String itemName;            //name of the item
        private String itemDescription;     // description of the item
        private double itemPrice;           // the price of the item
        private String itemCategory;        // the category in which the item belongs
    

        /*
        * REQUIRES: itemPrice > 0 and the item name has a non-zero length 
        * EFFECTS: name of the item is set to itemName; description of 
        *          the item is set to itemDescription; the item price is
        *          given by itemPrice; the category in which the item belongs
        *          is set to itemCategory.
        */
        public Menu(String itemName, String itemDescription, double itemPrice, String itemCategory) {
           //STUB
        }
    
        
        // Getters
        public String getItemName() {
            return "";
        }
    
        public String getItemDescription() {
            return "";
        }
    
        public double getItemPrice() {
            return 0;
        }
    
        public String getItemCategory() {
            return "";
        }
    
        // Setters
        public void setItemName(String name) {
            //STUB
        }
    
        public void setItemDescription(String description) {
            //STUB
        }
    
        public void setItemPrice(double price) {
            //STUB
        }
    
        public void setItemCategory(String category) {
            //STUB
        }
    }
    



