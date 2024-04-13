package com.example.managingtransactions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DEPARTMENT_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentType extends AuditRecord {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTMENT_TYPE_SEQ")
    @SequenceGenerator(name = "DEPARTMENT_TYPE_SEQ", sequenceName = "DEPARTMENT_TYPE_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", unique = true)
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;

}
