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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setLayout(new GridLayout(4, 1, 15, 15));

        mainPanel.setBackground(new Color(245, 202, 195));

        JLabel welcomeLabel = new JLabel("Welcome to the E Restaurant Manager!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(welcomeLabel);

        JPanel ownerCustomerPanel = new JPanel();
        ownerCustomerPanel.setLayout(new GridLayout(1, 2, 10, 10));
        ownerCustomerPanel.setBackground(new Color(245, 202, 195));

        ownerButton = createSizedButton("Restaurant Owner");
        customerButton = createSizedButton("Customer");
        styleButton(ownerButton, new Color(247, 237, 226), new Color(0, 0, 0));
        styleButton(customerButton, new Color(247, 237, 226), new Color(0, 0, 0));

        ownerCustomerPanel.add(ownerButton);
        ownerCustomerPanel.add(customerButton);

        loadButton = createSizedButton("Load Previous Changes");
        exitButton = createSizedButton("Exit");
        styleButton(loadButton, new Color(247, 237, 226), new Color(0, 0, 0));
        styleButton(exitButton, new Color(242, 132, 130), new Color(255, 255, 255));
        mainPanel.add(ownerCustomerPanel);
        mainPanel.add(loadButton);
        mainPanel.add(exitButton);

        ownerButton.addActionListener(e -> handleOwnerOptions());
        customerButton.addActionListener(e -> handleCustomerOptions());
        loadButton.addActionListener(e -> loadData());
        exitButton.addActionListener(e -> System.exit(0));

        setPanelContent(mainPanel);
    }

    // EFFECTS: Owner options panel
    private void handleOwnerOptions() {
        JPanel ownerPanel = new JPanel();
        ownerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        ownerPanel.setLayout(new GridLayout(5, 1, 15, 15));

        JLabel ownerLabel = new JLabel("Restaurant Owner Options", SwingConstants.CENTER);
        ownerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ownerPanel.setBackground(new Color(245, 202, 195));
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

        JPanel addRow3 = createOptionButtonRow("Read Reviews", "Save Changes");
        JButton readReviewsButton = (JButton) addRow3.getComponent(0);
        JButton saveChangesButton = (JButton) addRow3.getComponent(1);
        readReviewsButton.addActionListener(e -> readReviews());
        saveChangesButton.addActionListener(e -> saveData());
        ownerPanel.add(addRow3);

        JButton mainMenuButton = createSizedButton("Back to the main menu");
        mainMenuButton.addActionListener(e -> returnToMainMenu());
        styleButton(mainMenuButton, new Color(242, 132, 130), new Color(255, 255, 255));
        ownerPanel.add(mainMenuButton);

        setPanelContent(ownerPanel);
    }

    // EFFECTS: Customer options panel
    private void handleCustomerOptions() {
        JPanel customerPanel = new JPanel();
        customerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        customerPanel.setLayout(new GridLayout(5, 1, 15, 15));

        JLabel customerLabel = new JLabel("Customer Options", SwingConstants.CENTER);
        customerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        customerPanel.setBackground(new Color(245, 202, 195));
        customerPanel.add(customerLabel);

        JPanel addRow1 = createButtonRow("Make Reservation", "Place Order");
        JButton makeReservationButton = (JButton) addRow1.getComponent(0);
        JButton placeOrderButton = (JButton) addRow1.getComponent(1);
        makeReservationButton.addActionListener(e -> makeReservation());
        placeOrderButton.addActionListener(e -> placeOrder());
        customerPanel.add(addRow1);

        JPanel addRow2 = createButtonRow("Leave Review", "View Restaurants");
        JButton leaveReviewButton = (JButton) addRow2.getComponent(0);
        JButton viewRestaurantsButton = (JButton) addRow2.getComponent(1);
        leaveReviewButton.addActionListener(e -> leaveReview());
        viewRestaurantsButton.addActionListener(e -> listRestaurants());
        customerPanel.add(addRow2);

        JPanel addRow3 = createOptionButtonRow("View Reviews", "Save Changes");
        JButton readReviewsButton = (JButton) addRow3.getComponent(0);
        JButton saveChangesButton = (JButton) addRow3.getComponent(1);
        readReviewsButton.addActionListener(e -> readReviews());
        saveChangesButton.addActionListener(e -> saveData());
        customerPanel.add(addRow3);

        JButton mainMenuButton = createSizedButton("Back to the main menu");
        mainMenuButton.addActionListener(e -> returnToMainMenu());
        styleButton(mainMenuButton, new Color(242, 132, 130), new Color(255, 255, 255));
        customerPanel.add(mainMenuButton);

        setPanelContent(customerPanel);
    }

    // EFFECTS: Creates a row with two buttons with consistent sizes
    private JPanel createButtonRow(String button1Text, String button2Text) {
        JPanel rowPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        JButton button1 = createSizedButton(button1Text);
        JButton button2 = createSizedButton(button2Text);
        rowPanel.setBackground(new Color(245, 202, 195));
        styleButton(button1, new Color(247, 237, 226), new Color(0, 0, 0));
        styleButton(button2, new Color(247, 237, 226), new Color(0, 0, 0));
        rowPanel.add(button1);
        rowPanel.add(button2);
        return rowPanel;
    }

    // EFFECTS: Creates a row with two buttons with consistent sizes
    private JPanel createOptionButtonRow(String button1Text, String button2Text) {
        JPanel rowPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        JButton button1 = createSizedButton(button1Text);
        JButton button2 = createSizedButton(button2Text);
        rowPanel.setBackground(new Color(245, 202, 195));
        styleButton(button1, new Color(247, 237, 226), new Color(0, 0, 0));
        styleButton(button2, new Color(132, 165, 157), new Color(255, 255, 255));
        rowPanel.add(button1);
        rowPanel.add(button2);
        return rowPanel;
    }

    // EFFECTS: Creates a row with two buttons with consistent sizes
    private JPanel createOptionButtons(String button1Text, String button2Text) {
        JPanel rowPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        JButton button1 = createSizedButton(button1Text);
        JButton button2 = createSizedButton(button2Text);
        rowPanel.setBackground(new Color(245, 202, 195));
        styleButton(button1, new Color(242, 132, 130), new Color(255, 255, 255));
        styleButton(button2, new Color(132, 165, 157), new Color(255, 255, 255));
        rowPanel.add(button1);
        rowPanel.add(button2);
        return rowPanel;
    }

    // EFFECTS: Creates a JButton with a fixed preferred size for consistency
    private JButton createSizedButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(400, 100));
        return button;
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

        JPanel addRestaurantPanel = new JPanel(new BorderLayout(15, 15));
        addRestaurantPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        addRestaurantPanel.setBackground(new Color(245, 202, 195));

        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        fieldsPanel.setBackground(new Color(245, 202, 195));

        JTextField nameField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField cuisineField = new JTextField();

        JLabel nameLabel = new JLabel("Restaurant Name: ");
        JLabel locationLabel = new JLabel("Location: ");
        JLabel cuisineLabel = new JLabel("Cuisine Type: ");

        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        locationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cuisineLabel.setFont(new Font("Arial", Font.BOLD, 14));

        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cuisineLabel.setHorizontalAlignment(SwingConstants.CENTER);

        fieldsPanel.add(nameLabel);
        fieldsPanel.add(nameField);
        fieldsPanel.add(locationLabel);
        fieldsPanel.add(locationField);
        fieldsPanel.add(cuisineLabel);
        fieldsPanel.add(cuisineField);

        JPanel buttonPanel = createOptionButtons("Add Restaurant", "Back");

        addRestaurantPanel.add(fieldsPanel, BorderLayout.CENTER);
        addRestaurantPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = (JButton) buttonPanel.getComponent(0);
        JButton backButton = (JButton) buttonPanel.getComponent(1);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String location = locationField.getText().trim();
            String cuisine = cuisineField.getText().trim();

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
        setPanelContent(addRestaurantPanel);
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
    if (restaurants.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No restaurants available in the Manager. Please add a restaurant first.");
        return;
    }

    JPanel menuItemPanel = new JPanel(new BorderLayout(15, 15));
    menuItemPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    menuItemPanel.setBackground(new Color(245, 202, 195));

    // Fields for adding a menu item, including the restaurant dropdown
    JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 15, 15));
    fieldsPanel.setBackground(new Color(245, 202, 195));

    // Restaurant selection dropdown
    JLabel selectRestaurantLabel = new JLabel("Select Restaurant:");
    selectRestaurantLabel.setFont(new Font("Arial", Font.BOLD, 14));
    selectRestaurantLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JComboBox<String> restaurantDropdown = new JComboBox<>();
    for (Restaurant restaurant : restaurants) {
        restaurantDropdown.addItem(restaurant.getRestaurantName() + " - " + restaurant.getRestaurantLocation());
    }

    JTextField itemNameField = new JTextField();
    JTextField itemCategoryField = new JTextField();
    JTextField itemDescriptionField = new JTextField();
    JTextField itemPriceField = new JTextField();

    JLabel itemNameLabel = new JLabel("Menu Item Name: ");
    JLabel itemCategoryLabel = new JLabel("Menu Item Category: ");
    JLabel itemDescriptionLabel = new JLabel("Menu Item Description: ");
    JLabel itemPriceLabel = new JLabel("Menu Item Price: ");

    itemNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
    itemCategoryLabel.setFont(new Font("Arial", Font.BOLD, 14));
    itemDescriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
    itemPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));

    itemNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    itemCategoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
    itemDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    itemPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Add the restaurant selection and other fields to the panel
    fieldsPanel.add(selectRestaurantLabel);
    fieldsPanel.add(restaurantDropdown);
    fieldsPanel.add(itemNameLabel);
    fieldsPanel.add(itemNameField);
    fieldsPanel.add(itemCategoryLabel);
    fieldsPanel.add(itemCategoryField);
    fieldsPanel.add(itemDescriptionLabel);
    fieldsPanel.add(itemDescriptionField);
    fieldsPanel.add(itemPriceLabel);
    fieldsPanel.add(itemPriceField);

    JPanel buttonPanel = createOptionButtons("Add MenuItem", "Back");

    menuItemPanel.add(fieldsPanel, BorderLayout.CENTER);
    menuItemPanel.add(buttonPanel, BorderLayout.SOUTH);

    JButton addButton = (JButton) buttonPanel.getComponent(0);
    JButton backButton = (JButton) buttonPanel.getComponent(1);

    addButton.addActionListener(e -> {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            // Find the selected restaurant based on the dropdown choice
            Restaurant selectedRestaurant = restaurants.stream()
                    .filter(r -> (r.getRestaurantName() + " - " + r.getRestaurantLocation()).equals(selectedRestaurantInfo))
                    .findFirst()
                    .orElse(null);

            if (selectedRestaurant != null) {
                String itemName = itemNameField.getText().trim();
                String itemCategory = itemCategoryField.getText().trim();
                String itemDescription = itemDescriptionField.getText().trim();

                if (!itemName.isEmpty() && !itemCategory.isEmpty() && !itemDescription.isEmpty()
                        && !itemPriceField.getText().isEmpty()) {
                    try {
                        double itemPrice = Double.parseDouble(itemPriceField.getText().trim());

                        selectedRestaurant.addMenuItem(itemName,itemDescription, itemPrice, itemCategory);
                        JOptionPane.showMessageDialog(this, "Menu item added successfully!");
                        returnToOwnerMenu();

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                }
            }
        }
    });

    backButton.addActionListener(e -> returnToOwnerMenu());

    setPanelContent(menuItemPanel);
}



