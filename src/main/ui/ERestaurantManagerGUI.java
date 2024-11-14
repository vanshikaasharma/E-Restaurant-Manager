package ui;

import javax.swing.*;

import model.MenuItems;
import model.Restaurant;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
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
        add(mainPanel);

        ownerButton.addActionListener(e -> handleOwnerOptions());
        customerButton.addActionListener(e -> handleCustomerOptions());
        loadButton.addActionListener(e -> loadData());
        exitButton.addActionListener(e -> System.exit(0));
        setVisible(true);
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
        JOptionPane.showMessageDialog(this, "Loading data...");
    }

    // EFFECTS: adds a restaurant using user input
    private void addRestaurant() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
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

            // Step 6: Set the new panel and refresh the frame
            setContentPane(menuItemPanel);
            revalidate();
        } else {
            // If restaurant not found
            JOptionPane.showMessageDialog(this, "Restaurant not found.");
        }
    }

    // EFFECTS: lets the user update a menu item of a specific restaurant
private void updateMenuItem() {
    Restaurant restaurant = checkRestaurant();

    if (restaurant != null) {
        JTextField itemNameField = new JTextField();
        
        // Step 1: Prompt the user to enter the menu item to update
        Object[] message = {
            "Enter the name of the menu item to update:", itemNameField
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Find Menu Item", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String itemName = itemNameField.getText();
            MenuItems menuItem = restaurant.findMenuItem(itemName);
            
            if (menuItem != null) {
                // Step 2: Create a panel for updating the menu item
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
                            JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                    }
                });

                backButton.addActionListener(e -> returnToOwnerMenu());

                // Step 6: Set the new panel and refresh the frame
                setContentPane(updateItemPanel);
                revalidate();
            } else {
                // If menu item not found
                JOptionPane.showMessageDialog(this, "Menu item not found.");
            }
        }
    } else {
        // If restaurant not found
        JOptionPane.showMessageDialog(this, "Restaurant not found.");
    }
}


    /*
     * EFFECTS: lets the user remove a menu item from a specific restaurant
     */
    private void removeMenuItem() {
        JOptionPane.showMessageDialog(this, "Remove menu item...in progress...");
    }

    /*
     * EFFECTS: lets the user read reviews for the given restaurant
     */
    private void readReviews() {
        JOptionPane.showMessageDialog(this, "Read reviews...in progress...");
    }

    /*
     * REQUIRES: restaurant != null
     * EFFECTS: makes a reservation for a customer using user input
     */
    private void makeReservation() {
        JOptionPane.showMessageDialog(this, "Making reservations...in progress...");
    }

    /*
     * REQUIRES: restaurant != null
     * EFFECTS: places an order for a customer using user input
     */
    private void placeOrder() {
        JOptionPane.showMessageDialog(this, "placing order...in progress...");
    }

    /*
     * REQUIRES: restaurant != null
     * EFFECTS: lets customer leave a review for the restaurant using user input
     */
    private void leaveReview() {
        JOptionPane.showMessageDialog(this, "leaving review...in progress...");
    }

    /*
     * EFFECTS: retrieves a list of all restaurants
     */
    private void listRestaurants() {
        JOptionPane.showMessageDialog(this, "list restaurants...in progress...");
    }

    /*
     * EFFECTS: Prompts user to save data
     */
    protected void saveData() {
        JOptionPane.showMessageDialog(this, "Save data...in progress...");
    }

    // EFFECTS: Return to main menu
    private void returnToMainMenu() {
        getContentPane().removeAll();
        new ERestaurantManagerGUI(); // Reload main GUI
        revalidate();
        repaint();
    }

    // EFFECTS: Return to owner menu
    private void returnToOwnerMenu() {
        getContentPane().removeAll();
        handleOwnerOptions(); // Open the owner options panel
        revalidate();
        repaint();
    }

    // EFFECTS: Return to customer menu
    private void returnToCustomerMenu() {
        getContentPane().removeAll();
        handleCustomerOptions(); // Open the customer options panel
        revalidate();
        repaint();
    }

}
