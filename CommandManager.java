import java.util.Arrays;
import java.util.List;

public class CommandManager {
    //valid commands
    private static final List<String> validCommands = Arrays.asList("move", "take", "use", "status", "exit");

    // Parse input string into a command
    public String parse(String input, Player player) {
        String command = input.trim().toLowerCase().split(" ")[0]; // Get the first word of the input
        player.setLastCommand(input);  
        if (validCommands.contains(command)) {
            return command;
        } else {
            System.out.println("Invalid command. Try again.");
            return "";  // empty string for invalid commands
        }
    }

    // Execute the command based on the parsed input
    public boolean execute(String command, Player player, GameMap gameMap, HealthManager healthManager, ProgressManager progressManager) {
        switch (command) {
            case "move":
                // Command: move <direction>
                return handleMove(player, gameMap);
            case "take":
                // Command: take <item>
                return handleTake(player, gameMap);
            case "use":
                // Command: use <item>
                return handleUse(player);
            case "status":
                // Command: status
                handleStatus(player, healthManager);
                return true; // Continue game
            case "exit":
                // Command: exit (end the game)
                System.out.println("Exiting game...");
                return false; // End the game
            default:
                System.out.println("Invalid command.");
                return true; // Continue game
        }
    }

    // the move command
    private boolean handleMove(Player player, GameMap gameMap) {
        String[] commandParts = player.getLastCommand().split(" ");
        if (commandParts.length < 2) {
            System.out.println("You must specify a direction to move.");
            return true; // Continue game
        }

        String direction = commandParts[1];
        gameMap.movePlayer(direction);
        return true; // Continue game
    }

    // take command
    private boolean handleTake(Player player, GameMap gameMap) {
        String[] commandParts = player.getLastCommand().split(" ");
        if (commandParts.length < 2) {
            System.out.println("You must specify an item to take.");
            return true; // Continue game
        }
    
        // Combine all parts of the command after "take" as the item name
        String itemName = String.join(" ", Arrays.copyOfRange(commandParts, 1, commandParts.length));
        Location currentLocation = gameMap.getCurrentLocation();
        Item item = currentLocation.getItems().stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);
    
        if (item != null) {
            player.getInventory().addItem(item);
            currentLocation.getItems().remove(item);
            System.out.println("You have taken the " + itemName + ".");
        } else {
            System.out.println("Item not found in this location.");
        }
    
        return true; // Continue game
    }

    // Handle the use command
    private boolean handleUse(Player player) {
        String[] commandParts = player.getLastCommand().split(" ");
        if (commandParts.length < 2) {
            System.out.println("You must specify an item to use.");
            return true; // Continue game
        }

        String itemName = commandParts[1];
        Item item = player.getInventory().getItems().stream()
                .filter(i -> i.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);

        if (item != null) {
            // Simulate using item for healing, solving challenges. etc
            System.out.println("You used the " + itemName + ".");
            if (itemName.equalsIgnoreCase("wooden stick")) {
                // Placeholder for using the item in a more complex way
                System.out.println("You defended yourself with the wooden stick.");
            }
        } else {
            System.out.println("You do not have that item.");
        }

        return true; // Continue game
    }

    // status command
    private void handleStatus(Player player, HealthManager healthManager) {
        // Show current health and inventory
        System.out.println("Health: " + healthManager.getHealth());
        System.out.println("Inventory: ");
        player.getInventory().getItems().forEach(item -> System.out.println("- " + item.getName()));
    }
}