// EFFECTS: Lets the user update a menu item of a specific restaurant
private void updateMenuItem() {
    if (restaurants.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No restaurants available. Please add a restaurant first.");
        return;
    }

    JPanel updateMenuItemPanel = new JPanel(new BorderLayout(15, 15));
    updateMenuItemPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    updateMenuItemPanel.setBackground(new Color(245, 202, 195));

    // Fields for updating a menu item, including the restaurant dropdown
    JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 15, 15));
    fieldsPanel.setBackground(new Color(245, 202, 195));

    // Restaurant selection dropdown
    JLabel selectRestaurantLabel = new JLabel("Select Restaurant:");
    selectRestaurantLabel.setFont(new Font("Arial", Font.BOLD, 14));
    selectRestaurantLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JComboBox<String> restaurantDropdown = new JComboBox<>();
    for (Restaurant restaurant : restaurants) {
        restaurantDropdown.addItem(restaurant.getRestaurantName() + " - " + restaurant.getRestaurantLocation());
    }

    // Menu item dropdown (initially empty, will be populated based on the selected restaurant)
    JLabel itemNameLabel = new JLabel("Select Menu Item to Update:");
    JComboBox<String> itemDropdown = new JComboBox<>();
    itemNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
    itemNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // New fields for updating menu item details
    JTextField newDescriptionField = new JTextField();
    JTextField newPriceField = new JTextField();
    JTextField newCategoryField = new JTextField();

    JLabel newDescriptionLabel = new JLabel("New Description:");
    JLabel newPriceLabel = new JLabel("New Price:");
    JLabel newCategoryLabel = new JLabel("New Category:");

    newDescriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
    newPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));
    newCategoryLabel.setFont(new Font("Arial", Font.BOLD, 14));

    newDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    newPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
    newCategoryLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Add the restaurant selection and other fields to the panel
    fieldsPanel.add(selectRestaurantLabel);
    fieldsPanel.add(restaurantDropdown);
    fieldsPanel.add(itemNameLabel);
    fieldsPanel.add(itemDropdown);
    fieldsPanel.add(newDescriptionLabel);
    fieldsPanel.add(newDescriptionField);
    fieldsPanel.add(newPriceLabel);
    fieldsPanel.add(newPriceField);
    fieldsPanel.add(newCategoryLabel);
    fieldsPanel.add(newCategoryField);

    JPanel buttonPanel = createOptionButtons("Update MenuItem", "Back");

    updateMenuItemPanel.add(fieldsPanel, BorderLayout.CENTER);
    updateMenuItemPanel.add(buttonPanel, BorderLayout.SOUTH);

    JButton updateButton = (JButton) buttonPanel.getComponent(0);
    JButton backButton = (JButton) buttonPanel.getComponent(1);

    restaurantDropdown.addActionListener(e -> {
        itemDropdown.removeAllItems();
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            // Find the selected restaurant based on the dropdown choice
            Restaurant selectedRestaurant = restaurants.stream()
                    .filter(r -> (r.getRestaurantName() + " - " + r.getRestaurantLocation()).equals(selectedRestaurantInfo))
                    .findFirst()
                    .orElse(null);
    
            if (selectedRestaurant != null) {
                // Populate the item dropdown with the menu items' names from the selected restaurant
                for (MenuItems item : selectedRestaurant.getRestaurantMenu().getMenuItems()) {
                    itemDropdown.addItem(item.getItemName()); // Ensure this uses getItemName()
                }
            }
        }
    });
    

    updateButton.addActionListener(e -> {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            Restaurant selectedRestaurant = restaurants.stream()
                    .filter(r -> (r.getRestaurantName() + " - " + r.getRestaurantLocation()).equals(selectedRestaurantInfo))
                    .findFirst()
                    .orElse(null);

            if (selectedRestaurant != null) {
                String selectedItemName = (String) itemDropdown.getSelectedItem();
                MenuItems menuItem = selectedRestaurant.findMenuItem(selectedItemName);

                if (menuItem != null) {
                    String newDescription = newDescriptionField.getText().trim();
                    String newCategory = newCategoryField.getText().trim();

                    if (!newDescription.isEmpty() && !newCategory.isEmpty() && !newPriceField.getText().isEmpty()) {
                        try {
                            double newPrice = Double.parseDouble(newPriceField.getText().trim());

                            selectedRestaurant.updateMenuItem(selectedItemName, newDescription, newPrice, newCategory);
                            JOptionPane.showMessageDialog(this, "Menu item updated successfully!");
                            returnToOwnerMenu();

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Menu item not found.");
                }
            }
        }
    });

    backButton.addActionListener(e -> returnToOwnerMenu());

    setPanelContent(updateMenuItemPanel);
}


