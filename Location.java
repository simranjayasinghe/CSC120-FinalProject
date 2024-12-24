import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Location {
    private String name;
    private String description;
    private boolean hasChallenges;
    private List<Item> items;
    private List<String> actions;
    private Map<String, Location> connections; // Holds directional connections to other locations

    public Location(String name, String description, boolean hasChallenges) {
        this.name = name;
        this.description = description;
        this.hasChallenges = hasChallenges;
        this.items = new ArrayList<>();
        this.actions = new ArrayList<>();
        this.connections = new HashMap<>(); // Initialize connections
    }

    // Getters and setters for name and description
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasChallenges() {
        return hasChallenges;
    }

    // Add an item to the location
    public void addItem(Item item) {
        items.add(item);
    }

    // Add an action to the location
    public void addAction(String action) {
        actions.add(action);
    }

    // Remove an item from the location
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    // List all items in the location
    public List<Item> getItems() {
        return items;
    }

    public List<String> getActions() {
        return actions;
    }

    // Execute challenges at this location
    public void executeChallenges(Player player, HealthManager healthManager) {
        if (hasChallenges) {
            // Simulating a challenge (e.g., damage from wild animals)
            healthManager.decreaseHealth(20);  // Example: wild animal attack causes damage
            System.out.println("You encountered a challenge! You lost 20 health.");
        }
    }

    // Display available items at this location
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("No items to pick up here.");
        } else {
            System.out.println("Available items:");
            for (Item item : items) {
                System.out.println("- " + item.getName());
            }
        }
    }

    // Add a connection to another location
    public void addConnection(String direction, Location location) {
        connections.put(direction, location);
    }

    // Get the location connected in a specific direction
    public Location getConnection(String direction) {
        return connections.get(direction);
    }

    // Prompt for actions at the current location
    public boolean executeAction(String action, Player player, ProgressManager progressManager) {
        switch (action.toLowerCase()) {
            case "build house":
                // Check if the player has all required materials (whale bones, sinew, and driftwood)
                if (progressManager.hasAllMaterials()) {
                    System.out.println("You have the necessary materials to build the house.");
                    System.out.print("Would you like to build the house? (yes/no): ");
                    Scanner scanner = new Scanner(System.in);
                    String response = scanner.nextLine().trim().toLowerCase();
                    if (response.equals("yes")) {
                        buildHouse(player, progressManager);
                    } else {
                        System.out.println("You decided not to build a house.");
                    }
                    return true;  // Action executed
                } else {
                    System.out.println("You don't have the necessary materials to build the house.");
                    return false;  // Action not executed due to lack of materials
                }
            default:
                System.out.println("Unknown action.");
                return false;
        }
    }

    // Build house method
    private void buildHouse(Player player, ProgressManager progressManager) {
        System.out.println("You start building the house.");
        
        // Find the items in the inventory by name
        Item whaleBones = null;
        Item sinew = null;
        Item driftwood = null;
        
        // Check if the required items exist in the inventory
        for (Item item : player.getInventory().getItems()) {
            if (item.getName().equalsIgnoreCase("Whale Bones")) {
                whaleBones = item;
            } else if (item.getName().equalsIgnoreCase("Sinew")) {
                sinew = item;
            } else if (item.getName().equalsIgnoreCase("Driftwood")) {
                driftwood = item;
            }
        }
        
        // Remove the items from the inventory
        if (whaleBones != null) {
            player.getInventory().removeItem(whaleBones);
        }
        if (sinew != null) {
            player.getInventory().removeItem(sinew);
        }
        if (driftwood != null) {
            player.getInventory().removeItem(driftwood);
        }
    
        // Print confirmation and updated inventory
        System.out.println("The materials have been used to build the house and are removed from your inventory.");
        System.out.println("Updated Inventory:");
        player.getInventory().getItems().forEach(item -> System.out.println("- " + item.getName()));
        
        // Mark the house as built in the ProgressManager
        progressManager.markHouseBuilt();
    }
}