import java.util.Random;

public class Weather {
    // weather types
    public enum WeatherCondition {
        SUNNY, RAINY, STORMY, WINDY, FOGGY
    }

    private WeatherCondition currentWeather;
    private int timeOfDay;
    // Constructor initializes the weather and time of day
    public Weather() {
        this.currentWeather = WeatherCondition.SUNNY;  // Default weather
        this.timeOfDay = 12;  // Default to noon
    }

    // Get current weather
    public WeatherCondition getCurrentWeather() {
        return currentWeather;
    }

    // Change the weather randomly
    public void advanceTime() {
        // Randomly change the weather every time the weather advances
        Random rand = new Random();
        currentWeather = WeatherCondition.values()[rand.nextInt(WeatherCondition.values().length)];

        // Passing of time 
        timeOfDay = (timeOfDay + 1) % 24;
        if (timeOfDay == 0) {
            System.out.println("A new day begins...");
        }
    }

    // Affect the player based on the current weather
    public void affectPlayer(Player player, HealthManager healthManager) {
        switch (currentWeather) {
            case SUNNY:
                // No negative effect, but maybe a slight health recovery over time
                System.out.println("The sun is shining brightly.");
                break;

            case RAINY:
                // Player might lose health due to cold or exhaustion
                healthManager.decreaseHealth(10);
                System.out.println("It's raining. You feel cold and exhausted.");
                break;

            case STORMY:
                // Severe weather may lead to injury or additional challenges
                healthManager.decreaseHealth(20);
                System.out.println("A storm has hit! The strong winds and rain are damaging.");
                break;

            case WINDY:
                // Windy weather may reduce health or make survival more difficult
                System.out.println("The wind is strong. It’s difficult to move around.");
                break;

            case FOGGY:
                // Low visibility might lead to accidents or hinder progress
                System.out.println("The fog is thick. Visibility is poor.");
                break;
        }
    }

    // Display the current weather condition
    public void displayWeather() {
        switch (currentWeather) {
            case SUNNY:
                System.out.println("The weather is sunny.");
                break;
            case RAINY:
                System.out.println("It’s raining.");
                break;
            case STORMY:
                System.out.println("A storm is raging.");
                break;
            case WINDY:
                System.out.println("It’s very windy.");
                break;
            case FOGGY:
                System.out.println("The fog is thick.");
                break;
        }
    }

    // Get the current time of day (optional)
    public int getTimeOfDay() {
        return timeOfDay;
    }

    // Set the time of day (optional for testing)
    public void setTimeOfDay(int timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}