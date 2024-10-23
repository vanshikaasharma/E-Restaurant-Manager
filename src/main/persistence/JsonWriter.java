package persistence;

import model.Restaurant;
import model.MenuItems;
import model.Review;
import model.Reservation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;;

// Represents a writer that writes JSON representation of restaurants to a file
public class JsonWriter {
    
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    // cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        //STUB
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of the restaurant to file
    public void write(ArrayList<Restaurant> restaurants) {
        //STUB
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        //STUB
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
       //STUB
    }

    // EFFECTS: converts restaurant to JSON object
    private JSONObject restaurantToJson(Restaurant restaurant) {
        return null;
    }
}
