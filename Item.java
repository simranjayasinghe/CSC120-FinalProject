public class Item {
    private String name;
    private String description;

    // Constructor for the item
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Get the name of the item
    public String getName() {
        return name;
    }

    // Get the description of the item
    public String getDescription() {
        return description;
    }

}