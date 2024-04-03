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

    @PostMapping("/add")
    public ResponseEntity<Employee> addNewEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("gender") String gender,
            @RequestParam("dob") String dob,
            @RequestParam("email") String email
    ){
        Employee newEmployee = employeeService.addEmployee(firstName, lastName, gender, dob, email);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @PostMapping("/update")
    public ResponseEntity<Employee> addNewEmployee(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "dob", required = false) String dob,
            @RequestParam(value = "email", required = false) String email
    ) throws Exception {
        if (id == null && firstName == null && lastName == null && gender == null && dob == null && email == null) {
            throw new IllegalArgumentException("At least one parameter is required.");
        }
        Employee newEmployee = employeeService.updateEmployee(id, firstName, lastName, gender, dob, email);
        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, OK);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(employee, OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return response(OK, "EMPLOYEE_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
