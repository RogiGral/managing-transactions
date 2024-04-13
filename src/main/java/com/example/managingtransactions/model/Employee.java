package com.example.managingtransactions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee extends AuditRecord {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SEQ")
    @SequenceGenerator(name = "EMPLOYEE_SEQ", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Column(name = "UUID",nullable = false, unique = true)
    private String uuid;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "DATE_OF_BIRTH")
    private String dob;
    @Column(name = "EMAIL", unique = true)
    private String email;

    @JoinColumn(name = "DEPARTMENT", referencedColumnName = "ID")
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Department department;

}
