package com.example.managingtransactions.services.impl;

import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.model.Ticket;
import com.example.managingtransactions.repository.EmployeeRepository;
import com.example.managingtransactions.repository.TicketRepository;
import com.example.managingtransactions.services.TicketService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final EmployeeRepository employeeRepository;
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(EmployeeRepository employeeRepository, TicketRepository ticketRepository) {
        this.employeeRepository = employeeRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public Ticket addOrUpdateTicket(Ticket ticket) {
        if(ticket.getEmployee() == null) {
            throw new RuntimeException("Employee must be provided.");
        }

        final Employee employeeOnTicket = ticket.getEmployee();
        ticket.setEmployee(null);
        final Ticket savedTicket = ticketRepository.save(ticket);

        if(employeeOnTicket.getId() == null){
            final Employee employee = employeeRepository.save(employeeOnTicket);
            savedTicket.setEmployee(employee);
        } else {
            final Employee employee = employeeRepository.findById(employeeOnTicket.getId()).orElseThrow(()-> new RuntimeException("Employee not found"));
            savedTicket.setEmployee(employee);
        }

        return ticketRepository.save(savedTicket);

    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getTicket(String id) {
        return ticketRepository.findTicketById(id);
    }



    @Override
    public boolean isTicketExists(Ticket ticket) {
        return ticketRepository.existsTicketById(ticket.getId());
    }

    @Override
    public void deleteTicket(String id) {
        ticketRepository.deleteById(id);
    }
}
