package com.example.managingtransactions.controller;

import com.example.managingtransactions.exceptions.model.EmployeeNotFound;
import com.example.managingtransactions.exceptions.model.TicketNotFound;
import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.model.HttpResponse;
import com.example.managingtransactions.model.Ticket;
import com.example.managingtransactions.services.EmployeeService;
import com.example.managingtransactions.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping(value ="/tickets")
@AllArgsConstructor
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping()
    public ResponseEntity<Ticket> createTicket(
            @RequestBody final Ticket ticket){

        Ticket newTicket = ticketService.addTicket(ticket);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Ticket> updateTicket(
            @PathVariable final Long id,
            @RequestBody final Ticket ticket) throws TicketNotFound {

        Ticket updatedTicket = ticketService.updateTicket(ticket,id);
        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }

    @PutMapping(path = "/add_employee_to_ticket/{employeeUuid}/{ticketId}")
    public ResponseEntity<Ticket> addEmployeeToTicket(
            @PathVariable final Long ticketId,
            @PathVariable final String employeeUuid
    ) throws TicketNotFound, EmployeeNotFound {
        Ticket ticket = ticketService.addEmployeeToTicket(ticketId,employeeUuid);
        return new ResponseEntity<>(ticket, OK);
    }


    @GetMapping()
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return new ResponseEntity<>(tickets, OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("id") Long id) {
        final Optional<Ticket> foundTicket = ticketService.getTicket(id);
        return foundTicket.map(ticket -> new ResponseEntity<>(ticket, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteTicket(@PathVariable("id") Long id) {
        ticketService.deleteTicket(id);
        return response(OK, "TICKET_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
