package com.example.managingtransactions.services;
import com.example.managingtransactions.exceptions.model.DepartmentNotFound;
import com.example.managingtransactions.model.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartment(Long idd);
    Department createDepartment(Department department);
    Department updateDepartment(Department department, Long id) throws DepartmentNotFound;
    void deleteDepartment(Long id);
}
