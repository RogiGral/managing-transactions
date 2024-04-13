package com.example.managingtransactions.services;

import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> getAllTickets();
    Optional<Ticket> getTicket(Long id);
    void deleteTicket(Long id);
    Ticket addTicket(Ticket ticket);
    Ticket updateTicket(Ticket ticket, Long id);

    Ticket addEmployeeToTicket(Long ticketId, String employeeUuid);
}
