package com.example.managingtransactions.controller;

import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.model.HttpResponse;
import com.example.managingtransactions.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping(value ="/employee")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;



    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Employee> getEmployee(
            @PathVariable final String uuid
    ) {
        Employee employee = employeeService.getEmployee(uuid);
        return new ResponseEntity<>(employee, OK);
    }

    @PostMapping()
    public ResponseEntity<Employee> addNewEmployee(
            @RequestBody final Employee employee
    ){
        Employee newEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<Employee> addNewEmployee(
            @PathVariable final String uuid,
            @RequestBody final Employee employee
    ){
        Employee newEmployee = employeeService.updateEmployee(employee,uuid);
        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpResponse> deleteEmployee(@PathVariable("uuid") String uuid) {
        employeeService.deleteEmployee(uuid);
        return response(OK, "EMPLOYEE_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