// EFFECTS: lets the user remove a menu item from a specific restaurant
private void removeMenuItem() {
    if (restaurants.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No restaurants available. Please add a restaurant first.");
        return;
    }

    JPanel removeMenuItemPanel = new JPanel(new BorderLayout(15, 15));
    removeMenuItemPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    removeMenuItemPanel.setBackground(new Color(245, 202, 195));

    // Panel for fields
    JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 15, 15));
    fieldsPanel.setBackground(new Color(245, 202, 195));

    // Restaurant selection dropdown
    JLabel selectRestaurantLabel = new JLabel("Select Restaurant:");
    selectRestaurantLabel.setFont(new Font("Arial", Font.BOLD, 14));
    selectRestaurantLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JComboBox<String> restaurantDropdown = new JComboBox<>();
    for (Restaurant restaurant : restaurants) {
        restaurantDropdown.addItem(restaurant.getRestaurantName() + " - " + restaurant.getRestaurantLocation());
    }

    // Menu item dropdown (initially empty, will be populated based on the selected restaurant)
    JLabel itemNameLabel = new JLabel("Select Menu Item to Remove:");
    JComboBox<String> itemDropdown = new JComboBox<>();
    itemNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
    itemNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Add restaurant selection and menu item fields to the panel
    fieldsPanel.add(selectRestaurantLabel);
    fieldsPanel.add(restaurantDropdown);
    fieldsPanel.add(itemNameLabel);
    fieldsPanel.add(itemDropdown);

    JPanel buttonPanel = createOptionButtons("Remove MenuItem", "Back");

    removeMenuItemPanel.add(fieldsPanel, BorderLayout.CENTER);
    removeMenuItemPanel.add(buttonPanel, BorderLayout.SOUTH);

    JButton removeButton = (JButton) buttonPanel.getComponent(0);
    JButton backButton = (JButton) buttonPanel.getComponent(1);

    // Populate the menu item dropdown based on selected restaurant
    restaurantDropdown.addActionListener(e -> {
        itemDropdown.removeAllItems();
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            // Find the selected restaurant based on the dropdown choice
            Restaurant selectedRestaurant = restaurants.stream()
                    .filter(r -> (r.getRestaurantName() + " - " + r.getRestaurantLocation()).equals(selectedRestaurantInfo))
                    .findFirst()
                    .orElse(null);

            if (selectedRestaurant != null) {
                // Populate the item dropdown with the menu items' names from the selected restaurant
                for (MenuItems item : selectedRestaurant.getRestaurantMenu().getMenuItems()) {
                    itemDropdown.addItem(item.getItemName()); // Ensure this uses getItemName()
                }
            }
        }
    });

    // Action for removing a selected menu item
    removeButton.addActionListener(e -> {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            Restaurant selectedRestaurant = restaurants.stream()
                    .filter(r -> (r.getRestaurantName() + " - " + r.getRestaurantLocation()).equals(selectedRestaurantInfo))
                    .findFirst()
                    .orElse(null);

            if (selectedRestaurant != null) {
                String selectedItemName = (String) itemDropdown.getSelectedItem();

                if (selectedItemName != null && !selectedItemName.isEmpty()) {
                    MenuItems menuItem = selectedRestaurant.findMenuItem(selectedItemName);

                    if (menuItem != null) {
                        selectedRestaurant.removeMenuItem(selectedItemName);
                        JOptionPane.showMessageDialog(this, "Menu item removed successfully!");
                        returnToOwnerMenu();
                    } else {
                        JOptionPane.showMessageDialog(this, "Menu item not found.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a menu item.");
                }
            }
        }
    });

    backButton.addActionListener(e -> returnToOwnerMenu());

    setPanelContent(removeMenuItemPanel);
}


    private void setPanelContent(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        validate();
        revalidate();
        repaint();
    }

    /*
 * EFFECTS: Lets the user read reviews for the given restaurant
 */
