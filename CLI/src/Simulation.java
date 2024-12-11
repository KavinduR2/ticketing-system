import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) {
        // Initialization variables
        int totalTickets = 0, ticketReleaseRate = 0, customerRetrievalRate = 0, maxTicketCapacity = 0;
        double price = 0;
        String filename = "";

        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();

        // User menu
        System.out.println("Welcome to the TicketPool Simulation");
        System.out.println("1. Load an existing event");
        System.out.println("2. Create a new event");
        System.out.print("Enter your choice (1 or 2): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            // Load event configuration from JSON
            System.out.print("Enter the filename of the existing event (e.g., config.json): ");
            filename = scanner.nextLine();

            try (FileReader reader = new FileReader(filename)) {
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                totalTickets = jsonObject.get("totalTickets").getAsInt();
                ticketReleaseRate = jsonObject.get("ticketReleaseRate").getAsInt();
                customerRetrievalRate = jsonObject.get("customerRetrievalRate").getAsInt();
                maxTicketCapacity = jsonObject.get("maxTicketCapacity").getAsInt();

                System.out.println("Loaded Event Configuration:");
                System.out.println("Total Tickets: " + totalTickets);
                System.out.println("Ticket Release Rate: " + ticketReleaseRate);
                System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
                System.out.println("Max Ticket Capacity: " + maxTicketCapacity);
            } catch (FileNotFoundException e) {
                System.err.println("Error: File not found. Please check the filename.");
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else if (choice == 2) {
            // Create a new event configuration
            System.out.print("Enter the Event name: ");
            filename = scanner.nextLine();

            System.out.print("Enter the total tickets: ");
            totalTickets = inputValidation();

            System.out.print("Enter the ticket release rate: ");
            ticketReleaseRate = inputValidation();

            System.out.print("Enter the Customer retrieval rate: ");
            customerRetrievalRate = inputValidation();

            System.out.print("Enter the Max Capacity: ");
            maxTicketCapacity = inputValidation();

            System.out.print("Enter the price per Ticket: ");
            price = inputValidation();

            Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, filename);

            System.out.println("\nNew Event Configuration Created:");
            System.out.println("Total tickets: " + config.getTotalTickets());
            System.out.println("Ticket release rate: " + config.getTicketReleaseRate());
            System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
            System.out.println("Max ticket capacity: " + config.getMaxTicketCapacity());

            config.saveConfig(config);
            System.out.println("Configuration saved successfully to " + filename);
        } else {
            System.out.println("Invalid choice. Restart the program.");
            return;
        }

        // Initialize the TicketPool
        TicketPool ticketPool = new TicketPool(filename, totalTickets, maxTicketCapacity, price);

        scanner.close();
    }

    public static int inputValidation() {
        Scanner scanner = new Scanner(System.in);
        int value;

        while (true) {
            try {
                value = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Enter a valid number: ");
                scanner.next(); // Consume invalid input
            }
        }
        return value;
    }
}