import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vendor who creates events and releases tickets.
 */
public class Vendor implements Runnable {
    private final String name;
    private final List<String> eventNameIdeas;
    private final List<Event> events;

    /**
     * Constructor to initialize a vendor with a name.
     *
     * @param name Vendor's name
     */
    public Vendor(String name) {
        this.name = name;
        this.eventNameIdeas = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    /**
     * Adds a new event name idea to the vendor's list.
     *
     * @param eventName The name of the event
     */
    public void addEventNameIdea(String eventName) {
        eventNameIdeas.add(eventName);
    }

    @Override
    public void run() {
        TicketPool ticketPool = TicketPool.getInstance();
        for (String eventName : eventNameIdeas) {
            Event event = new Event(eventName, "2024-12-31", this, 10, 100);
            ticketPool.addTickets(event);
            events.add(event);
            System.out.println(name + " created and added tickets for " + eventName);
            try {
                Thread.sleep(500); // Simulate delay
            } catch (InterruptedException e) {
                System.err.println(name + " was interrupted.");
            }
        }
        System.out.println(name + " has finished creating events.");
    }
}
