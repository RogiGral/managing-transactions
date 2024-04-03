package com.example.managingtransactions.services;

import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> getAllTickets();
    Optional<Ticket> getTicket(String id);
    Ticket addOrUpdateTicket(Ticket ticket);
    void deleteTicket(String id);

    boolean isTicketExists(Ticket ticket);
}
