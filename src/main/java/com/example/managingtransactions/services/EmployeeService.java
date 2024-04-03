package com.example.managingtransactions.services;

import com.example.managingtransactions.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployee(String uuid);
    Employee createUpdateEmployee(Employee employee, String uuid);
    boolean isEmployeeExist(String uuid);
    void deleteEmployee(String uuid);
}
