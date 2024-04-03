package com.example.managingtransactions.repository;

import com.example.managingtransactions.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByEmail(String email);
    Employee findByUuid(String uuid);
    void deleteByUuid(String uuid);
    boolean existsEmployeeByUuid(String uuid);
}
