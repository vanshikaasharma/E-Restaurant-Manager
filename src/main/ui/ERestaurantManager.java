package ui;

import model.OrderFood;
import model.MenuItems;
import model.Reservation;
import model.Restaurant;
import model.Review;

import java.util.ArrayList;
import java.util.Scanner;

// Represents the E Restaurant Manager that manages the restaurants
public class ERestaurantManager {

    private ArrayList<Restaurant> restaurants; // the list of restaurants on the E restaurant manager
    private ArrayList<OrderFood> orders; // the list of orders
    private Scanner scanner; // Scanner for user input

    /*
     * EFFECTS: creates a list of restaurants, initializes customers and orders
     * lists,
     * and sets up the scanner for user input
     */
    public ERestaurantManager() {
        this.restaurants = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        runInterface();
    }

    // Run the E Restaurant Manager to demonstrate its functionality
    public void runInterface() {
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the E Restaurant Manager!");
            System.out.print("\nEnter your choice:\n");
            System.out.println("1. Restaurant Owner");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            int userType = scanner.nextInt();

            switch (userType) {
                case 1:
                    handleOwnerOptions();
                    break;
                case 2:
                    handleCustomerOptions();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
        System.out.println("Exiting the E Restaurant Manager. Goodbye! Have a nice day!");
    }

    /*
     * EFFECTS: displays options available to Restaurant Owner
     */
    public void displayOwnerOptions() {
        System.out.println("\nWhat are you looking for:");
        System.out.println("1: Add Restaurant");
        System.out.println("2: Add Menu Item");
        System.out.println("3: Read Reviews");
        System.out.println("4: Exit to Main Menu");
    }

    /*
     * EFFECTS: displays options available to Customer
     */
    public void displayCustomerOptions() {
        System.out.println("\nWhat are you looking for:");
        System.out.println("1: Make Reservation");
        System.out.println("2: Place Order");
        System.out.println("3: Leave Review");
        System.out.println("4: View all Restaurants");
        System.out.println("5: Read Reviews");
        System.out.println("6: Exit to Main Menu");
    }

    /*
     * EFFECTS: handles the operations that is available to the Restaurant Owner
     */
    private void handleOwnerOptions() {
        boolean running = true;
        while (running) {
            displayOwnerOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addRestaurant();
                    break;
                case 2:
                    addMenuItem();
                    break;
                case 3:
                    readReviews();
                    break;
                case 4:
                    running = false; // Exit to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /*
     * EFFECTS: handles the operations that is available to the Customer
     */
    private void handleCustomerOptions() {
        boolean running = true;
        while (running) {
            displayCustomerOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();
            running = processCustomerChoice(choice);
        }
    }

    /*
     * EFFECTS: stores the chpice made by the Customer
     */
    private boolean processCustomerChoice(int choice) {
        switch (choice) {
            case 1:
                makeReservation();
                break;
            case 2:
                placeOrder();
                break;
            case 3:
                leaveReview();
                break;
            case 4:
                listRestaurants();
                break;
            case 5:
                readReviews();
                break;
            case 6:
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    /*
     * EFFECTS: adds a restaurant using user input
     */
    private void addRestaurant() {
        System.out.print("Enter restaurant name: ");
        String name = scanner.nextLine();
        System.out.print("Enter restaurant location: ");
        String location = scanner.nextLine();
        System.out.print("Enter cuisine type: ");
        String cuisine = scanner.nextLine();

        Restaurant restaurant = new Restaurant(name, location, cuisine);
        restaurants.add(restaurant);
        System.out.println("Restaurant added: " + name);
    }

    /*
     * EFFECTS: lets the user read reviews for all restaurants
     */
    private void readReviews() {
        for (Restaurant restaurant : restaurants) {
            System.out.println("\nReviews for " + restaurant.getRestaurantName() + ":");
            if (restaurant.getRestaurantReviews().isEmpty()) {
                System.out.println("No reviews available.");
            } else {
                for (Review review : restaurant.getRestaurantReviews()) {
                    System.out.println(" - " + review.getCustomerName() + ": " + review.getReviewComment()
                            + " (Rating: " + review.getRating() + ")");
                }
            }
        }
    }

    /*
     * EFFECTS: finds restaurants in the ArrayList
     */
    public Restaurant findRestaurant(String name) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRestaurantName().equalsIgnoreCase(name)) {
                return restaurant;
            }
        }
        return null;
    }

    /*
     * EFFECTS: retrieves a list of all restaurants
     */
    public void listRestaurants() {
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
        } else {
            System.out.println("Available Restaurants:");
            for (Restaurant restaurant : restaurants) {
                System.out.println(" - " + restaurant.getRestaurantName() + " (" + restaurant.getCuisineType() + ")");
            }
        }
    }

    /*
     * REQUIRES: restaurant != null
     * EFFECTS: places an order for a customer using user input
     */
    private void placeOrder() {
        String restaurantName = enterRestaurantName();
        Restaurant restaurant = findRestaurant(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }
        displayMenu(restaurantName);
        
        String customerName = enterCustomerName();
        ArrayList<MenuItems> orderItems = enterOrderItems(restaurant);
    
        if (!orderItems.isEmpty()) {
            placeCustomerOrder(customerName, restaurant, orderItems);
        } else {
            System.out.println("No items added to the order.");
        }
    }

    /*
     * EFFECTS: prompts the user to input the restaurant name
     */
    private String enterRestaurantName() {
        System.out.print("Enter restaurant name: ");
        return scanner.nextLine();
    }
    
    /*
     * EFFECTS: prompts the user to input the their name
     */
    private String enterCustomerName() {
        System.out.print("Enter customer name: ");
        return scanner.nextLine();
    }
    
    /*
     * EFFECTS: prompts the user to input menu item names for an order
     */
    private ArrayList<MenuItems> enterOrderItems(Restaurant restaurant) {
        ArrayList<MenuItems> orderItems = new ArrayList<>();
        boolean addingItems = true;
    
        while (addingItems) {
            System.out.print("Enter item name (or 'done' to finish): ");
            String itemName = scanner.nextLine();
            if (itemName.equalsIgnoreCase("done")) {
                addingItems = false;
            } else {
                MenuItems menuItem = restaurant.findMenuItem(itemName);
                if (menuItem != null) {
                    orderItems.add(menuItem);
                    System.out.println(itemName + " added to the order.");
                } else {
                    System.out.println("Item not found on the menu.");
                }
            }
        }
        return orderItems;
    }

    /*
     * EFFECTS: lets the user place the order
     */
    private void placeCustomerOrder(String customerName, Restaurant restaurant, ArrayList<MenuItems> orderItems) {
        OrderFood order = new OrderFood();
        order.setCustomerName(customerName);
        order.setRestaurantName(restaurant.getRestaurantName());
        order.setOrderItems(orderItems);
        order.setTotalPrice(orderItems.stream().mapToDouble(MenuItems::getItemPrice).sum());
        restaurant.addOrder(order);
        orders.add(order);
        System.out.println("Order placed successfully for " + customerName);
        System.out.println("your total cost is " + order.getTotalPrice());
    }
    
    

    /*
     * EFFECTS: retrieves a list of all orders
     */
    public ArrayList<OrderFood> getOrders() {
        return orders;
    }

    /*
     * REQUIRES: restaurant != null
     * EFFECTS: makes a reservation for a customer using user input
     */
    private void makeReservation() {
        System.out.print("Enter restaurant name: ");
        String restaurantName = scanner.nextLine();

        // Display menu before making a reservation
        displayMenu(restaurantName);

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter reservation date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter reservation time (HH:MM): ");
        String time = scanner.nextLine();
        System.out.print("Enter number of guests: ");
        int numberOfGuests = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Restaurant restaurant = findRestaurant(restaurantName);
        if (restaurant != null) {
            Reservation reservation = new Reservation(customerName, date, time, numberOfGuests);
            restaurant.addReservation(reservation);
            System.out.println("Reservation made successfully for " + customerName);
        } else {
            System.out.println("Restaurant not found.");
        }
    }

    /*
     * REQUIRES: restaurant != null
     * EFFECTS: lets customer leave a review for the restaurant using user input
     */
    private void leaveReview() {
        System.out.print("Enter restaurant name: ");
        String restaurantName = scanner.nextLine();

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter review comment: ");
        String comment = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Restaurant restaurant = findRestaurant(restaurantName);
        if (restaurant != null) {
            Review review = new Review(customerName, comment, rating);
            restaurant.addReview(review);
            System.out.println("Review submitted successfully.");
        } else {
            System.out.println("Restaurant not found.");
        }
    }

    /*
     * EFFECTS: displays the menu of the specified restaurant
     */
    private void displayMenu(String restaurantName) {
        Restaurant restaurant = findRestaurant(restaurantName);
        if (restaurant != null) {
            System.out.println("\nMenu for " + restaurant.getRestaurantName() + ":");
            if (restaurant.viewMenu().isEmpty()) {
                System.out.println("No menu items available.");
            } else {
                for (MenuItems item : restaurant.viewMenu()) {
                    System.out.println(" - " + item.getItemName() + ": " + item.getItemDescription()
                            + " (Price: $" + item.getItemPrice() + ", Category: " + item.getItemCategory() + ")");
                }
            }
        } else {
            System.out.println("Restaurant not found.");
        }
    }

    /*
     * EFFECTS: lets the owner add menu items to a specific restaurant
     */
    private void addMenuItem() {
        System.out.print("Enter the restaurant name to add a menu item: ");
        String restaurantName = scanner.nextLine();
        Restaurant restaurant = findRestaurant(restaurantName);

        if (restaurant != null) {
            System.out.print("Enter item name: ");
            String itemName = scanner.nextLine();
            System.out.print("Enter item description: ");
            String itemDescription = scanner.nextLine();
            System.out.print("Enter item price: ");
            double itemPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter item category: ");
            String itemCategory = scanner.nextLine();

            restaurant.addMenuItem(itemName, itemDescription, itemPrice, itemCategory); // Call method with parameters
            System.out.println("Menu item added to " + restaurantName + ": " + itemName);
        } else {
            System.out.println("Restaurant not found.");
        }
    }

}
