import java.util.Scanner;

public class Game {
    private Player player;
    private GameMap gameMap;
    private Weather weather;
    private CommandManager commandManager;
    private ProgressManager progressManager;
    private HealthManager healthManager;
    private Inventory inventory;
    private int commandCount;

    public Game() {
        player = new Player();
        gameMap = new GameMap();
        weather = new Weather();
        commandManager = new CommandManager();
        healthManager = new HealthManager();
        progressManager = new ProgressManager(player.getInventory(), healthManager);
        inventory = new Inventory();
        commandCount = 0;
    }

    private void displayGameInstructions() {
        System.out.println("Instructions: Survive the island by gathering key items, managing your health, and solving challenges.");
        System.out.println("Type commands like 'move north', 'take item', or 'use item' to play the game.");
        System.out.println("Note: The game ends after 100 in-game days if you haven't escaped.");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isGameRunning = true;

        System.out.println("Welcome to the Island Survival Game!");
        displayGameInstructions();

        while (isGameRunning) {

            // Display current day
            System.out.println("\nDay " + progressManager.getDayCount() + " on the island...");

            // Update time of day and weather
            weather.advanceTime();
            weather.affectPlayer(player, healthManager);

            // Display current health status and inventory
            healthManager.displayHealthStatus();
            System.out.println("Inventory: ");
            player.getInventory().getItems().forEach(item -> System.out.println("- " + item.getName()));

            // Display current location and its description
            Location currentLocation = gameMap.getCurrentLocation();
            System.out.println("You are at: " + currentLocation.getDescription());

            // Display available items in the location
            currentLocation.displayItems();

            // Display available actions at the current location (such as building a house)
            if (!currentLocation.getActions().isEmpty()) {
                System.out.println("Available actions:");
                currentLocation.getActions().forEach(action -> System.out.println("- " + action));
                System.out.print("Would you like to perform an action? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("yes")) {
                    // Prompt for action and do action
                    System.out.print("Which action would you like to perform? ");
                    String chosenAction = scanner.nextLine().trim();
                    boolean actionExecuted = currentLocation.executeAction(chosenAction, player, progressManager);
                    if (!actionExecuted) {
                        System.out.println("Invalid or unavailable action.");
                    }
                }
            }

            // Get and parse  user input
            System.out.print("> ");
            String input = scanner.nextLine();
            String command = commandManager.parse(input, player);

            // Execute the parsed command
            isGameRunning = commandManager.execute(command, player, gameMap, healthManager, progressManager);

            // Increase the command count
            commandCount++;

            // Check if it's time to advance the day (every 5 commands)
            if (commandCount >= 5) {
                progressManager.advanceDay();  // call to advance the day
                commandCount = 0;  // Reset counter after advancing the day
            }

            if (commandCount % 10 == 0) {
                healthManager.decreaseHealth(10);
            }

            // Check if players health reached 0, game ends
            if (healthManager.isGameOver()) {
                System.out.println("Game over! You did not survive.");
                isGameRunning = false;
            }

            // End the game after 100 days
            if (progressManager.isTimeUp()) {
                System.out.println("Congrats! You have survived until the next trading ship has arrived, and may leave the island with the traders.");
                isGameRunning = false;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Initialize the game
        Game game = new Game();
    
        // Start the game
        game.start();
    }
}