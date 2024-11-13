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

        // Main panel layout
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

        ownerPanel.add(createButtonRow("Add Restaurant", "Add Menu Item"));
        ownerPanel.add(createButtonRow("Update Menu Item", "Remove Menu Item"));
        ownerPanel.add(createButtonRow("Read Reviews", "Save Changes"));

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

        customerPanel.add(createButtonRow("Make Reservation", "Place Order"));
        customerPanel.add(createButtonRow("Leave Review", "View Restarants"));
        customerPanel.add(createButtonRow("View Reviews", "Save Changes"));

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

    // EFFECTS: Return to main menu
    private void returnToMainMenu() {
        getContentPane().removeAll();
        new ERestaurantManagerGUI();  // Reload main GUI
        revalidate();
        repaint();
    }
}
