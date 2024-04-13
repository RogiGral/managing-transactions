package com.example.managingtransactions.services;

import com.example.managingtransactions.exceptions.model.DepartmentNotFound;
import com.example.managingtransactions.exceptions.model.EmployeeNotFound;
import com.example.managingtransactions.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployee(String uuid);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee, String uuid) throws EmployeeNotFound;
    void deleteEmployee(String uuid) throws EmployeeNotFound;
    Employee addDepartmentToEmployee(String uuid, Long departmentId) throws DepartmentNotFound, EmployeeNotFound;
}
