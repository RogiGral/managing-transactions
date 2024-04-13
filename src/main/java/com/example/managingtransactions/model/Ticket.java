package com.example.managingtransactions.model;

import com.example.managingtransactions.enumeration.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TICKET")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket extends AuditRecord {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TICKET_SEQ")
    @SequenceGenerator(name = "TICKET_SEQ", sequenceName = "TICKET_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TICKET_STATUS")
    private TicketStatus ticketStatus;

    @JoinColumn(name = "EMPLOYEE", referencedColumnName = "ID")
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    private Employee employee;

}