private void readReviews() {
    if (restaurants.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No restaurants available. Please add a restaurant first.");
        return;
    }

    JPanel readReviewsPanel = new JPanel(new BorderLayout(15, 15));
    readReviewsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    readReviewsPanel.setBackground(new Color(245, 202, 195));

    // Restaurant selection dropdown
    JLabel selectRestaurantLabel = new JLabel("Select Restaurant:");
    selectRestaurantLabel.setFont(new Font("Arial", Font.BOLD, 14));
    selectRestaurantLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JComboBox<String> restaurantDropdown = new JComboBox<>();
    for (Restaurant restaurant : restaurants) {
        restaurantDropdown.addItem(restaurant.getRestaurantName() + " - " + restaurant.getRestaurantLocation());
    }

    JPanel fieldsPanel = new JPanel(new GridLayout(1, 2, 15, 15));
    fieldsPanel.setBackground(new Color(245, 202, 195));

    fieldsPanel.add(selectRestaurantLabel);
    fieldsPanel.add(restaurantDropdown);

    JPanel buttonPanel = createOptionButtons("View Reviews", "Back");

    readReviewsPanel.add(fieldsPanel, BorderLayout.CENTER);
    readReviewsPanel.add(buttonPanel, BorderLayout.SOUTH);

    JButton viewReviewsButton = (JButton) buttonPanel.getComponent(0);
    JButton backButton = (JButton) buttonPanel.getComponent(1);

    viewReviewsButton.addActionListener(e -> {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            Restaurant selectedRestaurant = restaurants.stream()
                    .filter(r -> (r.getRestaurantName() + " - " + r.getRestaurantLocation()).equals(selectedRestaurantInfo))
                    .findFirst()
                    .orElse(null);

            if (selectedRestaurant != null) {
                if (selectedRestaurant.getRestaurantReviews().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No reviews available for this restaurant.");
                } else {
                    StringBuilder reviewsList = new StringBuilder();
                    for (Review review : selectedRestaurant.getRestaurantReviews()) {
                        reviewsList.append("Customer: ").append(review.getCustomer().getName())
                                .append(", Rating: ").append(review.getRating())
                                .append("\nComment: ").append(review.getReviewComment()).append("\n\n");
                    }

                    JTextArea textArea = new JTextArea(10, 30);
                    textArea.setText(reviewsList.toString());
                    textArea.setEditable(false);

                    JScrollPane scrollPane = new JScrollPane(textArea);

                    JOptionPane.showMessageDialog(this, scrollPane, "Reviews for " + selectedRestaurant.getRestaurantName(),
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    });

    backButton.addActionListener(e -> returnToOwnerMenu());

    setPanelContent(readReviewsPanel);
}

// EFFECTS: Lets the user make a reservation at a specific restaurant
private void makeReservation() {
    if (restaurants.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No restaurants available. Please add a restaurant first.");
        return;
    }

    JPanel reservationPanel = new JPanel(new BorderLayout(15, 15));
    reservationPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    reservationPanel.setBackground(new Color(245, 202, 195));

    // Fields for making a reservation, including the restaurant dropdown
    JPanel fieldsPanel = new JPanel(new GridLayout(6, 2, 15, 15));
    fieldsPanel.setBackground(new Color(245, 202, 195));

    // Restaurant selection dropdown
    JLabel selectRestaurantLabel = new JLabel("Select Restaurant:");
    selectRestaurantLabel.setFont(new Font("Arial", Font.BOLD, 14));
    selectRestaurantLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JComboBox<String> restaurantDropdown = new JComboBox<>();
    for (Restaurant restaurant : restaurants) {
        restaurantDropdown.addItem(restaurant.getRestaurantName() + " - " + restaurant.getRestaurantLocation());
    }

    // Fields for customer name, email, and reservation details
    JTextField nameField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField dateField = new JTextField();
    JTextField timeField = new JTextField();
    JTextField guestsField = new JTextField();

    JLabel nameLabel = new JLabel("Customer Name:");
    JLabel emailLabel = new JLabel("Customer Email:");
    JLabel dateLabel = new JLabel("Reservation Date (YYYY-MM-DD):");
    JLabel timeLabel = new JLabel("Reservation Time (HH:MM):");
    JLabel guestsLabel = new JLabel("Number of Guests:");

    nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
    emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
    dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
    timeLabel.setFont(new Font("Arial", Font.BOLD, 14));
    guestsLabel.setFont(new Font("Arial", Font.BOLD, 14));

    nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
    dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    guestsLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Add the restaurant selection and other fields to the panel
    fieldsPanel.add(selectRestaurantLabel);
    fieldsPanel.add(restaurantDropdown);
    fieldsPanel.add(nameLabel);
    fieldsPanel.add(nameField);
    fieldsPanel.add(emailLabel);
    fieldsPanel.add(emailField);
    fieldsPanel.add(dateLabel);
    fieldsPanel.add(dateField);
    fieldsPanel.add(timeLabel);
    fieldsPanel.add(timeField);
    fieldsPanel.add(guestsLabel);
    fieldsPanel.add(guestsField);

    JPanel buttonPanel = createOptionButtons("Make Reservation", "Back");

    reservationPanel.add(fieldsPanel, BorderLayout.CENTER);
    reservationPanel.add(buttonPanel, BorderLayout.SOUTH);

    JButton reserveButton = (JButton) buttonPanel.getComponent(0);
    JButton backButton = (JButton) buttonPanel.getComponent(1);

    reserveButton.addActionListener(e -> {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            Restaurant selectedRestaurant = restaurants.stream()
                    .filter(r -> (r.getRestaurantName() + " - " + r.getRestaurantLocation()).equals(selectedRestaurantInfo))
                    .findFirst()
                    .orElse(null);

            if (selectedRestaurant != null) {
                String customerName = nameField.getText().trim();
                String customerEmail = emailField.getText().trim();
                String date = dateField.getText().trim();
                String time = timeField.getText().trim();
                String guestsText = guestsField.getText().trim();

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
                    selectedRestaurant.addReservation(reservation);
                    JOptionPane.showMessageDialog(this, "Reservation made successfully for " + customerName);
                    returnToCustomerMenu();

                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid date or time format.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid number of guests.");
                }
            }
        }
    });

    backButton.addActionListener(e -> returnToCustomerMenu());

    setPanelContent(reservationPanel);
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

    JPanel orderPanel = new JPanel(new BorderLayout(15, 15));
    orderPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    orderPanel.setBackground(new Color(245, 202, 195));

    JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
    fieldsPanel.setBackground(new Color(245, 202, 195));

    JTextField customerNameField = new JTextField();
    JTextField customerEmailField = new JTextField();

    JComboBox<String> menuComboBox = new JComboBox<>();
    for (MenuItems item : restaurant.getRestaurantMenu().getMenuItems()) {
        menuComboBox.addItem(item.getItemName());
    }

    // Labels for fields
    JLabel customerNameLabel = new JLabel("Customer Name:");
    JLabel customerEmailLabel = new JLabel("Customer Email:");
    JLabel menuLabel = new JLabel("Select Menu Item:");
    
    // Set the font and alignment for the labels
    customerNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
    customerEmailLabel.setFont(new Font("Arial", Font.BOLD, 14));
    menuLabel.setFont(new Font("Arial", Font.BOLD, 14));
    
    customerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    customerEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
    menuLabel.setHorizontalAlignment(SwingConstants.CENTER);

    fieldsPanel.add(customerNameLabel);
    fieldsPanel.add(customerNameField);
    fieldsPanel.add(customerEmailLabel);
    fieldsPanel.add(customerEmailField);
    fieldsPanel.add(menuLabel);
    fieldsPanel.add(menuComboBox);

    // Buttons for adding items, placing the order, and going back
    JButton addButton = new JButton("Add Item");
    JButton placeOrderButton = new JButton("Place Order");
    JButton backButton = new JButton("Back");

    fieldsPanel.add(addButton);
    fieldsPanel.add(placeOrderButton);
    fieldsPanel.add(backButton);

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
            JOptionPane.showMessageDialog(this, "Order placed successfully!");
            returnToCustomerMenu();
        } else {
            JOptionPane.showMessageDialog(this, "Please complete all fields and add items to the order.");
        }
    });

    backButton.addActionListener(e -> returnToCustomerMenu());

    orderPanel.add(fieldsPanel, BorderLayout.CENTER);

    // Set the content pane to the order panel
    setContentPane(orderPanel);
    revalidate();
}

    /*
 * REQUIRES: restaurant != null
 * EFFECTS: lets customer leave a review for the restaurant using user input
 */
