package com.example.managingtransactions.services.impl;

import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.repository.EmployeeRepository;
import com.example.managingtransactions.services.EmployeeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
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
        if(foundEmployee == null){
            throw new RuntimeException("Employee does not exists");
        }
        else if(employeeRepository.findByEmail(employee.getEmail()) != null){
            throw new RuntimeException("Email must be unique");
        } else {
            foundEmployee.setEmail(employee.getEmail() != null ? employee.getEmail() : foundEmployee.getEmail());
            foundEmployee.setGender(employee.getGender() != null ? employee.getGender() : foundEmployee.getGender());
            foundEmployee.setFirstName(employee.getFirstName() != null ? employee.getFirstName() : foundEmployee.getFirstName());
            foundEmployee.setLastName(employee.getLastName() != null ? employee.getLastName() : foundEmployee.getLastName());
            foundEmployee.setDob(employee.getDob() != null ? employee.getDob() : foundEmployee.getDob());
            employeeRepository.save(foundEmployee);
        }
        return foundEmployee;
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
