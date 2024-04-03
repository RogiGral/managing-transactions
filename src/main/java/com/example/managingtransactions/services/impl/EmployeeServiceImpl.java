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

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private EmployeeRepository employeeRepository;

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
    public Employee createUpdateEmployee(Employee employee, String uuid) {
        Employee foundEmployee = employeeRepository.findByUuid(uuid);
        if(foundEmployee == null){
            if(employeeRepository.findByEmail(employee.getEmail()) != null){
                throw new RuntimeException("Email must be unique");
            }
            String newUuid = UUID.randomUUID().toString();
            employee.setUuid(newUuid);
            employeeRepository.save(employee);
        } else {
            if(employeeRepository.findByEmail(employee.getEmail()) != null){
                throw new RuntimeException("Email must be unique");
            }
            foundEmployee.setEmail(employee.getEmail());
            foundEmployee.setGender(employee.getGender());
            foundEmployee.setFirstName(employee.getFirstName());
            foundEmployee.setLastName(employee.getLastName());
            foundEmployee.setDob(employee.getDob());
            employeeRepository.save(foundEmployee);
        }

        return employee;
    }

    @Override
    public boolean isEmployeeExist(String uuid) {
        return employeeRepository.existsEmployeeByUuid(uuid);
    }

    @Override
    public void deleteEmployee(String uuid) {
        employeeRepository.deleteByUuid(uuid);
    }
}
