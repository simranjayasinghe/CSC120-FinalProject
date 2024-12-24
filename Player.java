
public class Player {
    private HealthManager healthManager;
    private Inventory inventory;
    private String lastCommand;  // Track last command entered

    public Player() {
        this.inventory = new Inventory();
        this.lastCommand = "";  // initialize to empty string
        this.healthManager = new HealthManager();
    }

    //Get health of player
    public int getHealth() {
        return healthManager.getHealth();
    }

    // Get the inventory of  player
    public Inventory getInventory() {
        return inventory;
    }

    // Set the last command entered by the player
    public void setLastCommand(String command) {
        this.lastCommand = command;
    }

    // Get the last command entered by the player
    public String getLastCommand() {
        return lastCommand;
    }

}
