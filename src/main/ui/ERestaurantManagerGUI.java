package ui;

import javax.swing.*;

import model.Customer;
import model.MenuItems;
import model.OrderFood;
import model.Reservation;
import model.Restaurant;
import model.Review;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

// Represents the E Restaurant Manager GUI that manages the restaurants
public class ERestaurantManagerGUI extends JFrame {
    private static final String JSON_STORE = "./data/eRestaurant.json";
    private JButton ownerButton;
    private JButton customerButton;
    private JButton loadButton;
    private JButton exitButton;
    private ArrayList<Restaurant> restaurants;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public ERestaurantManagerGUI() {
        setTitle("E Restaurant Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

        this.restaurants = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        mainMenuPanel();

        setVisible(true);
    }

    // EFFECTS: Creates the main menu panel with options for owner, customer, load,
    // and exit
    private void mainMenuPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to the E Restaurant Manager!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(welcomeLabel);

        JPanel ownerCustomerPanel = new JPanel();
        ownerCustomerPanel.setLayout(new GridLayout(1, 2, 10, 10));

        ownerButton = new JButton("Restaurant Owner");
        customerButton = new JButton("Customer");
        ownerCustomerPanel.add(ownerButton);
        ownerCustomerPanel.add(customerButton);
        loadButton = new JButton("Load Previous Changes");
        exitButton = new JButton("Exit");

        mainPanel.add(ownerCustomerPanel);
        mainPanel.add(loadButton);
        mainPanel.add(exitButton);

        // Set up action listeners for buttons
        ownerButton.addActionListener(e -> handleOwnerOptions());
        customerButton.addActionListener(e -> handleCustomerOptions());
        loadButton.addActionListener(e -> loadData());
        exitButton.addActionListener(e -> System.exit(0));

        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        revalidate();
        repaint();
    }

    // EFFECTS: Owner options panel
    private void handleOwnerOptions() {
        JPanel ownerPanel = new JPanel();
        ownerPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel ownerLabel = new JLabel("Restaurant Owner Options", SwingConstants.CENTER);
        ownerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ownerPanel.add(ownerLabel);

        JPanel addRow1 = createButtonRow("Add Restaurant", "Add Menu Item");
        JButton addRestaurantButton = (JButton) addRow1.getComponent(0);
        JButton addMenuItemButton = (JButton) addRow1.getComponent(1);
        addRestaurantButton.addActionListener(e -> addRestaurant());
        addMenuItemButton.addActionListener(e -> addMenuItem());
        ownerPanel.add(addRow1);

        JPanel addRow2 = createButtonRow("Update Menu Item", "Remove Menu Item");
        JButton updateMenuItemButton = (JButton) addRow2.getComponent(0);
        JButton removeMenuItemButton = (JButton) addRow2.getComponent(1);
        updateMenuItemButton.addActionListener(e -> updateMenuItem());
        removeMenuItemButton.addActionListener(e -> removeMenuItem());
        ownerPanel.add(addRow2);

        JPanel addRow3 = createButtonRow("Read Reviews", "Save Changes");
        JButton readReviewsButton = (JButton) addRow3.getComponent(0);
        JButton saveChangesButton = (JButton) addRow3.getComponent(1);
        readReviewsButton.addActionListener(e -> readReviews());
        saveChangesButton.addActionListener(e -> saveData());
        ownerPanel.add(addRow3);

        JButton mainMenuButton = new JButton("Back to the main menu");
        mainMenuButton.addActionListener(e -> returnToMainMenu());
        ownerPanel.add(mainMenuButton);
        getContentPane().removeAll();
        getContentPane().add(ownerPanel);
        revalidate();
        repaint();
    }

