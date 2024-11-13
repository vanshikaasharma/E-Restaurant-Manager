package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the E Restaurant Manager GUI that manages the restaurants
public class ERestaurantManagerGUI extends JFrame {
    private JButton ownerButton;
    private JButton customerButton;
    private JButton loadButton;
    private JButton exitButton;

    public ERestaurantManagerGUI() {
        setTitle("E Restaurant Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);

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
        JOptionPane.showMessageDialog(this, "Add a restaurant...in progress...");
    }

    /*
     * EFFECTS: lets the owner add menu items to a specific restaurant
     */
    private void addMenuItem() {
        JOptionPane.showMessageDialog(this, "Add menu item...in progress...");
    }

    /*
     * EFFECTS: lets the user update a menu item of a specific restaurant
     */
    private void updateMenuItem() {
        JOptionPane.showMessageDialog(this, "Update menu item...in progress...");
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
}
