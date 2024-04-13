package com.example.managingtransactions.services;
import com.example.managingtransactions.model.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartment(Long idd);
    Department createDepartment(Department department);
    Department updateDepartment(Department department, Long id);
    void deleteDepartment(Long id);
}