    // EFFECTS: Customer options panel
    private void handleCustomerOptions() {
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel customerLabel = new JLabel("Customer Options", SwingConstants.CENTER);
        customerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        customerPanel.add(customerLabel);

        JPanel addRow1 = createButtonRow("Make Reservation", "Place Order");
        JButton makedReservationButton = (JButton) addRow1.getComponent(0);
        JButton placeOrderButton = (JButton) addRow1.getComponent(1);
        makedReservationButton.addActionListener(e -> makeReservation());
        placeOrderButton.addActionListener(e -> placeOrder());
        customerPanel.add(addRow1);

        JPanel addRow2 = createButtonRow("Leave Review", "View Restarants");
        JButton leaveReviewButton = (JButton) addRow2.getComponent(0);
        JButton viewRestaurantsButton = (JButton) addRow2.getComponent(1);
        leaveReviewButton.addActionListener(e -> leaveReview());
        viewRestaurantsButton.addActionListener(e -> listRestaurants());
        customerPanel.add(addRow2);

        JPanel addRow3 = createButtonRow("View Reviews", "Save Changes");
        JButton readReviewsButton = (JButton) addRow3.getComponent(0);
        JButton saveChangesButton = (JButton) addRow3.getComponent(1);
        readReviewsButton.addActionListener(e -> readReviews());
        saveChangesButton.addActionListener(e -> saveData());
        customerPanel.add(addRow3);

        JButton mainMenuButton = new JButton("Back to the main menu");
        mainMenuButton.addActionListener(e -> returnToMainMenu());
        customerPanel.add(mainMenuButton);

        getContentPane().removeAll();
        getContentPane().add(customerPanel);
        revalidate();
        repaint();
    }

    // EFFECTS: Creates a row with two buttons
    private JPanel createButtonRow(String button1Text, String button2Text) {
        JPanel rowPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton button1 = new JButton(button1Text);
        JButton button2 = new JButton(button2Text);
        rowPanel.add(button1);
        rowPanel.add(button2);
        return rowPanel;
    }

    // EFFECTS: Loads previous data (dummy action for now)

