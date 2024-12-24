public class ProgressManager {
    private boolean hasFoundKeyItems;  // track if the player has found key items
    private Inventory inventory;      // player's inventory
    private int days;
    private int MAX_DAYS;
    private HealthManager healthManager;
    private boolean houseBuilt; // Track if the house has been built

    public ProgressManager(Inventory inventory, HealthManager healthManager) {
        this.inventory = inventory;
        this.hasFoundKeyItems = false;
        this.days = 1;
        this.healthManager = healthManager;
        this.MAX_DAYS = 100;
        this.houseBuilt = false;  // Start with no house built
    }

    // Increase the day count
    public void advanceDay() {
        days++;
        System.out.println("Day " + days + " has begun!");
        
        // Decrease health every 2 days
        if (days % 2 == 0) {
            healthManager.decreaseHealth(10);
        }
    }

    // Get the current day
    public int getDayCount() {
        return days;
    }

    // Check if the maximum number of days has been reached
    public boolean isTimeUp() {
        return days >= MAX_DAYS;
    }

    // Check if key items are in the inventory
    public void checkForKeyItems() {
        // Define the key items needed to progress (e.g., "Map", "Compass")
        String[] keyItemNames = {"Driftwood"};  // Example key items

        // Iterate over the key item names
        for (String keyItemName : keyItemNames) {
            boolean hasItem = false;
            
            // Check if the inventory contains the key item
            for (Item item : inventory.getItems()) {  // Correctly iterating over List<Item>
                if (item.getName().equalsIgnoreCase(keyItemName)) {  // Case-insensitive comparison
                    hasItem = true;
                    break;
                }
            }

            // If a key item is not found, stop checking
            if (!hasItem) {
                System.out.println("You haven't found a " + keyItemName + " yet.");
                return;  // Exit if a key item is missing
            }
        }

        // If all key items are found, set hasFoundKeyItems to true
        hasFoundKeyItems = true;
        System.out.println("You have found all the key items!");
    }

    // Get the status of key items
    public boolean hasFoundKeyItems() {
        return hasFoundKeyItems;
    }

    // Check if the player has all materials to build a house
    public boolean hasAllMaterials() {
        boolean hasWhaleBones = false;
        boolean hasSinew = false;
        boolean hasDriftwood = false;

        // Check for the required items in the inventory
        for (Item item : inventory.getItems()) {
            if (item.getName().equalsIgnoreCase("Whale Bones")) {
                hasWhaleBones = true;
            }
            if (item.getName().equalsIgnoreCase("Sinew")) {
                hasSinew = true;
            }
            if (item.getName().equalsIgnoreCase("Driftwood")) {
                hasDriftwood = true;
            }
        }

        return hasWhaleBones && hasSinew && hasDriftwood;
    }

    // Mark the house as built
    public void markHouseBuilt() {
        houseBuilt = true;
        System.out.println("You have successfully built the house.");
    }

    // Get the status of the house (whether it has been built or not)
    public boolean isHouseBuilt() {
        return houseBuilt;
    }

    // Reset progress if needed
    public void resetProgress() {
        hasFoundKeyItems = false;
        houseBuilt = false;  // Reset the house build status
    }
}