public class HealthManager {
    private int health;
    private static final int MAX_HEALTH = 100;
    private static final int MIN_HEALTH = 0;

    public HealthManager() {
        this.health = MAX_HEALTH;
    }

    // Get current health
    public int getHealth() {
        return health;
    }

    // Decrease health by a certain amount
    public void decreaseHealth(int amount) {
        if (health - amount < MIN_HEALTH) {
            health = MIN_HEALTH;
        } else {
            health -= amount;
        }
    }

    // Increase health by a certain amount
    public void increaseHealth(int amount) {
        if (health + amount > MAX_HEALTH) {
            health = MAX_HEALTH;
        } else {
            health += amount;
        }
    }

    // Check if the player's health is 0 or below (game over condition)
    public boolean isGameOver() {
        return health <= MIN_HEALTH;
    }

    // Check if the player has enough health to survive a challenge
    public boolean canSurviveChallenge(int challengeDamage) {
        return health > challengeDamage;
    }

    // Update the player's health based on events in the game
    public void updateHealth(Player player, GameMap gameMap) {
        // For example, health decreases due to challenges
        Location currentLocation = gameMap.getCurrentLocation();

        // Example challenge: Wild Dog Attack decreases health by 20
        if (currentLocation.getName().equals("Wild Dog Attack")) {
            decreaseHealth(20);  // Decrease health when attacked by dogs
            System.out.println("You were attacked by wild dogs! You lost 20 health.");
        }

        // Add more challenge types as needed, e.g., starvation, weather effects, etc.

        // If health is 0 or below, the player dies
        if (isGameOver()) {
            System.out.println("You have died from your injuries. Game over.");
        }
    }

    // Display current health status
    public void displayHealthStatus() {
        System.out.println("Current Health: " + health + "/" + MAX_HEALTH);
    }
}