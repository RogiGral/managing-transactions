package com.example.managingtransactions.services.impl;

import com.example.managingtransactions.model.Department;
import com.example.managingtransactions.model.DepartmentType;
import com.example.managingtransactions.model.Employee;
import com.example.managingtransactions.repository.DepartmentRepository;
import com.example.managingtransactions.repository.DepartmentTypeRepository;
import com.example.managingtransactions.repository.EmployeeRepository;
import com.example.managingtransactions.services.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final DepartmentRepository departmentRepository;
    private final DepartmentTypeRepository departmentTypeRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentTypeRepository departmentTypeRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentTypeRepository = departmentTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Department not found"));
    }

    @Override
    public Department createDepartment(Department department) {
        DepartmentType departmentType = departmentTypeRepository.findById(department.getDepartmentType().getId())
                .orElseGet(() -> departmentTypeRepository.save(department.getDepartmentType()));

        department.setDepartmentType(departmentType);

        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department, Long id) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));

        DepartmentType departmentType = departmentTypeRepository.findById(department.getDepartmentType().getId())
                .orElseGet(() -> departmentTypeRepository.save(department.getDepartmentType()));

        existingDepartment.setDepartmentType(departmentType);
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
