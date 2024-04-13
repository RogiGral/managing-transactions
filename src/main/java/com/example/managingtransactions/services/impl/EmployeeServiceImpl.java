package com.example.managingtransactions.services.impl;

import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.repository.EmployeeRepository;
import com.example.managingtransactions.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployee(String uuid) {
        return employeeRepository.findByUuid(uuid);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if(employeeRepository.findByEmail(employee.getEmail()) != null){
            throw new RuntimeException("Email must be unique");
        }
        String newUuid = UUID.randomUUID().toString();
        employee.setUuid(newUuid);
        Employee savedEmployee =  employeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    public Employee updateEmployee(Employee employee, String uuid) {
        Employee foundEmployee = employeeRepository.findByUuid(uuid);
        if (foundEmployee == null) {
            throw new RuntimeException("Employee does not exist");
        }
        Employee existingEmployeeWithEmail = employeeRepository.findByEmail(employee.getEmail());
        if (existingEmployeeWithEmail != null && !existingEmployeeWithEmail.getUuid().equals(uuid)) {
            throw new RuntimeException("Email must be unique");
        }
        if (employee.getEmail() != null) {
            foundEmployee.setEmail(employee.getEmail());
        }
        if (employee.getGender() != null) {
            foundEmployee.setGender(employee.getGender());
        }
        if (employee.getFirstName() != null) {
            foundEmployee.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            foundEmployee.setLastName(employee.getLastName());
        }
        if (employee.getDob() != null) {
            foundEmployee.setDob(employee.getDob());
        }

        return employeeRepository.save(foundEmployee);
    }

    @Override
    public void deleteEmployee(String uuid) {
        Employee employee = employeeRepository.findByUuid(uuid);
        if(employee == null){
            throw new RuntimeException("Employee does not exists");
        } else {
         employeeRepository.delete(employee);
        }
    }
}
