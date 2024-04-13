package com.example.managingtransactions.controllers;


import com.example.managingtransactions.controller.DepartmentController;
import com.example.managingtransactions.model.Department;
import com.example.managingtransactions.model.DepartmentType;
import com.example.managingtransactions.services.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DepartmentController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepartmentControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Department> sampleDepartments;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Department financeDepartment = new Department(1L, new DepartmentType(1L, "Finance", "Handles financial matters"));
        Department hrDepartment = new Department(2L, new DepartmentType(2L, "HR", "Handles human resources"));
        sampleDepartments = Arrays.asList(financeDepartment, hrDepartment);

    }

    @Test
    public void deleteDepartment_ShouldReturnSuccessResponse() throws Exception {
        willDoNothing().given(departmentService).deleteDepartment(sampleDepartments.get(0).getId());

        mockMvc.perform(delete("/department/{id}", sampleDepartments.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("DEPARTMENT_DELETED_SUCCESSFULLY"));
    }

    @Test
    public void getAllDepartments_ShouldReturnAllDepartments() throws Exception {
        List<Department> allDepartments = sampleDepartments;
        given(departmentService.getAllDepartments()).willReturn(allDepartments);

        mockMvc.perform(get("/department"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].departmentType.name").value("Finance"))
                .andExpect(jsonPath("$[1].departmentType.name").value("HR"));
    }

    @Test
    public void getDepartment_ShouldReturnDepartmentById() throws Exception {
        given(departmentService.getDepartment(sampleDepartments.get(0).getId())).willReturn(sampleDepartments.get(0));

        mockMvc.perform(get("/department/{id}", sampleDepartments.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(sampleDepartments.get(0).getId()))
                .andExpect(jsonPath("$.departmentType.name").value("Finance"));
    }

    @Test
    public void addNewDepartment_ShouldCreateDepartment() throws Exception {
        given(departmentService.createDepartment(any(Department.class))).willReturn(sampleDepartments.get(0));

        mockMvc.perform(post("/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleDepartments.get(0))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(sampleDepartments.get(0).getId()));
    }

    @Test
    public void updateDepartment_ShouldUpdateDepartment() throws Exception {
        given(departmentService.updateDepartment(any(Department.class), eq(sampleDepartments.get(0).getId()))).willReturn(sampleDepartments.get(0));

        mockMvc.perform(put("/department/{id}", sampleDepartments.get(0).getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleDepartments.get(0))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleDepartments.get(0).getId()));
    }

}