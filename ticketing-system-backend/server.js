const express = require("express");
const cors = require("cors");

const app = express();
app.use(cors()); // Allow cross-origin requests
app.use(express.json()); // Parse JSON request bodies

// Placeholder for tickets and sold tickets
let tickets = [];
let soldTickets = [];

// Start the simulation
app.post("/start-simulation", (req, res) => {
    const { totalTickets, maxCapacity, vendors, customers } = req.body;

    if (!totalTickets || !maxCapacity || !vendors || !customers) {
        return res.status(400).send({ error: "All fields are required!" });
    }

    // Initialize ticket pool
    tickets = Array.from({ length: totalTickets }, (_, i) => ({
        id: i + 1,
        event: `Event-${Math.ceil((i + 1) / (totalTickets / vendors))}`,
        status: "available",
    }));

    soldTickets = []; // Reset sold tickets

    res.send({
        message: "Simulation started successfully!",
        totalTickets: tickets.length,
        availableTickets: tickets.length,
        soldTickets: soldTickets.length,
    });
});

// Handle ticket purchases
app.post("/buy-ticket", (req, res) => {
    const { customerName } = req.body;

    if (!customerName) {
        return res.status(400).send({ error: "Customer name is required!" });
    }

    const ticket = tickets.find((t) => t.status === "available");
    if (!ticket) {
        return res.status(404).send({ error: "No tickets available!" });
    }

    ticket.status = "sold";
    soldTickets.push({ ...ticket, customer: customerName });

    res.send({
        message: `Ticket purchased successfully by ${customerName}`,
        ticket,
        availableTickets: tickets.filter((t) => t.status === "available").length,
        soldTickets: soldTickets.length,
    });
});

// Start the server
const PORT = 5000;
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
