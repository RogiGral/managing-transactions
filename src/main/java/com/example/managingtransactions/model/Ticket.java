package com.example.managingtransactions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String title;
    private String description;
    private String creationDate;
    private String endDate;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;
}
