/**
 * Represents an event for which tickets can be sold.
 */
public class Event {
    private final String name;
    private final String date;
    private final Vendor vendor;
    private int ticketCount;
    private final int price;

    /**
     * Constructor to initialize an event with details.
     *
     * @param name        Event name
     * @param date        Event date
     * @param vendor      Vendor managing the event
     * @param ticketCount Number of tickets available
     * @param price       Price per ticket
     */
    public Event(String name, String date, Vendor vendor, int ticketCount, int price) {
        this.name = name;
        this.date = date;
        this.vendor = vendor;
        this.ticketCount = ticketCount;
        this.price = price;
    }

    /**
     * Issues tickets for the event and resets ticketCount to zero.
     *
     * @return Array of issued tickets
     */
    public EventTicket[] issueTickets() {
        EventTicket[] tickets = new EventTicket[ticketCount];
        for (int i = 0; i < ticketCount; i++) {
            tickets[i] = new EventTicket(this, price);
        }
        ticketCount = 0; // All tickets are issued
        return tickets;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Vendor getVendor() {
        return vendor;
    }
}
