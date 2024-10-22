package persistence;

import model.Restaurant;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads about Restaurant from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
    }

    // EFFECTS: reads list of restaurants from file and returns them;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Restaurant> read() throws IOException {
        return null;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;
    }

    // EFFECTS: parses restaurants from JSON object and returns them
    private ArrayList<Restaurant> parseRestaurants(JSONObject jsonObject) {
        return null; 
    }

    // EFFECTS: parses restaurant from JSON object and returns it
    private Restaurant parseRestaurant(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: restaurant
    // EFFECTS: parses menu items from JSON object and adds them to restaurant
    private void addMenuItems(Restaurant restaurant, JSONObject jsonObject) {
        //STUB
    }

    // MODIFIES: restaurant
    // EFFECTS: parses reviews from JSON object and adds them to restaurant
    private void addReviews(Restaurant restaurant, JSONObject jsonObject) {
        //STUB
    }


}