private void leaveReview() {
    if (restaurants.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No restaurants available. Please add a restaurant first.");
        return;
    }

    JPanel reviewPanel = new JPanel(new BorderLayout(15, 15));
    reviewPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    reviewPanel.setBackground(new Color(245, 202, 195));

    // Fields for leaving a review, including the restaurant dropdown
    JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 15, 15));
    fieldsPanel.setBackground(new Color(245, 202, 195));

    // Restaurant selection dropdown
    JLabel selectRestaurantLabel = new JLabel("Select Restaurant:");
    selectRestaurantLabel.setFont(new Font("Arial", Font.BOLD, 14));
    selectRestaurantLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JComboBox<String> restaurantDropdown = new JComboBox<>();
    for (Restaurant restaurant : restaurants) {
        restaurantDropdown.addItem(restaurant.getRestaurantName() + " - " + restaurant.getRestaurantLocation());
    }

    // Fields for customer review input
    JTextField customerNameField = new JTextField();
    JTextField customerEmailField = new JTextField();
    JTextField commentField = new JTextField();
    JTextField Field = new JTextField();

    JLabel customerNameLabel = new JLabel("Customer Name:");
    JLabel customerEmailLabel = new JLabel("Customer Email:");
    JLabel commentLabel = new JLabel("Comment:");
    
    customerNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
    customerEmailLabel.setFont(new Font("Arial", Font.BOLD, 14));
    commentLabel.setFont(new Font("Arial", Font.BOLD, 14));

    customerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    customerEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
    commentLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Rating selection dropdown
    String[] ratings = { "1", "2", "3", "4", "5" };
    JComboBox<String> ratingComboBox = new JComboBox<>(ratings);

    // Add the restaurant selection and review fields to the panel
    fieldsPanel.add(selectRestaurantLabel);
    fieldsPanel.add(restaurantDropdown);
    fieldsPanel.add(customerNameLabel);
    fieldsPanel.add(customerNameField);
    fieldsPanel.add(customerEmailLabel);
    fieldsPanel.add(customerEmailField);
    fieldsPanel.add(commentLabel);
    fieldsPanel.add(commentField);
    JLabel ratingLabel = new JLabel("Rating (1-5):");
ratingLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Set the font to bold
ratingLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label
fieldsPanel.add(ratingLabel);
    fieldsPanel.add(ratingComboBox);

    JPanel buttonPanel = createOptionButtons("Submit Review", "Back");

    reviewPanel.add(fieldsPanel, BorderLayout.CENTER);
    reviewPanel.add(buttonPanel, BorderLayout.SOUTH);

    JButton submitButton = (JButton) buttonPanel.getComponent(0);
    JButton backButton = (JButton) buttonPanel.getComponent(1);

    submitButton.addActionListener(e -> {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            Restaurant selectedRestaurant = restaurants.stream()
                    .filter(r -> (r.getRestaurantName() + " - " + r.getRestaurantLocation()).equals(selectedRestaurantInfo))
                    .findFirst()
                    .orElse(null);

            if (selectedRestaurant != null) {
                String customerName = customerNameField.getText();
                String customerEmail = customerEmailField.getText();
                String comment = commentField.getText();
                String ratingText = (String) ratingComboBox.getSelectedItem();

                if (!customerName.isEmpty() && !customerEmail.isEmpty() && !comment.isEmpty() && ratingText != null) {
                    try {
                        int rating = Integer.parseInt(ratingText);

                        if (rating >= 1 && rating <= 5) {
                            Customer customer = new Customer(customerName, customerEmail);
                            Review review = new Review(customer, comment, rating);
                            selectedRestaurant.addReview(review);
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
            }
        }
    });

    backButton.addActionListener(e -> returnToCustomerMenu());

    setPanelContent(reviewPanel);
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
            jsonWriter.open();
            jsonWriter.write(restaurants);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Data saved successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No savable changes have been made");
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

    // MODIFIES: button
    // EFFECTS: Sets the background color, text color, and font style for a button
    private void styleButton(JButton button, Color backgroundColor, Color textColor) {
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setOpaque(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ERestaurantManagerGUI());
    }

}
