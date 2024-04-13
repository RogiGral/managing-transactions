package com.example.managingtransactions.repository;

import com.example.managingtransactions.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByEmail(String email);
    Optional<Employee> findByUuid(String uuid);
}
