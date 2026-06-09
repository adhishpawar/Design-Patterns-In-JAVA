package org.example.model;

import java.time.LocalDateTime;

public class Ticket {

    private String ticketId;
    private Vehicle vehicle;
    private LocalDateTime  entryTime;

    public Ticket(String ticketId, Vehicle vehicle,LocalDateTime  entryTime)
    {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
