import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer who buys tickets from the ticket pool.
 */
public class Customer implements Runnable {
    private final String name;
    private final List<EventTicket> pocket;

    /**
     * Constructor to initialize a customer with a name.
     *
     * @param name Customer's name
     */
    public Customer(String name) {
        this.name = name;
        this.pocket = new ArrayList<>();
    }

    /**
     * Attempts to buy a ticket from the ticket pool.
     *
     * @param ticketPool The shared ticket pool
     * @return true if a ticket was successfully purchased, false otherwise
     */
    private boolean buyTicket(TicketPool ticketPool) {
        synchronized (ticketPool) {
            if (ticketPool.getAvailableTicketCount() <= 0) {
                System.out.println("No tickets available for " + name);
                return false;
            }
            EventTicket ticket = ticketPool.buyRandomTicket(this);
            if (ticket == null) {
                System.out.println(name + " couldn't buy a ticket.");
                return false;
            }
            pocket.add(ticket);
            System.out.println(name + " bought a ticket for " + ticket.getEvent().getName());
            return true;
        }
    }

    @Override
    public void run() {
        TicketPool ticketPool = TicketPool.getInstance();
        while (pocket.size() < 5) { // Limit tickets a customer can purchase
            if (!buyTicket(ticketPool)) {
                try {
                    Thread.sleep(1000); // Wait before retrying
                } catch (InterruptedException e) {
                    System.err.println(name + " was interrupted.");
                }
            }
        }
        System.out.println(name + " has finished purchasing tickets.");
    }
}
