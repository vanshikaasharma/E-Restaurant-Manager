package model;

import java.util.ArrayList;

// Represents an order, with the name of the customer, item that has been ordered,
// the total cost, the restaurant from which the food is ordered, and the delivery address
public class OrderFood {

    private Customer customer;                  // name of the customer ordering food
    private String restaurantName;             // name of the restaurant
    private ArrayList<MenuItems> orderItems;   // items available to order from Menu
    private double totalPrice;                  // total price of food that is ordered
    private String deliveryAddress;             // the address at which the food is delivered

    /*
     * REQUIRES: the restaurant exists, at least 1 item from the menu is
     *           selected and totalPrice > 0
     * EFFECTS: the delivery Address of the order is set to deliveryAddress;
     *          the totalPrice of the order is initially set to 0;
     *          the restaurant in which the reservation is being made is set to restaurant.
     */
    public OrderFood() {
        this.orderItems = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    /* 
     * REQUIRES: the item to exist in the restaurant
     * MODIFIES: this
     * EFFECTS: lets the customer add item to the order 
     */
    public void addItem(MenuItems item) {
        orderItems.add(item);
        totalPrice += item.getItemPrice(); // Update total price when item is added
    }

    /* 
     * REQUIRES: the item to exist in the restaurant and in the order
     * MODIFIES: this
     * EFFECTS: lets the customer remove item from the order 
     */
    public void removeItem(MenuItems item) {
        orderItems.remove(item);
        totalPrice -= item.getItemPrice();
    }

    // Getters
    public ArrayList<MenuItems> getOrderItems() {
        return orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    // Setters
    public void setOrderItems(ArrayList<MenuItems> items) {
        this.orderItems = items;
        totalPrice = 0.0;
        for (MenuItems item : items) {
            totalPrice += item.getItemPrice();
        }
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
