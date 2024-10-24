package persistence;

import model.Reservation;
import model.Restaurant;
import model.Review;
import model.Customer;

import java.time.LocalDate;
import java.time.LocalTime;

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
        this.source = source;
    }

    // EFFECTS: reads list of restaurants from file and returns them;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Restaurant> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRestaurants(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses restaurants from JSON object and returns them
    private ArrayList<Restaurant> parseRestaurants(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("restaurants");
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        for (Object json : jsonArray) {
            JSONObject nextRestaurant = (JSONObject) json;
            restaurants.add(parseRestaurant(nextRestaurant));
        }

        return restaurants;
    }

    // EFFECTS: parses restaurant from JSON object and returns it
    private Restaurant parseRestaurant(JSONObject jsonObject) {
        String name = jsonObject.getString("restaurantName");
        String location = jsonObject.getString("restaurantLocation");
        String cuisineType = jsonObject.getString("cuisineType");

        Restaurant restaurant = new Restaurant(name, location, cuisineType);
        addMenuItems(restaurant, jsonObject);
        addReviews(restaurant, jsonObject);
        addReservations(restaurant, jsonObject);

        return restaurant;
    }

    // MODIFIES: restaurant
    // EFFECTS: parses menu items from JSON object and adds them to restaurant
    private void addMenuItems(Restaurant restaurant, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("menuItems");

        for (Object json : jsonArray) {
            JSONObject nextMenuItem = (JSONObject) json;
            String itemName = nextMenuItem.getString("itemName");
            String itemDescription = nextMenuItem.getString("description");
            double itemPrice = nextMenuItem.getDouble("price");
            String itemCategory = nextMenuItem.getString("category");

            restaurant.addMenuItem(itemName, itemDescription, itemPrice, itemCategory);
        }
    }

    // MODIFIES: restaurant
    // EFFECTS: parses reviews from JSON object and adds them to restaurant
    private void addReviews(Restaurant restaurant, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("reviews");

        for (Object json : jsonArray) {
            JSONObject nextReview = (JSONObject) json;
            JSONObject customerJson = nextReview.getJSONObject("customer");
            Customer customer = parseCustomer(customerJson);
            String reviewComment = nextReview.getString("reviewComment");
            int rating = nextReview.getInt("rating");
            Review review = new Review(customer, reviewComment, rating);
            restaurant.addReview(review);
        }
    }

    // MODIFIES: restaurant
    // EFFECTS: parses reservations from JSON object and adds them to restaurant
    private void addReservations(Restaurant restaurant, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("reservations");

        for (Object json : jsonArray) {
            JSONObject nextReservation = (JSONObject) json;
            JSONObject customerJson = nextReservation.getJSONObject("customer"); 
            Customer customer = parseCustomer(customerJson);
            String reservationDateString = nextReservation.getString("reservationDate");
            String reservationTimeString = nextReservation.getString("reservationTime");
            int numberOfGuests = nextReservation.getInt("numberOfGuests");

            LocalDate reservationDate = LocalDate.parse(reservationDateString);
            LocalTime reservationTime = LocalTime.parse(reservationTimeString);

            Reservation reservation = new Reservation(customer, reservationDate, reservationTime, numberOfGuests);
            restaurant.addReservation(reservation);
        }
    }

    // EFFECTS: parses customer from JSON object and returns it
    private Customer parseCustomer(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String email = jsonObject.getString("email");
        // Add more fields as necessary
        return new Customer(name, email); // Assuming Customer has a constructor that takes these fields
    }

}
