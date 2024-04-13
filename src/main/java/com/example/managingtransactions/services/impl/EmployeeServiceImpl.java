package com.example.managingtransactions.services.impl;

import com.example.managingtransactions.model.Department;
import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.repository.DepartmentRepository;
import com.example.managingtransactions.repository.EmployeeRepository;
import com.example.managingtransactions.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository){
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployee(String uuid) {
        return employeeRepository.findByUuid(uuid).orElseThrow(()-> new RuntimeException("Employee with id: "+uuid+" not found"));
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

        Employee foundEmployee = employeeRepository.findByUuid(uuid)
                .orElseThrow(()-> new RuntimeException("Employee with id: "+uuid+" not found"));

        Employee existingEmployeeWithEmail = employeeRepository.findByEmail(employee.getEmail());
        if (existingEmployeeWithEmail != null && !existingEmployeeWithEmail.getUuid().equals(uuid)) {
            throw new RuntimeException("Email "+employee.getEmail()+" already in use by another employee");
        }

        foundEmployee.setEmail(employee.getEmail());
        foundEmployee.setGender(employee.getGender());
        foundEmployee.setFirstName(employee.getFirstName());
        foundEmployee.setLastName(employee.getLastName());
        foundEmployee.setDob(employee.getDob());

        return employeeRepository.save(foundEmployee);
    }

    @Override
    public void deleteEmployee(String uuid) {
        Employee employee = employeeRepository.findByUuid(uuid)
                .orElseThrow(()-> new RuntimeException("Employee with id: "+uuid+" not found"));
         employeeRepository.delete(employee);
    }

    @Override
    public Employee addDepartmentToEmployee(String uuid, Long departmentId) {
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(()-> new RuntimeException("Department with id: "+departmentId+" not found"));
        Employee employee = employeeRepository
                .findByUuid(uuid)
                .orElseThrow(()-> new RuntimeException("Employee with id: "+uuid+" not found"));
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return employeeRepository.save(employee);
    }
}
