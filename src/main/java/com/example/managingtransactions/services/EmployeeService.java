package com.example.managingtransactions.services;

import com.example.managingtransactions.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployee(String uuid);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee, String uuid);
    void deleteEmployee(String uuid);
    Employee addDepartmentToEmployee(String uuid, Long departmentId);
}
