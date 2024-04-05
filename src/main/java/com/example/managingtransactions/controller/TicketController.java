package com.example.managingtransactions.controller;

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

    @PutMapping(path = "/{id}")
    public ResponseEntity<Ticket> createUpdateTicket(
            @PathVariable final String id,
            @RequestBody final Ticket ticket){
        ticket.setId(id);
        final boolean isTicketExists = ticketService.isTicketExists(ticket);

        Ticket newTicket = ticketService.addOrUpdateTicket(ticket);
        if (isTicketExists) {
            return new ResponseEntity<>(newTicket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return new ResponseEntity<>(tickets, OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("id") String id) {
        final Optional<Ticket> foundTicket = ticketService.getTicket(id);
        return foundTicket.map(ticket -> new ResponseEntity<>(ticket, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteTicket(@PathVariable("id") String id) {
        ticketService.deleteTicket(id);
        return response(OK, "TICKET_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
