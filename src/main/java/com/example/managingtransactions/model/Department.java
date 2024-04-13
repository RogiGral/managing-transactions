package com.example.managingtransactions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DEPARTMENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Department extends AuditRecord{
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPARTMENT_SEQ")
    @SequenceGenerator(name = "DEPARTMENT_SEQ", sequenceName = "DEPARTMENT_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "DEPARTMENT_TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private DepartmentType departmentType;

}
