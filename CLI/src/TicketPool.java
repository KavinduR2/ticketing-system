import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private static TicketPool instance;
    private final List<EventTicket> freshTickets;
    private final List<EventTicket> soldTickets;
    private int maxCapacity;

    public static TicketPool getInstance() {
        if (instance == null) {
            synchronized (TicketPool.class) {
                if (instance == null) {

                }
            }
        }
        return instance;
    }

    TicketPool(String filename, int totalTickets, int maxTicketCapacity, double price) {
        freshTickets = Collections.synchronizedList(new ArrayList<>());
        soldTickets = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized void setMaxCapacity(int capacity) {
        this.maxCapacity = capacity;
    }

    public synchronized boolean isFull() {
        return freshTickets.size() >= maxCapacity;
    }

    public synchronized void addTickets(Event event) {
        if (isFull()) {
            System.out.println("TicketPool is at maximum capacity. Cannot add more tickets.");
            return;
        }
        EventTicket[] tickets = event.issueTickets();
        for (EventTicket ticket : tickets) {
            freshTickets.add(ticket);
        }
        System.out.println("Tickets added to the pool: " + tickets.length);
    }

    public synchronized EventTicket buyRandomTicket(Customer customer) {
        if (freshTickets.isEmpty()) {
            return null;
        }

        int randomIndex = (int) (Math.random() * freshTickets.size());
        EventTicket ticket = freshTickets.remove(randomIndex);
        ticket.sellTicket(customer);
        soldTickets.add(ticket);
        return ticket;
    }

    public synchronized int getAvailableTicketCount() {
        return freshTickets.size();
    }

    public synchronized int getSoldTicketCount() {
        return soldTickets.size();
    }
}
