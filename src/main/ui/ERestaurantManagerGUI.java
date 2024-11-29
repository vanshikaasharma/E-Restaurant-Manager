package ui;

import javax.swing.*;

import model.Customer;
import model.EventLog;
import model.Event;
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
    private boolean isOwnerModeActive = false;
    private boolean isCustomerModeActive = false;

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

    // MODIFIES: this
    //EFFECTS: Creates the main menu panel with options for owner, customer, load,
    // and exit
    @SuppressWarnings("methodlength")
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
        exitButton.addActionListener(e -> {
            EventLog eventLog= EventLog.getInstance();
            printEventLog(eventLog);
            System.exit(0);
        });

        setPanelContent(mainPanel);
    }

    //EFFECTS: Prints the event log on console
    private void printEventLog(EventLog eventLog){
        for (Event e: eventLog){
            System.out.println(e);
            System.out.println(" ");
        }
    }

    // MODIFIES: thiso
    //EFFECTS: Displays the owner's options such as adding restaurants, 
    //updating/removing menu items, reading reviews, and saving changes.
    @SuppressWarnings("methodlength")
    private void handleOwnerOptions() {
        isOwnerModeActive = true;
        isCustomerModeActive = false;
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

    /// MODIFIES: this
    //EFFECTS:Shows the customer's options, such as making 
    //reservations, placing orders, leaving reviews, and viewing restaurants
    @SuppressWarnings("methodlength")
    private void handleCustomerOptions() {
        isOwnerModeActive = false;
        isCustomerModeActive = true;
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

    // EFFECTS: Creates a row with three buttons with consistent sizes
    private JPanel createOptionButtons(String button1Text, String button2Text, String button3Text) {
        JPanel rowPanel = new JPanel(new GridLayout(1, 3, 15, 15)); // Use 1 row and 3 columns
        JButton button1 = createSizedButton(button1Text);
        JButton button2 = createSizedButton(button2Text);
        JButton button3 = createSizedButton(button3Text);

        rowPanel.setBackground(new Color(245, 202, 195));

        styleButton(button1, new Color(246, 189, 96), new Color(0, 0, 0));
        styleButton(button2, new Color(242, 132, 130), new Color(255, 255, 255));
        styleButton(button3, new Color(132, 165, 157), new Color(255, 255, 255));

        rowPanel.add(button1);
        rowPanel.add(button2);
        rowPanel.add(button3);

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

    // EFFECTS: creates and returns a JPanel with a grid layout for fields input
    private JPanel createRestaurantPanel() {
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        fieldsPanel.setBackground(new Color(245, 202, 195));
        return fieldsPanel;
    }

    // REQUIRES: name, location, and cuisine fields are non-empty
    // MODIFIES: this
    // EFFECTS: adds a new restaurant using user input if it doesn't already exist
    private void addRestaurant() {
        JPanel panel = createInitialPanel();
        JPanel fields = createRestaurantPanel();

        JTextField nameField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField cuisineField = new JTextField();

        addFields(fields, nameField, locationField, cuisineField);
        JPanel buttons = createOptionButtons("Add Restaurant", "Back");

        panel.add(fields, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        JButton addButton = (JButton) buttons.getComponent(0);
        JButton backButton = (JButton) buttons.getComponent(1);

        addButton.addActionListener(e -> handleAddRestaurant(nameField, locationField, cuisineField));
        backButton.addActionListener(e -> handleOwnerOptions());

        setPanelContent(panel);
    }

    // EFFECTS: initializes and adds text fields to the fields panel with bold and
    // centered labels
    private void addFields(JPanel fields, JTextField nameField, JTextField locationField, JTextField cuisineField) {
        fields.add(createBoldCenteredLabel("Restaurant Name:"));
        fields.add(nameField);
        fields.add(createBoldCenteredLabel("Location:"));
        fields.add(locationField);
        fields.add(createBoldCenteredLabel("Cuisine Type:"));
        fields.add(cuisineField);
    }

    // EFFECTS: creates a JLabel with bold text and center alignment
    private JLabel createBoldCenteredLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14)); // Set font to bold
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
        return label;
    }

    // EFFECTS: attempts to add a new restaurant; shows messages if successful or if
    // it already exists
    private void handleAddRestaurant(JTextField nameField, JTextField locationField, JTextField cuisineField) {
        String name = nameField.getText().trim();
        String location = locationField.getText().trim();
        String cuisine = cuisineField.getText().trim();

        if (isInputValid(name, location, cuisine)) {
            Restaurant restaurant = new Restaurant(name, location, cuisine);
            if (restaurants.add(restaurant)) {
                JOptionPane.showMessageDialog(this, "Restaurant added: " + name);
                returnToOwnerMenu();
            } else {
                JOptionPane.showMessageDialog(this, "Restaurant already exists.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        }
    }

    // EFFECTS: checks if name, location, and cuisine are non-empty
    private boolean isInputValid(String name, String location, String cuisine) {
        return !name.isEmpty() && !location.isEmpty() && !cuisine.isEmpty();
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

    // EFFECTS: Creates and returns a dropdown containing a list of restaurants
    private JComboBox<String> createRestaurantDropdown() {
        JComboBox<String> restaurantDropdown = new JComboBox<>();
        for (Restaurant restaurant : restaurants) {
            restaurantDropdown.addItem(restaurant.getRestaurantName() + " - " + restaurant.getRestaurantLocation());
        }
        return restaurantDropdown;
    }

    // EFFECTS: Lets the owner add menu items to a specific restaurant
    private void addMenuItem() {
        checkIfRestaurant();

        JPanel panel = createInitialPanel();
        JPanel fieldsPanel = createMenuItemFieldsPanel();

        JTextField itemNameField = new JTextField();
        JTextField itemCategoryField = new JTextField();
        JTextField itemDescriptionField = new JTextField();
        JTextField itemPriceField = new JTextField();

        // Restaurant selection dropdown
        JComboBox<String> restaurantDropdown = createRestaurantDropdown();

        addMenuItemFields(fieldsPanel, restaurantDropdown, itemNameField, itemCategoryField, itemDescriptionField,
                itemPriceField);
        JPanel buttons = createOptionButtons("Add MenuItem", "Back");

        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        JButton addButton = (JButton) buttons.getComponent(0);
        JButton backButton = (JButton) buttons.getComponent(1);

        addButton.addActionListener(e -> handleAddMenuItem(restaurantDropdown, itemNameField, itemCategoryField,
                itemDescriptionField, itemPriceField));
        backButton.addActionListener(e -> returnToOwnerMenu());

        setPanelContent(panel);
    }

    // EFFECTS: creates and returns a JPanel for menu item fields with a grid layout
    private JPanel createMenuItemFieldsPanel() {
        JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 15, 15));
        fieldsPanel.setBackground(new Color(245, 202, 195));
        return fieldsPanel;
    }

    // EFFECTS: initializes and adds fields for adding a menu item to the fields
    // panel
    private void addMenuItemFields(JPanel fieldsPanel, JComboBox<String> restaurantDropdown, JTextField itemNameField,
            JTextField itemCategoryField, JTextField itemDescriptionField, JTextField itemPriceField) {
        fieldsPanel.add(createBoldCenteredLabel("Select Restaurant:"));
        fieldsPanel.add(restaurantDropdown);
        fieldsPanel.add(createBoldCenteredLabel("Menu Item Name:"));
        fieldsPanel.add(itemNameField);
        fieldsPanel.add(createBoldCenteredLabel("Menu Item Category:"));
        fieldsPanel.add(itemCategoryField);
        fieldsPanel.add(createBoldCenteredLabel("Menu Item Description:"));
        fieldsPanel.add(itemDescriptionField);
        fieldsPanel.add(createBoldCenteredLabel("Menu Item Price:"));
        fieldsPanel.add(itemPriceField);
    }

    // EFFECTS: adds fields for updating a menu item to the panel
    private void addMenuItemFields(JPanel fieldsPanel, JComboBox<String> restaurantDropdown,
            JComboBox<String> itemDropdown,
            JTextField newDescriptionField, JTextField newPriceField, JTextField newCategoryField) {
        fieldsPanel.add(createBoldCenteredLabel("Select Restaurant:"));
        fieldsPanel.add(restaurantDropdown);
        fieldsPanel.add(createBoldCenteredLabel("Select Menu Item to Update:"));
        fieldsPanel.add(itemDropdown);
        fieldsPanel.add(createBoldCenteredLabel("New Description:"));
        fieldsPanel.add(newDescriptionField);
        fieldsPanel.add(createBoldCenteredLabel("New Price:"));
        fieldsPanel.add(newPriceField);
        fieldsPanel.add(createBoldCenteredLabel("New Category:"));
        fieldsPanel.add(newCategoryField);
    }

    // EFFECTS: handles the addition of a menu item to the selected restaurant
    private void handleAddMenuItem(JComboBox<String> restaurantDropdown, JTextField itemNameField,
            JTextField itemCategoryField, JTextField itemDescriptionField,
            JTextField itemPriceField) {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        Restaurant selectedRestaurant = getRestaurantFromDropdown(selectedRestaurantInfo);

        if (selectedRestaurant != null
                && areMenuFieldsValid(itemNameField, itemCategoryField, itemDescriptionField, itemPriceField)) {
            try {
                double itemPrice = Double.parseDouble(itemPriceField.getText().trim());
                selectedRestaurant.addMenuItem(itemNameField.getText().trim(), itemDescriptionField.getText().trim(),
                        itemPrice, itemCategoryField.getText().trim());
                JOptionPane.showMessageDialog(this, "Menu item added successfully!");
                returnToOwnerMenu();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price format. Please enter a valid number.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        }
    }

    // EFFECTS: checks if all fields are non-empty and valid
    private boolean areMenuFieldsValid(JTextField itemNameField, JTextField itemCategoryField,
            JTextField itemDescriptionField,
            JTextField itemPriceField) {
        return !itemNameField.getText().trim().isEmpty()
                && !itemCategoryField.getText().trim().isEmpty()
                && !itemDescriptionField.getText().trim().isEmpty()
                && !itemPriceField.getText().trim().isEmpty();
    }

    // EFFECTS: checks if all fields are non-empty and valid for updating
    private boolean areMenuFieldsValid(JTextField newDescriptionField, JTextField newCategoryField,
            JTextField newPriceField) {
        return !newDescriptionField.getText().trim().isEmpty()
                && !newCategoryField.getText().trim().isEmpty()
                && !newPriceField.getText().trim().isEmpty();
    }

    // EFFECTS: Lets the user update a menu item of a specific restaurant
    @SuppressWarnings("methodlength")
    private void updateMenuItem() {
        checkIfRestaurant();

        JPanel panel = createInitialPanel();
        JPanel fieldsPanel = createMenuItemFieldsPanel();

        JComboBox<String> restaurantDropdown = createRestaurantDropdown();
        JComboBox<String> itemDropdown = new JComboBox<>();
        JTextField newDescriptionField = new JTextField();
        JTextField newPriceField = new JTextField();
        JTextField newCategoryField = new JTextField();

        addMenuItemFields(fieldsPanel, restaurantDropdown, itemDropdown, newDescriptionField, newPriceField,
                newCategoryField);
        JPanel buttons = createOptionButtons("Update MenuItem", "Back");

        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        JButton updateButton = (JButton) buttons.getComponent(0);
        JButton backButton = (JButton) buttons.getComponent(1);

        restaurantDropdown.addActionListener(e -> {
            itemDropdown.removeAllItems();
            String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
            if (selectedRestaurantInfo != null) {
                Restaurant selectedRestaurant = getRestaurantFromDropdown(selectedRestaurantInfo);
                if (selectedRestaurant != null) {
                    for (MenuItems item : selectedRestaurant.getRestaurantMenu().getMenuItems()) {
                        itemDropdown.addItem(item.getItemName());
                    }
                }
            }
        });

        updateButton.addActionListener(e -> {
            String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
            if (selectedRestaurantInfo != null) {
                Restaurant selectedRestaurant = getRestaurantFromDropdown(selectedRestaurantInfo);
                if (selectedRestaurant != null) {
                    String selectedItemName = (String) itemDropdown.getSelectedItem();
                    MenuItems menuItem = selectedRestaurant.findMenuItem(selectedItemName);

                    if (menuItem != null && areMenuFieldsValid(newDescriptionField, newCategoryField, newPriceField)) {
                        try {
                            double newPrice = Double.parseDouble(newPriceField.getText().trim());
                            selectedRestaurant.updateMenuItem(selectedItemName, newDescriptionField.getText().trim(),
                                    newPrice, newCategoryField.getText().trim());
                            JOptionPane.showMessageDialog(this, "Menu item updated successfully!");
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

        setPanelContent(panel);
    }

    // EFFECTS: Lets the user remove a menu item from a specific restaurant
    private void removeMenuItem() {
        checkIfRestaurant();

        JPanel panel = createInitialPanel();
        JPanel fieldsPanel = createMenuItemFieldsPanel();

        JComboBox<String> restaurantDropdown = createRestaurantDropdown();
        JComboBox<String> itemDropdown = new JComboBox<>();

        addRemoveMenuItemFields(fieldsPanel, restaurantDropdown, itemDropdown);
        JPanel buttons = createOptionButtons("Remove MenuItem", "Back");

        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        JButton removeButton = (JButton) buttons.getComponent(0);
        JButton backButton = (JButton) buttons.getComponent(1);

        restaurantDropdown.addActionListener(e -> populateMenuItemsDropdown(restaurantDropdown, itemDropdown));

        removeButton.addActionListener(e -> handleRemoveMenuItem(restaurantDropdown, itemDropdown));
        backButton.addActionListener(e -> returnToOwnerMenu());

        setPanelContent(panel);
    }

    // EFFECTS: initializes and adds fields for removing a menu item
    private void addRemoveMenuItemFields(JPanel fieldsPanel, JComboBox<String> restaurantDropdown,
            JComboBox<String> itemDropdown) {
        fieldsPanel.add(createBoldCenteredLabel("Select Restaurant:"));
        fieldsPanel.add(restaurantDropdown);
        fieldsPanel.add(createBoldCenteredLabel("Select Menu Item to Remove:"));
        fieldsPanel.add(itemDropdown);
    }

    // EFFECTS: populates the menu item dropdown based on the selected restaurant
    private void populateMenuItemsDropdown(JComboBox<String> restaurantDropdown, JComboBox<String> itemDropdown) {
        itemDropdown.removeAllItems();
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            Restaurant selectedRestaurant = getRestaurantFromDropdown(selectedRestaurantInfo);
            if (selectedRestaurant != null) {
                for (MenuItems item : selectedRestaurant.getRestaurantMenu().getMenuItems()) {
                    itemDropdown.addItem(item.getItemName());
                }
            }
        }
    }

    // EFFECTS: handles the removal of the selected menu item
    private void handleRemoveMenuItem(JComboBox<String> restaurantDropdown, JComboBox<String> itemDropdown) {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        Restaurant selectedRestaurant = getRestaurantFromDropdown(selectedRestaurantInfo);

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
    
//EFFECTS: Addes a specific panel by removing all its contents
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
        checkIfRestaurant();

        JPanel readReviewsPanel = createInitialPanel();
        JComboBox<String> restaurantDropdown = createRestaurantDropdown();

        JPanel fieldsPanel = createFieldsPanel("Select Restaurant:", restaurantDropdown);
        JPanel buttonPanel = createOptionButtons("View Reviews", "Back");

        readReviewsPanel.add(fieldsPanel, BorderLayout.CENTER);
        readReviewsPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton viewReviewsButton = (JButton) buttonPanel.getComponent(0);
        JButton backButton = (JButton) buttonPanel.getComponent(1);

        viewReviewsButton.addActionListener(e -> handleViewReviews(restaurantDropdown));
        backButton.addActionListener(e -> returnToPreviousMenu());

        setPanelContent(readReviewsPanel);
    }

    // EFFECTS: Returns the user to the correct menu based on where they accessed
    // 'readReviews' from
    private void returnToPreviousMenu() {
        if (isAccessedFromOwnerOptions()) {
            returnToOwnerMenu();
        } else if (isAccessedFromCustomerOptions()) {
            returnToCustomerMenu();
        } else {
            returnToMainMenu();
        }
    }

    //EFFECTS: Returns true if the current mode is owner mode, based 
    //on the value of the isOwnerModeActive flag.
    private boolean isAccessedFromOwnerOptions() {
        return isOwnerModeActive;
    }

    //EFFECTS: Returns true if the current mode is customer mode, based 
    //on the value of the isCustomerModeActive flag.
    private boolean isAccessedFromCustomerOptions() {
        return isCustomerModeActive; 
    }

    /*
    * MODIFIES: this
     * EFFECTS: Creates and returns a panel containing a label and a dropdown field
     * for restaurant selection
     */
    private JPanel createFieldsPanel(String labelText, JComboBox<String> dropdown) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new GridLayout(1, 2, 15, 15));
        panel.setBackground(new Color(245, 202, 195));
        panel.add(label);
        panel.add(dropdown);

        return panel;
    }

    /*
     * EFFECTS: Handles the action of viewing reviews for a selected restaurant
     * MODIFIES: this
     */
    private void handleViewReviews(JComboBox<String> restaurantDropdown) {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            Restaurant selectedRestaurant = getRestaurantFromDropdown(selectedRestaurantInfo);

            if (selectedRestaurant != null) {
                displayReviews(selectedRestaurant);
            }
        }
    }

    /*
     * EFFECTS: Displays reviews of the given restaurant in a dialog box.
     */
    private void displayReviews(Restaurant selectedRestaurant) {
        if (selectedRestaurant.getRestaurantReviews().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No reviews available for this restaurant.");
        } else {
            String reviewsText = selectedRestaurant.getRestaurantReviews().stream()
                    .map(this::formatReview)
                    .reduce("", (a, b) -> a + b + "\n\n");

            JTextArea textArea = new JTextArea(10, 30);
            textArea.setText(reviewsText);
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(this, scrollPane,
                    "Reviews for " + selectedRestaurant.getRestaurantName(),
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*
     * EFFECTS: Formats a single review into a readable string format
     */
    private String formatReview(Review review) {
        return "Customer: " + review.getCustomer().getName()
                + ", Rating: " + review.getRating()
                + "\nComment: " + review.getReviewComment();
    }

    // EFFECTS: checks if there is a restaurant
    private void checkIfRestaurant() {
        if (restaurants.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No restaurants available. Please add a restaurant first.");
            return;
        }
    }

    // EFFECTS: Lets the user make a reservation at a specific restaurant
    private void makeReservation() {
        checkIfRestaurant();

        JPanel panel = createInitialPanel();
        JPanel fieldsPanel = createReservationFields();

        JComboBox<String> restaurantDropdown = createRestaurantDropdown();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();
        JTextField guestsField = new JTextField();

        addReservationFields(fieldsPanel, restaurantDropdown, nameField, emailField, dateField, timeField, guestsField);
        JPanel buttons = createOptionButtons("Make Reservation", "Back");

        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        JButton reserveButton = (JButton) buttons.getComponent(0);
        JButton backButton = (JButton) buttons.getComponent(1);

        reserveButton.addActionListener(
                e -> handleReservation(restaurantDropdown, nameField, emailField, dateField, timeField, guestsField));
        backButton.addActionListener(e -> returnToCustomerMenu());

        setPanelContent(panel);
    }

    // EFFECTS: Creates and returns a JPanel for reservation fields
    private JPanel createReservationFields() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 15, 15));
        panel.setBackground(new Color(245, 202, 195));
        return panel;
    }

    // MODIFIES: fieldsPanel
    // EFFECTS: Adds reservation input fields to the provided fieldsPanel
    private void addReservationFields(JPanel fieldsPanel, JComboBox<String> restaurantDropdown,
            JTextField nameField, JTextField emailField,
            JTextField dateField, JTextField timeField, JTextField guestsField) {
        fieldsPanel.add(createBoldCenteredLabel("Select Restaurant:"));
        fieldsPanel.add(restaurantDropdown);
        fieldsPanel.add(createBoldCenteredLabel("Customer Name:"));
        fieldsPanel.add(nameField);
        fieldsPanel.add(createBoldCenteredLabel("Customer Email:"));
        fieldsPanel.add(emailField);
        fieldsPanel.add(createBoldCenteredLabel("Reservation Date (YYYY-MM-DD):"));
        fieldsPanel.add(dateField);
        fieldsPanel.add(createBoldCenteredLabel("Reservation Time (HH:MM):"));
        fieldsPanel.add(timeField);
        fieldsPanel.add(createBoldCenteredLabel("Number of Guests:"));
        fieldsPanel.add(guestsField);
    }

    // EFFECTS: Handles the reservation creation based on user input
    @SuppressWarnings("methodlength")
    private void handleReservation(JComboBox<String> restaurantDropdown, JTextField nameField, JTextField emailField,
            JTextField dateField, JTextField timeField, JTextField guestsField) {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            Restaurant selectedRestaurant = getRestaurantFromDropdown(selectedRestaurantInfo);

            if (selectedRestaurant != null) {
                String customerName = nameField.getText().trim();
                String customerEmail = emailField.getText().trim();
                String date = dateField.getText().trim();
                String time = timeField.getText().trim();
                String guestsText = guestsField.getText().trim();

                if (!areReservationFieldsValid(customerName, customerEmail, date, time, guestsText)) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                    return;
                }

                try {
                    int guests = Integer.parseInt(guestsText);
                    LocalDate reservationDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
                    LocalTime reservationTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));

                    createReservation(selectedRestaurant, customerName, customerEmail, reservationDate, reservationTime,
                            guests);
                } catch (DateTimeParseException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid date, time, or number of guests format.");
                }
            }
        }
    }

    // EFFECTS: Validates the reservation input fields and returns true if all
    // fields are correctly filled, false otherwise
    private boolean areReservationFieldsValid(String name, String email, String date, String time, String guests) {
        return !name.isEmpty() && !email.isEmpty() && !date.isEmpty() && !time.isEmpty() && !guests.isEmpty();
    }

    // MODIFIES: restaurant
    // EFFECTS: Creates a new reservation and adds it to the restaurant
    private void createReservation(Restaurant restaurant, String name, String email,
            LocalDate date, LocalTime time, int guests) {
        Customer customer = new Customer(name, email);
        Reservation reservation = new Reservation(customer, date, time, guests);
        restaurant.addReservation(reservation);
        JOptionPane.showMessageDialog(this, "Reservation made successfully for " + name);
        returnToCustomerMenu();
    }

    // REQUIRES: restaurant != null
    // EFFECTS: places an order for a customer using user input via a GUI
    private void placeOrder() {
        Restaurant restaurant = checkRestaurant();
        if (restaurant == null) {
            JOptionPane.showMessageDialog(this, "Restaurant not found.");
            return;
        }

        JPanel orderPanel = createInitialPanel();
        JPanel fieldsPanel = createOrderFields(restaurant);
        JPanel buttonPanel = createOptionButtons("Add Item", "Place Order", "Back");

        orderPanel.add(fieldsPanel, BorderLayout.CENTER);
        orderPanel.add(buttonPanel, BorderLayout.SOUTH);

        setupOrderButtonActions(restaurant, fieldsPanel, buttonPanel);

        setPanelContent(orderPanel);
    }

    // EFFECTS: creates and returns the main order panel
    private JPanel createInitialPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(245, 202, 195));
        return panel;
    }

    // EFFECTS: creates and returns the panel containing fields for placing an order
    private JPanel createOrderFields(Restaurant restaurant) {
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        fieldsPanel.setBackground(new Color(245, 202, 195));

        JTextField customerNameField = new JTextField();
        JTextField customerEmailField = new JTextField();
        JComboBox<String> menuComboBox = createMenuComboBox(restaurant);

        addOrderFieldLabels(fieldsPanel, customerNameField, customerEmailField, menuComboBox);

        return fieldsPanel;
    }

    // EFFECTS: creates and returns a combo box populated with menu items from the
    // given restaurant
    private JComboBox<String> createMenuComboBox(Restaurant restaurant) {
        JComboBox<String> menuComboBox = new JComboBox<>();
        for (MenuItems item : restaurant.getRestaurantMenu().getMenuItems()) {
            menuComboBox.addItem(item.getItemName());
        }
        return menuComboBox;
    }

    // MODIFIES: fieldsPanel
    // EFFECTS: adds labels and corresponding input fields to the fieldsPanel
    private void addOrderFieldLabels(JPanel fieldsPanel, JTextField customerNameField, JTextField customerEmailField,
            JComboBox<String> menuComboBox) {
        JLabel customerNameLabel = createCenteredLabel("Customer Name:");
        JLabel customerEmailLabel = createCenteredLabel("Customer Email:");
        JLabel menuLabel = createCenteredLabel("Select Menu Item:");

        fieldsPanel.add(customerNameLabel);
        fieldsPanel.add(customerNameField);
        fieldsPanel.add(customerEmailLabel);
        fieldsPanel.add(customerEmailField);
        fieldsPanel.add(menuLabel);
        fieldsPanel.add(menuComboBox);
    }

    // EFFECTS: creates and returns a centered label with specified text
    private JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    // MODIFIES: this
    // EFFECTS: sets up button actions for adding items, placing an order, and going
    // back to the menu
    private void setupOrderButtonActions(Restaurant restaurant, JPanel fieldsPanel, JPanel buttonPanel) {
        JButton addButton = (JButton) buttonPanel.getComponent(0);
        JButton placeOrderButton = (JButton) buttonPanel.getComponent(1);
        JButton backButton = (JButton) buttonPanel.getComponent(2);

        ArrayList<MenuItems> orderItems = new ArrayList<>();
        JComboBox<String> menuComboBox = (JComboBox<String>) fieldsPanel.getComponent(5);
        JTextField customerNameField = (JTextField) fieldsPanel.getComponent(1);
        JTextField customerEmailField = (JTextField) fieldsPanel.getComponent(3);

        addButton.addActionListener(e -> handleAddItem(menuComboBox, restaurant, orderItems));
        placeOrderButton.addActionListener(
                e -> handlePlaceOrder(restaurant, orderItems, customerNameField, customerEmailField));
        backButton.addActionListener(e -> returnToCustomerMenu());
    }

    // MODIFIES: orderItems
    // EFFECTS: handles adding an item to the order
    private void handleAddItem(JComboBox<String> menuComboBox, Restaurant restaurant, ArrayList<MenuItems> orderItems) {
        String selectedItem = (String) menuComboBox.getSelectedItem();
        MenuItems menuItem = restaurant.findMenuItem(selectedItem);
        if (menuItem != null) {
            orderItems.add(menuItem);
            JOptionPane.showMessageDialog(this, selectedItem + " added to your order.");
        }
    }

    // EFFECTS: handles placing the order if all fields are valid
    private void handlePlaceOrder(Restaurant restaurant, ArrayList<MenuItems> orderItems, JTextField customerNameField,
            JTextField customerEmailField) {
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
    }

    // EFFECTS: lets customer leave a review for a specific restaurant
    private void leaveReview() {
        checkIfRestaurant();

        JPanel panel = createInitialPanel();
        JPanel fieldsPanel = createReviewFields();

        JComboBox<String> restaurantDropdown = createRestaurantDropdown();
        JTextField customerNameField = new JTextField();
        JTextField customerEmailField = new JTextField();
        JTextField commentField = new JTextField();
        JComboBox<String> ratingComboBox = createRatingComboBox();

        addReviewFields(fieldsPanel, restaurantDropdown, customerNameField, customerEmailField, commentField,
                ratingComboBox);
        JPanel buttons = createOptionButtons("Submit Review", "Back");

        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        JButton submitButton = (JButton) buttons.getComponent(0);
        JButton backButton = (JButton) buttons.getComponent(1);

        submitButton.addActionListener(e -> handleReviewSubmit(restaurantDropdown, customerNameField,
                customerEmailField, commentField, ratingComboBox));
        backButton.addActionListener(e -> returnToCustomerMenu());

        setPanelContent(panel);
    }

    // EFFECTS: creates and returns a JPanel for review fields with a grid layout
    private JPanel createReviewFields() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 15, 15));
        panel.setBackground(new Color(245, 202, 195));
        return panel;
    }

    // EFFECTS: creates and returns a JComboBox for ratings (1-5)
    private JComboBox<String> createRatingComboBox() {
        String[] ratings = { "1", "2", "3", "4", "5" };
        return new JComboBox<>(ratings);
    }

    // EFFECTS: adds all the fields to the fieldsPanel for review creation
    private void addReviewFields(JPanel fieldsPanel, JComboBox<String> restaurantDropdown, JTextField customerNameField,
            JTextField customerEmailField, JTextField commentField, JComboBox<String> ratingComboBox) {
        fieldsPanel.add(createBoldCenteredLabel("Select Restaurant:"));
        fieldsPanel.add(restaurantDropdown);
        fieldsPanel.add(createBoldCenteredLabel("Customer Name:"));
        fieldsPanel.add(customerNameField);
        fieldsPanel.add(createBoldCenteredLabel("Customer Email:"));
        fieldsPanel.add(customerEmailField);
        fieldsPanel.add(createBoldCenteredLabel("Comment:"));
        fieldsPanel.add(commentField);
        fieldsPanel.add(createBoldCenteredLabel("Rating (1-5):"));
        fieldsPanel.add(ratingComboBox);
    }

    // EFFECTS: handles the submission of the review
    private void handleReviewSubmit(JComboBox<String> restaurantDropdown, JTextField customerNameField,
            JTextField customerEmailField, JTextField commentField, JComboBox<String> ratingComboBox) {
        String selectedRestaurantInfo = (String) restaurantDropdown.getSelectedItem();
        if (selectedRestaurantInfo != null) {
            Restaurant selectedRestaurant = getRestaurantFromDropdown(selectedRestaurantInfo);

            if (selectedRestaurant != null) {
                String customerName = customerNameField.getText().trim();
                String customerEmail = customerEmailField.getText().trim();
                String comment = commentField.getText().trim();
                String ratingText = (String) ratingComboBox.getSelectedItem();

                if (validateReviewInput(customerName, customerEmail, comment, ratingText)) {
                    int rating = Integer.parseInt(ratingText);
                    addReviewToRestaurant(selectedRestaurant, customerName, customerEmail, comment, rating);
                } else {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields correctly.");
                }
            }
        }
    }

    // EFFECTS: validates the review input (checks if all fields are filled and
    // rating is valid)
    private boolean validateReviewInput(String customerName, String customerEmail, String comment, String ratingText) {
        if (customerName.isEmpty() || customerEmail.isEmpty() || comment.isEmpty() || ratingText == null) {
            return false;
        }

        try {
            int rating = Integer.parseInt(ratingText);
            return rating >= 1 && rating <= 5;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    // EFFECTS: adds the review to the selected restaurant
    private void addReviewToRestaurant(Restaurant selectedRestaurant, String customerName, String customerEmail,
            String comment, int rating) {
        Customer customer = new Customer(customerName, customerEmail);
        Review review = new Review(customer, comment, rating);
        selectedRestaurant.addReview(review);
        JOptionPane.showMessageDialog(this, "Review submitted successfully!");
        returnToCustomerMenu();
    }

    //EFFECTS: returns the restaurant from the drop down 
    private Restaurant getRestaurantFromDropdown(String selectedRestaurantInfo) {
        return restaurants.stream()
                .filter(r -> (r.getRestaurantName() + " - " + r.getRestaurantLocation())
                        .equals(selectedRestaurantInfo))
                .findFirst()
                .orElse(null);
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
