package org.example.repo;

import org.example.model.Ticket;

import java.util.HashMap;
import java.util.Map;

public class ParkingRepository {

    private Map<String, Ticket> tickets = new HashMap<>();

    public void save(Ticket ticket)
    {
        tickets.put(ticket.getTicketId(), ticket);
    }
    public Ticket find(String ticketId){
        return tickets.get(ticketId);
    }
    public void remove(String ticketId)
    {
        tickets.remove(ticketId);
    }
}
