import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the total number of tickets and other configurations from the user
        System.out.println("Welcome to the Real-Time Event Ticketing System!");
        System.out.print("Enter the total number of tickets: ");
        int totalTickets = scanner.nextInt();
        System.out.print("Enter the maximum ticket capacity in the pool: ");
        int maxCapacity = scanner.nextInt();
        System.out.print("Enter the number of vendors: ");
        int vendorCount = scanner.nextInt();
        System.out.print("Enter the number of customers: ");
        int customerCount = scanner.nextInt();

        // Initialize TicketPool
        TicketPool ticketPool = TicketPool.getInstance();
        ticketPool.setMaxCapacity(maxCapacity);

        // Create vendors and customers
        List<Thread> vendorThreads = new ArrayList<>();
        List<Thread> customerThreads = new ArrayList<>();

        for (int i = 1; i <= vendorCount; i++) {
            Vendor vendor = new Vendor("Vendor-" + i);
            vendor.addEventNameIdea("Event-" + i);
            Thread vendorThread = new Thread(vendor);
            vendorThreads.add(vendorThread);
        }

        for (int i = 1; i <= customerCount; i++) {
            Customer customer = new Customer("Customer-" + i);
            Thread customerThread = new Thread(customer);
            customerThreads.add(customerThread);
        }

        // Start vendor threads
        for (Thread vendorThread : vendorThreads) {
            vendorThread.start();
        }

        // Start customer threads
        for (Thread customerThread : customerThreads) {
            customerThread.start();
        }

        // Wait for all threads to finish
        try {
            for (Thread vendorThread : vendorThreads) {
                vendorThread.join();
            }
            for (Thread customerThread : customerThreads) {
                customerThread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        System.out.println("Simulation completed. Thank you for using the system!");
    }
}
