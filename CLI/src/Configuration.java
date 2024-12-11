import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the configuration for an event.
 */
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private double price;
    private String filename;

    // Constructor
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, String filename) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.price = price;
        this.filename = filename;
    }

    // Getters
    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Saves the current configuration to a JSON file.
     */
    public void saveConfig(Configuration config) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(config, writer);
            System.out.println("Configuration saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }
}
