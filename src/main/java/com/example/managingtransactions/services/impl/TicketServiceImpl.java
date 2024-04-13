package com.example.managingtransactions.services.impl;

import com.example.managingtransactions.enumeration.TicketStatus;
import com.example.managingtransactions.exceptions.model.EmployeeNotFound;
import com.example.managingtransactions.exceptions.model.TicketNotFound;
import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.model.Ticket;
import com.example.managingtransactions.repository.EmployeeRepository;
import com.example.managingtransactions.repository.TicketRepository;
import com.example.managingtransactions.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final EmployeeRepository employeeRepository;
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(EmployeeRepository employeeRepository, TicketRepository ticketRepository) {
        this.employeeRepository = employeeRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ticket> getTicket(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        ticket.setEmployee(null);
        Ticket savedTicket =  ticketRepository.save(ticket);
        return savedTicket;
    }

    @Override
    public Ticket updateTicket(Ticket ticket, Long id) throws TicketNotFound {
        Ticket foundTicket = ticketRepository.findById(id)
                .orElseThrow(()-> new TicketNotFound("Tikcet with id: "+id+" not found"));

        foundTicket.setTitle(ticket.getTitle());
        foundTicket.setDescription(ticket.getDescription());
        foundTicket.setTicketStatus(ticket.getTicketStatus());

        return ticketRepository.save(foundTicket);
    }
    @Override
    public Ticket addEmployeeToTicket(Long ticketId, String employeeUuid) throws EmployeeNotFound, TicketNotFound {
        Ticket foundTicket = ticketRepository.findById(ticketId)
                .orElseThrow(()-> new TicketNotFound("Tikcet with id: "+ticketId+" not found"));

        Employee employee = employeeRepository
                .findByUuid(employeeUuid)
                .orElseThrow(()-> new EmployeeNotFound("Employee with id: "+employeeUuid+" not found"));

        foundTicket.setEmployee(employee);

        return ticketRepository.save(foundTicket);
    }

}
