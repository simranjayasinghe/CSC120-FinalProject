import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    // Add an item to the inventory
    public void addItem(Item item) {
        items.add(item);
    }

    // Remove an item from the inventory
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    // Display all items in the inventory
    public void displayInventory() {
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory:");
            for (Item item : items) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }

    // Get the number of items in the inventory
    public int getItemCount() {
        return items.size();
    }

    // Add a method to get all items for the ProgressManager to check
    public List<Item> getItems() {
        return items;
    }
}