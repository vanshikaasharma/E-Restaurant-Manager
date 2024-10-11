package model;
import java.util.ArrayList;

// Reprsents a order, with the name of the customer, item that has been ordered,
// the total cost, the restaurant from which the foos is ordered,
// and the delivery address; 

public class OrderFood {

    private String customerName;               //name of the customer ordering food
    private String restaurantName;             //name of the restaurant
    private ArrayList<MenuItems> orderItems;      //items available to order from Menu
    private double totalPrice;          //total price of food that is ordered
    private String deliveryAddress;     // the address at which the food is delivered

    /*
     * REQUIRES: the restaurant exists, atleast 1 item from menu is
     *           selected and totalPrice > 0
     * EFFECTS: the delivery Address of the order is set to deliveryAddress;
     *          the totalPrice of the order is initially set to 0;
     *          the restaurant in which the resevation is being made is set to restaurant.
     */
    public OrderFood() {
       //STUB 
    }

    /* 
     * REQUIRES: the item to exist in the restaurant
     * MODIFIES: this
     * EFFECTS: lets the customer add item to the order 
     */
    public void addItem(MenuItems item) {
        //STUB
    }

     /* 
     * REQUIRES: the item to exist in the restaurant and in the order
     * MODIFIES: this
     * EFFECTS: lets the customer remove item to the order 
     */
    public void removeItem(MenuItems item) {
        //STUB
    }

    
    

    // Getters
    public ArrayList<MenuItems> getOrderItems() {
        return null;
    }

    public double getTotalPrice() {
        return 0;
    }

    public String getRestaurantName() {
        return "";
    }

    public String getCustomerName() {
        return "";
    }

    public String getDeliveryAddress() {
        return "";
    }

    // Setters
    public void setOrderItems(ArrayList<MenuItems> items) {
        //STUB
    }

    public void setRestaurantName(String restaurantName) {
       //STUB
    }

    public void setTotalPrice(int totalPrice) {
        //STUB
     }

    public void setCustomerName(String CustomerName) {
        //STUB
     }

    public void setDeliveryAddress(String deliveryAddress) {
        //STUB
    }

}
