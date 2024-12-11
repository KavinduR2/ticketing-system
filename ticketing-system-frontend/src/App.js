import React, { useState } from "react";

function App() {
    const [totalTickets, setTotalTickets] = useState("");
    const [maxCapacity, setMaxCapacity] = useState("");
    const [vendors, setVendors] = useState("");
    const [customers, setCustomers] = useState("");
    const [logs, setLogs] = useState([]);

    const handleSubmit = (e) => {
        e.preventDefault();
        setLogs([
            `Total Tickets: ${totalTickets}`,
            `Max Capacity: ${maxCapacity}`,
            `Vendors: ${vendors}`,
            `Customers: ${customers}`,
            "Simulation Started!"
        ]);
    };

    return (
        <div style={{ padding: "20px" }}>
            <h1>Real-Time Ticketing System</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Total Tickets: </label>
                    <input
                        type="number"
                        value={totalTickets}
                        onChange={(e) => setTotalTickets(e.target.value)}
                    />
                </div>
                <div>
                    <label>Max Capacity: </label>
                    <input
                        type="number"
                        value={maxCapacity}
                        onChange={(e) => setMaxCapacity(e.target.value)}
                    />
                </div>
                <div>
                    <label>Vendors: </label>
                    <input
                        type="number"
                        value={vendors}
                        onChange={(e) => setVendors(e.target.value)}
                    />
                </div>
                <div>
                    <label>Customers: </label>
                    <input
                        type="number"
                        value={customers}
                        onChange={(e) => setCustomers(e.target.value)}
                    />
                </div>
                <button type="submit">Start Simulation</button>
            </form>
            <div style={{ marginTop: "20px" }}>
                <h2>Logs</h2>
                <div style={{ border: "1px solid black", padding: "10px", height: "150px", overflowY: "scroll" }}>
                    {logs.map((log, index) => (
                        <p key={index}>{log}</p>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default App;
