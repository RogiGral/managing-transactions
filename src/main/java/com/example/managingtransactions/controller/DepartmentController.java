package com.example.managingtransactions.controller;

import com.example.managingtransactions.model.Department;
import com.example.managingtransactions.model.HttpResponse;
import com.example.managingtransactions.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping(value ="/department")
@AllArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;



    @GetMapping()
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(
            @PathVariable final Long id
    ) {
        Department department = departmentService.getDepartment(id);
        return new ResponseEntity<>(department, OK);
    }

    @PostMapping()
    public ResponseEntity<Department> addNewDepartment(
            @RequestBody final Department department
    ){
        Department newDepartment = departmentService.createDepartment(department);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Department> addNewDepartment(
            @PathVariable final Long id,
            @RequestBody final Department department
    ){
        Department newDepartment = departmentService.updateDepartment(department,id);
        return new ResponseEntity<>(newDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponse> deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return response(OK, "DEPARTMENT_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
