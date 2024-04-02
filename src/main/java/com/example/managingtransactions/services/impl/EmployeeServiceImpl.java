package com.example.managingtransactions.services.impl;

import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.repository.EmployeeRepository;
import com.example.managingtransactions.services.EmployeeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public Employee getEmployee(Long id) {
        return employeeRepository.getReferenceById(id);
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, String gender, String dob, String email) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setGender(gender);
        employee.setEmail(email);
        employee.setDob(dob);
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, String firstName, String lastName, String gender, String dob, String email) throws Exception {
        Employee employee = findemployeeById(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setGender(gender);
        employee.setEmail(email);
        employee.setDob(dob);
        employeeRepository.save(employee);
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private Employee findemployeeById(Long id) throws Exception {
        Employee employee = employeeRepository.getReferenceById(id);
        if(employee == null){
            throw new Exception("NO_WORKOUT_FOUND_BY_ID"+id);
        }
        return employee;
    }
}
