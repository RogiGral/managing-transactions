package com.example.managingtransactions.services;

import com.example.managingtransactions.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployee(Long id);
    Employee addEmployee(String firstName, String lastName,String gender, String dob, String email);
    Employee updateEmployee(Long id, String firstName, String lastName,String gender, String dob, String email) throws Exception;
    void deleteEmployee(Long id);
}
