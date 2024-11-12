package ui;

import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // try {
        //     new ERestaurantManager();
        // } catch (FileNotFoundException e) {
        //     System.out.println("Unable to run application: file not found");
        // }
        SwingUtilities.invokeLater(() -> new ERestaurantManagerGUI());
    }
}
