package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        //EFFECTS:Panel to hold the main layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

        //EFFECTS: Add welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the E Restaurant Manager!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(welcomeLabel);

        // Panel to hold Owner and Customer buttons side by side
        JPanel ownerCustomerPanel = new JPanel();
        ownerCustomerPanel.setLayout(new GridLayout(1, 2, 10, 10));
        
        // Initialize buttons
        ownerButton = new JButton("Restaurant Owner");
        customerButton = new JButton("Customer");
        loadButton = new JButton("Load Previous Changes");
        exitButton = new JButton("Exit");

        // Add Owner and Customer buttons to the side-by-side panel
        ownerCustomerPanel.add(ownerButton);
        ownerCustomerPanel.add(customerButton);

        // Add side-by-side panel to main panel
        mainPanel.add(ownerCustomerPanel);
        mainPanel.add(loadButton);
        mainPanel.add(exitButton);

        add(mainPanel);

        //EFFECTS: Add action listeners to buttons
        ownerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOwnerOptions();
            }
        });

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCustomerOptions();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // EFFECTS: Handles the owner options
    private void handleOwnerOptions() {
        JOptionPane.showMessageDialog(this, "Owner options would be displayed here.");
    }

    // EFFECTS: Handles the customer options
    private void handleCustomerOptions() {
        JOptionPane.showMessageDialog(this, "Customer options would be displayed here.");
    }

    // EFFECTS: Loads previous data
    private void loadData() {
        JOptionPane.showMessageDialog(this, "Loading data...");
    }
}
