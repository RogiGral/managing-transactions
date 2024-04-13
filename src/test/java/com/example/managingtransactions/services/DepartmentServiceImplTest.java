package com.example.managingtransactions.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.managingtransactions.exceptions.model.DepartmentNotFound;
import com.example.managingtransactions.services.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.managingtransactions.repository.DepartmentRepository;
import com.example.managingtransactions.repository.DepartmentTypeRepository;
import com.example.managingtransactions.model.Department;
import com.example.managingtransactions.model.DepartmentType;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentTypeRepository departmentTypeRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void testGetAllDepartments() {
        List<Department> expectedDepartments = new ArrayList<>();
        when(departmentRepository.findAll()).thenReturn(expectedDepartments);
        List<Department> actualDepartments = departmentService.getAllDepartments();
        assertSame(expectedDepartments, actualDepartments);
    }

    @Test
    public void testGetDepartment() {
        Long departmentId = 1L;
        Optional<Department> department = Optional.of(new Department());
        when(departmentRepository.findById(departmentId)).thenReturn(department);
        Department foundDepartment = departmentService.getDepartment(departmentId);
        assertNotNull(foundDepartment);
    }

    @Test
    public void testCreateDepartment() {
        Department department = new Department();
        DepartmentType departmentType = new DepartmentType();
        department.setDepartmentType(departmentType);
        when(departmentTypeRepository.findById(departmentType.getId())).thenReturn(Optional.empty());
        when(departmentTypeRepository.save(departmentType)).thenReturn(departmentType);
        when(departmentRepository.save(department)).thenReturn(department);
        Department createdDepartment = departmentService.createDepartment(department);
        assertNotNull(createdDepartment);
    }

    @Test
    public void testUpdateDepartment() throws DepartmentNotFound {
        Long departmentId = 1L;
        Department department = new Department();
        DepartmentType departmentType = new DepartmentType();
        department.setDepartmentType(departmentType);
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        when(departmentTypeRepository.findById(departmentType.getId())).thenReturn(Optional.of(departmentType));
        when(departmentRepository.save(department)).thenReturn(department);
        Department updatedDepartment = departmentService.updateDepartment(department, departmentId);
        assertNotNull(updatedDepartment);
    }

    @Test
    public void testDeleteDepartment() {
        Long departmentId = 1L;
        doNothing().when(departmentRepository).deleteById(departmentId);
        departmentService.deleteDepartment(departmentId);
        verify(departmentRepository, times(1)).deleteById(departmentId);
    }
}
