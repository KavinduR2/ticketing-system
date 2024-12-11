/**
 * Represents a ticket for a specific event.
 */
public class EventTicket {
    private final Event event;
    private final int price;
    private boolean isSold;

    /**
     * Constructor to initialize a ticket for an event.
     *
     * @param event The event the ticket is for
     * @param price The price of the ticket
     */
    public EventTicket(Event event, int price) {
        this.event = event;
        this.price = price;
        this.isSold = false;
    }

    /**
     * Marks the ticket as sold to a customer.
     *
     * @param customer The customer who bought the ticket
     */
    public void sellTicket(Customer customer) {
        if (isSold) {
            throw new IllegalStateException("Ticket already sold.");
        }
        this.isSold = true;
    }

    public Event getEvent() {
        return event;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSold() {
        return isSold;
    }
}