    private void loadData() {
        try {
            restaurants = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Data loaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    // EFFECTS: adds a restaurant using user input
    private void addRestaurant() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JTextField nameField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField cuisineField = new JTextField();
        JButton addButton = new JButton("Add Restaurant");
        JButton backButton = new JButton("Back");

        panel.add(new JLabel("Restaurant Name: "));
        panel.add(nameField);
        panel.add(new JLabel("Location: "));
        panel.add(locationField);
        panel.add(new JLabel("Cuisine Type: "));
        panel.add(cuisineField);
        panel.add(addButton);
        panel.add(backButton);

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String location = locationField.getText();
            String cuisine = cuisineField.getText();
            if (!name.isEmpty() && !location.isEmpty() && !cuisine.isEmpty()) {
                Restaurant restaurant = new Restaurant(name, location, cuisine);
                if (!restaurants.contains(restaurant)) {
                    restaurants.add(restaurant);
                    JOptionPane.showMessageDialog(this, "Restaurant added: " + name);
                    returnToOwnerMenu();
                } else {
                    JOptionPane.showMessageDialog(this, "Restaurant already exists.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });

        backButton.addActionListener(e -> handleOwnerOptions());
        setContentPane(panel);
        revalidate();
    }

    /*
     * EFFECTS: finds restaurants in the ArrayList
     */
    public Restaurant checkRestaurant() {
        Restaurant r = null;
        JTextField restaurantNameField = new JTextField();
        JTextField restaurantLocationField = new JTextField();

        Object[] message = {
                "Enter the Restaurant Name:", restaurantNameField,
                "Enter the Restaurant Location:", restaurantLocationField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Find Restaurant", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String restaurantName = restaurantNameField.getText();
            String restaurantLocation = restaurantLocationField.getText();

            for (Restaurant restaurant : restaurants) {
                if (restaurant.getRestaurantName().equals(restaurantName)
                        && restaurant.getRestaurantLocation().equals(restaurantLocation)) {
                    return restaurant;
                }
            }

        }
        return r;
    }

    // EFFECTS: Lets the owner add menu items to a specific restaurant
    private void addMenuItem() {

        Restaurant restaurant = checkRestaurant();

        if (restaurant != null) {
            JPanel menuItemPanel = new JPanel(new GridLayout(6, 2, 10, 10));

            JTextField itemNameField = new JTextField();
            JTextField itemCategoryField = new JTextField();
            JTextField itemDescriptionField = new JTextField();
            JTextField itemPriceField = new JTextField();

            JButton addButton = new JButton("Add Menu Item");
            JButton backButton = new JButton("Back");

            menuItemPanel.add(new JLabel("Menu Item Name:"));
            menuItemPanel.add(itemNameField);
            menuItemPanel.add(new JLabel("Menu Item Category:"));
            menuItemPanel.add(itemCategoryField);
            menuItemPanel.add(new JLabel("Menu Item Description:"));
            menuItemPanel.add(itemDescriptionField);
            menuItemPanel.add(new JLabel("Menu Item Price:"));
            menuItemPanel.add(itemPriceField);
            menuItemPanel.add(addButton);
            menuItemPanel.add(backButton);

            addButton.addActionListener(e -> {
                String itemName = itemNameField.getText();
                String itemCategory = itemCategoryField.getText();
                String itemDescription = itemDescriptionField.getText();

                if (!itemName.isEmpty() && !itemCategory.isEmpty() && !itemDescription.isEmpty()
                        && !itemPriceField.getText().isEmpty()) {
                    try {
                        double itemPrice = Double.parseDouble(itemPriceField.getText());

                        restaurant.addMenuItem(itemCategory, itemName, itemPrice, itemDescription);
                        JOptionPane.showMessageDialog(this, "Menu item added successfully!");
                        returnToOwnerMenu();

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                }
            });

            backButton.addActionListener(e -> returnToOwnerMenu());

            setContentPane(menuItemPanel);
            revalidate();
        } else {
            JOptionPane.showMessageDialog(this, "Restaurant not found.");
        }
    }

    // EFFECTS: lets the user update a menu item of a specific restaurant
    private void updateMenuItem() {
        Restaurant restaurant = checkRestaurant();

        if (restaurant != null) {
            JTextField itemNameField = new JTextField();

            Object[] message = {
                    "Enter the name of the menu item to update:", itemNameField
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Find Menu Item", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String itemName = itemNameField.getText();
                MenuItems menuItem = restaurant.findMenuItem(itemName);

                if (menuItem != null) {
                    JPanel updateItemPanel = new JPanel(new GridLayout(6, 2, 10, 10));

                    JTextField newDescriptionField = new JTextField(menuItem.getItemDescription());
                    JTextField newPriceField = new JTextField(String.valueOf(menuItem.getItemPrice()));
                    JTextField newCategoryField = new JTextField(menuItem.getItemCategory());

                    JButton updateButton = new JButton("Update Menu Item");
                    JButton backButton = new JButton("Back");

                    updateItemPanel.add(new JLabel("New Description:"));
                    updateItemPanel.add(newDescriptionField);
                    updateItemPanel.add(new JLabel("New Price:"));
                    updateItemPanel.add(newPriceField);
                    updateItemPanel.add(new JLabel("New Category:"));
                    updateItemPanel.add(newCategoryField);
                    updateItemPanel.add(updateButton);
                    updateItemPanel.add(backButton);

                    updateButton.addActionListener(e -> {
                        String newDescription = newDescriptionField.getText();
                        String newCategory = newCategoryField.getText();

                        if (!newDescription.isEmpty() && !newCategory.isEmpty() && !newPriceField.getText().isEmpty()) {
                            try {
                                double newPrice = Double.parseDouble(newPriceField.getText());

                                restaurant.updateMenuItem(itemName, newDescription, newPrice, newCategory);
                                JOptionPane.showMessageDialog(this, "Menu item updated successfully!");
                                returnToOwnerMenu();

                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(this,
                                        "Invalid price format. Please enter a valid number.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                        }
                    });

                    backButton.addActionListener(e -> returnToOwnerMenu());

                    setContentPane(updateItemPanel);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(this, "Menu item not found.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Restaurant not found.");
        }
    }

    // EFFECTS: lets the user remove a menu item from a specific restaurant
    private void removeMenuItem() {
        Restaurant restaurant = checkRestaurant();

        if (restaurant != null) {
            JPanel removeItemPanel = new JPanel(new GridLayout(3, 2, 10, 10));

            JTextField itemNameField = new JTextField();
            JButton removeButton = new JButton("Remove Menu Item");
            JButton backButton = new JButton("Back");

            removeItemPanel.add(new JLabel("Enter Menu Item Name:"));
            removeItemPanel.add(itemNameField);
            removeItemPanel.add(removeButton);
            removeItemPanel.add(backButton);

            removeButton.addActionListener(e -> {
                String itemName = itemNameField.getText();

                if (!itemName.isEmpty()) {
                    MenuItems menuItem = restaurant.findMenuItem(itemName);

                    if (menuItem != null) {
                        restaurant.removeMenuItem(itemName);
                        JOptionPane.showMessageDialog(this, "Menu item removed successfully!");
                        returnToOwnerMenu();
                    } else {
                        JOptionPane.showMessageDialog(this, "Menu item not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter the menu item name.");
                }
            });

            backButton.addActionListener(e -> returnToOwnerMenu());
            setContentPane(removeItemPanel);
            revalidate();
        } else {
            JOptionPane.showMessageDialog(this, "Restaurant not found.");
        }
    }

    /*
     * EFFECTS: lets the user read reviews for the given restaurant
     */
    private void readReviews() {
        Restaurant restaurant = checkRestaurant();

        if (restaurant != null) {
            if (restaurant.getRestaurantReviews().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No reviews available for this restaurant.");
            } else {
                StringBuilder reviewsList = new StringBuilder();
                for (Review review : restaurant.getRestaurantReviews()) {
                    reviewsList.append("Customer: ").append(review.getCustomer().getName())
                            .append(", Rating: ").append(review.getRating())
                            .append("\nComment: ").append(review.getReviewComment()).append("\n\n");
                }

                JTextArea textArea = new JTextArea(10, 30);
                textArea.setText(reviewsList.toString());
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);

                JOptionPane.showMessageDialog(this, scrollPane, "Reviews for " + restaurant.getRestaurantName(),
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Restaurant not found.");
        }
    }

    /*
     * REQUIRES: restaurant != null
     * EFFECTS: makes a reservation for a customer using user input
     */
    private void makeReservation() {
        JPanel reservationPanel = new JPanel(new GridLayout(8, 2, 10, 10));

        Restaurant restaurant = checkRestaurant();
        if (restaurant != null) {
            // Customer name and email
            JTextField nameField = new JTextField();
            JTextField emailField = new JTextField();
            reservationPanel.add(new JLabel("Customer Name:"));
            reservationPanel.add(nameField);
            reservationPanel.add(new JLabel("Customer Email:"));
            reservationPanel.add(emailField);

            // Date input with validation
            JTextField dateField = new JTextField();
            reservationPanel.add(new JLabel("Reservation Date (YYYY-MM-DD):"));
            reservationPanel.add(dateField);

            // Time input with validation
            JTextField timeField = new JTextField();
            reservationPanel.add(new JLabel("Reservation Time (HH:MM):"));
            reservationPanel.add(timeField);

            // Number of guests with validation
            JTextField guestsField = new JTextField();
            reservationPanel.add(new JLabel("Number of Guests:"));
            reservationPanel.add(guestsField);

            // Buttons
            JButton reserveButton = new JButton("Reserve");
            JButton backButton = new JButton("Back");
            reservationPanel.add(reserveButton);
            reservationPanel.add(backButton);

            reserveButton.addActionListener(e -> {
                String customerName = nameField.getText();
                String customerEmail = emailField.getText();
                String date = dateField.getText();
                String time = timeField.getText();
                String guestsText = guestsField.getText();

                // Validate fields
                if (customerName.isEmpty() || customerEmail.isEmpty() || date.isEmpty() || time.isEmpty()
                        || guestsText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                    return;
                }

                try {
                    int guests = Integer.parseInt(guestsText);
                    if (guests <= 0) {
                        throw new NumberFormatException(); // For invalid number of guests
                    }

                    LocalDate reservationDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalTime reservationTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));

                    Customer customer = new Customer(customerName, customerEmail);
                    Reservation reservation = new Reservation(customer, reservationDate, reservationTime, guests);
                    restaurant.addReservation(reservation);
                    JOptionPane.showMessageDialog(this, "Reservation made successfully for " + customerName);
                    returnToCustomerMenu();

                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid date or time format.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid number of guests.");
                }
            });

            backButton.addActionListener(e -> returnToCustomerMenu());

            setContentPane(reservationPanel);
            revalidate();
        } else {
            JOptionPane.showMessageDialog(this, "Restaurant not found.");
        }
    }

    // REQUIRES: restaurant != null
    // EFFECTS: places an order for a customer using user input via a GUI
    private void placeOrder() {
        Restaurant restaurant = checkRestaurant();
        if (restaurant == null) {
            JOptionPane.showMessageDialog(this, "Restaurant not found.");
            return;
        }

        displayMenu(restaurant);

        JPanel orderPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField customerNameField = new JTextField();
        JTextField customerEmailField = new JTextField();

        JComboBox<String> menuComboBox = new JComboBox<>();
        for (MenuItems item : restaurant.getRestaurantMenu().getMenuItems()) {
            menuComboBox.addItem(item.getItemName());
        }

        JButton addButton = new JButton("Add Item");
        JButton placeOrderButton = new JButton("Place Order");
        JButton backButton = new JButton("Back");

        orderPanel.add(new JLabel("Customer Name:"));
        orderPanel.add(customerNameField);
        orderPanel.add(new JLabel("Customer Email:"));
        orderPanel.add(customerEmailField);
        orderPanel.add(new JLabel("Select Menu Item:"));
        orderPanel.add(menuComboBox);
        orderPanel.add(addButton);
        orderPanel.add(placeOrderButton);
        orderPanel.add(backButton);

        ArrayList<MenuItems> orderItems = new ArrayList<>();

        addButton.addActionListener(e -> {
            String selectedItem = (String) menuComboBox.getSelectedItem();
            MenuItems menuItem = restaurant.findMenuItem(selectedItem);
            if (menuItem != null) {
                orderItems.add(menuItem);
                JOptionPane.showMessageDialog(this, selectedItem + " added to your order.");
            }
        });

        placeOrderButton.addActionListener(e -> {
            String customerName = customerNameField.getText();
            String customerEmail = customerEmailField.getText();

            if (!customerName.isEmpty() && !customerEmail.isEmpty() && !orderItems.isEmpty()) {
                Customer customer = new Customer(customerName, customerEmail);
                OrderFood order = new OrderFood();
                order.setCustomer(customer);
                order.setRestaurantName(restaurant.getRestaurantName());
                order.setOrderItems(orderItems);
                order.setTotalPrice(orderItems.stream().mapToDouble(MenuItems::getItemPrice).sum());
                restaurant.addOrder(order);
                restaurant.addOrder(order);
                JOptionPane.showMessageDialog(this, "Order placed successfully!");
                returnToCustomerMenu();
            } else {
                JOptionPane.showMessageDialog(this, "Please complete all fields and add items to the order.");
            }
        });

        backButton.addActionListener(e -> returnToCustomerMenu());

        setContentPane(orderPanel);
        revalidate();
    }

    /*
     * REQUIRES: restaurant != null
     * EFFECTS: lets customer leave a review for the restaurant using user input
     */
    private void leaveReview() {
        Restaurant restaurant = checkRestaurant();

        if (restaurant != null) {
            JPanel reviewPanel = new JPanel(new GridLayout(6, 2, 10, 10));

            JTextField customerNameField = new JTextField();
            JTextField customerEmailField = new JTextField();
            JTextField commentField = new JTextField();

            // Create a JComboBox for the rating selection
            String[] ratings = { "1", "2", "3", "4", "5" };
            JComboBox<String> ratingComboBox = new JComboBox<>(ratings);

            JButton submitButton = new JButton("Submit Review");
            JButton backButton = new JButton("Back");

            reviewPanel.add(new JLabel("Customer Name:"));
            reviewPanel.add(customerNameField);
            reviewPanel.add(new JLabel("Customer Email:"));
            reviewPanel.add(customerEmailField);
            reviewPanel.add(new JLabel("Comment:"));
            reviewPanel.add(commentField);
            reviewPanel.add(new JLabel("Rating (1-5):"));
            reviewPanel.add(ratingComboBox);
            reviewPanel.add(submitButton);
            reviewPanel.add(backButton);

            submitButton.addActionListener(e -> {
                String customerName = customerNameField.getText();
                String customerEmail = customerEmailField.getText();
                String comment = commentField.getText();
                String ratingText = (String) ratingComboBox.getSelectedItem(); // Get the selected rating

                if (!customerName.isEmpty() && !customerEmail.isEmpty() && !comment.isEmpty()
                        && ratingText != null) {
                    try {
                        int rating = Integer.parseInt(ratingText);

                        if (rating >= 1 && rating <= 5) {
                            Customer customer = new Customer(customerName, customerEmail);
                            Review review = new Review(customer, comment, rating);
                            restaurant.addReview(review);
                            JOptionPane.showMessageDialog(this, "Review submitted successfully!");
                            returnToCustomerMenu();
                        } else {
                            JOptionPane.showMessageDialog(this, "Please enter a valid rating between 1 and 5.");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid rating format. Please enter a valid number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                }
            });

            backButton.addActionListener(e -> returnToCustomerMenu());

            // Set the new panel and refresh the frame
            setContentPane(reviewPanel);
            revalidate();
        } else {
            JOptionPane.showMessageDialog(this, "Restaurant not found.");
        }
    }

    /*
     * EFFECTS: retrieves a list of all restaurants
     */
    private void listRestaurants() {
        if (restaurants.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No restaurants available.");
        } else {
            StringBuilder restaurantList = new StringBuilder();
            for (Restaurant restaurant : restaurants) {
                restaurantList.append("Name: ").append(restaurant.getRestaurantName())
                        .append(", Location: ").append(restaurant.getRestaurantLocation())
                        .append(", Cuisine: ").append(restaurant.getCuisineType()).append("\n");
            }

            JTextArea textArea = new JTextArea(10, 30);
            textArea.setText(restaurantList.toString());
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(this, scrollPane, "List of Restaurants", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // REQUIRES: restaurant != null
    // EFFECTS: displays the menu of the selected restaurant in a GUI
    private void displayMenu(Restaurant restaurant) {

        // Create a panel to display the menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());

        // Create a list of menu items to display
        DefaultListModel<String> menuListModel = new DefaultListModel<>();
        for (MenuItems item : restaurant.getRestaurantMenu().getMenuItems()) {
            menuListModel.addElement(item.getItemName() + " - $" + item.getItemPrice());
        }

        // Create a JList to show menu items
        JList<String> menuList = new JList<>(menuListModel);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setVisibleRowCount(10);
        JScrollPane menuScrollPane = new JScrollPane(menuList);
        menuPanel.add(menuScrollPane, BorderLayout.CENTER);

        // Create a button for returning to the previous screen
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> returnToCustomerMenu());
        menuPanel.add(backButton, BorderLayout.SOUTH);

        // Set the content pane to the menu panel and refresh the frame
        setContentPane(menuPanel);
        revalidate();
    }

    /*
     * EFFECTS: Prompts user to save data
     */
    private void saveData() {
        try {
            jsonWriter.write(restaurants);
            JOptionPane.showMessageDialog(this, "Data saved successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }

    // EFFECTS: Return to main menu
    private void returnToMainMenu() {
        getContentPane().removeAll();
        mainMenuPanel();
        revalidate();
        repaint();
    }

    // EFFECTS: Return to owner menu
    private void returnToOwnerMenu() {
        getContentPane().removeAll();
        handleOwnerOptions();
        revalidate();
        repaint();
    }

    // EFFECTS: Return to customer menu
    private void returnToCustomerMenu() {
        getContentPane().removeAll();
        handleCustomerOptions();
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ERestaurantManagerGUI());
    }

}
