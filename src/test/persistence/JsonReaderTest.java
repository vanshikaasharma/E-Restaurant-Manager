package persistence;

import model.Restaurant;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {


    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ArrayList<Restaurant> restaurants = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRestaurants() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRestaurants.json");
        try {
            ArrayList<Restaurant> restaurants = reader.read();
            assertEquals(0, restaurants.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRestaurants() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRestaurants.json");
        try {
            ArrayList<Restaurant> restaurants = reader.read();
            assertEquals(2, restaurants.size());

            //checkRestaurant("Pizza Place", "123 Main St", "Italian", restaurants.get(0));
            //checkRestaurant("Sushi Spot", "456 Elm St", "Japanese", restaurants.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
