import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private Map<String, Location> locations;
    private Location currentLocation;

    public GameMap() {
        locations = new HashMap<>();
        initializeLocations(); // Set up locations and connections
    }

    // initialize locations and connections
    private void initializeLocations() {
        // Create the starting location
        Location village = new Location("Abandoned Village", "THE ABANDONED VILLAGE. Your tribe's village is now abandoned, and without the presence of people, animals are starting to take over the structures, including the island's pack of wild dogs that hover nearby...", false);
        village.addItem(new Item("wooden stick", "A sturdy stick, could be used as a weapon."));
        locations.put("The abandoned village", village);

        // Create other locations
        Location headland = new Location("The headland", "THE HEADLAND. A narrow strip of land extending out form the coastline, raised up from the sea. It has a tall and flat rock face with crevices that may be helpful for shelter and storage.", false);
        headland.addAction("build house");
        locations.put("The headland", headland);

        Location beach = new Location("The beach", "THE BEACH. The beach is home to elephant seals and is littered with whale bones and driftwood.", false);
        beach.addItem(new Item("whale bones", "Tall, curved whale bones"));
        beach.addItem(new Item("sinew", "Whale sinew, can be used to make rope or other tough material"));
        beach.addItem(new Item("driftwood", "Pieces of wood that have washed ashore"));
        locations.put("The beach", beach);

        Location reef = new Location("The reef", "THE REEF. The reef is home to many fish and aquatic creatures, including abalone which can be used for food.", false);
        reef.addItem(new Item("abalone", "Sea mollusks that can be used for food!"));
        locations.put("The reef", reef);

        Location sandspit = new Location("The sandspit", "THE SANDSPIT. A long narrow sandbar, good for launching canoes.", true);
        locations.put("The sandspit", sandspit);

        Location cliffs = new Location("The cliffs", "THE CLIFFS. Steep cliffs that extend out into the ocean. Good viewpoint.", false);
        locations.put("The cliffs", cliffs);

        // Connect locations
        connectLocations("The abandoned village", "The headland", "north");
        connectLocations("The headland", "The abandoned village", "south");
        connectLocations("The abandoned village", "The beach", "south");
        connectLocations("The beach", "The abandoned village", "north");
        connectLocations("The abandoned village", "The reef", "west");
        connectLocations("The reef", "The abandoned village", "east");
        connectLocations("The beach", "The reef", "northwest");
        connectLocations("The reef", "The beach", "southeast");
        connectLocations("The reef", "The headland", "northeast");
        connectLocations("The headland", "The reef", "southwest");
        connectLocations("The abandoned village", "The sandspit", "east");
        connectLocations("The sandspit", "The abandoned village", "west");
        connectLocations("The sandspit", "The cliffs", "north");
        connectLocations("The cliffs", "The sandspit", "south");
        connectLocations("The headland", "The cliffs", "east");
        connectLocations("The cliffs", "The headland", "west");






        // Set the starting location
        currentLocation = village;
    }

    // Add a location to the map
    public void addLocation(String name, Location location) {
        locations.put(name, location);
    }

    // Connect two locations with directional commands
    public void connectLocations(String from, String to, String direction) {
        Location fromLocation = locations.get(from);
        Location toLocation = locations.get(to);

        if (fromLocation != null && toLocation != null) {
            fromLocation.addConnection(direction, toLocation);
        }
    }

    // Get the current location
    public Location getCurrentLocation() {
        return currentLocation;
    }

    // Set the current location by name
    public void setCurrentLocation(String name) {
        currentLocation = locations.get(name);
    }

    // Move the player to a new location based on direction
    public void movePlayer(String direction) {
        Location nextLocation = currentLocation.getConnection(direction);

        if (nextLocation != null) {
            currentLocation = nextLocation;
            System.out.println("You moved " + direction + " to " + currentLocation.getDescription());
        } else {
            System.out.println("You can't go that way.");
        }
    }
}