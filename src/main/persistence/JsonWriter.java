package persistence;

import model.Restaurant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

// Represents a writer that writes JSON representation of restaurant data to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of list of restaurants to file
    public void write(ArrayList<Restaurant> restaurants) {
        JSONArray jsonArray = new JSONArray();

        // Convert each restaurant to JSON and add to JSONArray
        for (Restaurant restaurant : restaurants) {
            JSONObject jsonRestaurant = restaurant.toJson();
            jsonArray.put(jsonRestaurant);
        }

        // Create a main object to hold the list of restaurants
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("restaurants", jsonArray);

        // Save the JSON data to file
        saveToFile(jsonObject.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
