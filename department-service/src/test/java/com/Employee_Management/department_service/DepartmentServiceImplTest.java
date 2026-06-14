package com.Employee_Management.department_service;


import com.Employee_Management.department_service.DTO.DepartmentDTO;
import com.Employee_Management.department_service.Entities.Department;
import com.Employee_Management.department_service.Exception.DepartmentNotFoundException;
import com.Employee_Management.department_service.Repositories.DepartmentRepo;
import com.Employee_Management.department_service.Service.Impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepo repository;

    @InjectMocks
    private DepartmentServiceImpl service;

    @Test
    void testCreateDepartment() {

        // Creating Mock object saved in repo
        DepartmentDTO dto =
                new DepartmentDTO(
                        null,
                        "IT",
                        "IT001");

        Department saved =
                new Department();

        saved.setId(1L);
        saved.setName("IT");
        saved.setCode("IT001");

        // returning the saved mock object
        when(repository.save(any()))
                .thenReturn(saved);

        DepartmentDTO result =
                service.create(dto);

        assertNotNull(result);

        assertEquals(
                "IT",
                result.getName());

        assertEquals(
                "IT001",
                result.getCode());
    }

    @Test
    void testGetDepartmentById() {

        Department department =
                new Department();

        department.setId(1L);
        department.setName("IT");
        department.setCode("IT001");

        when(repository.findById(1L))
                .thenReturn(
                        Optional.of(department));

        DepartmentDTO result =
                service.getById(1L);

        assertNotNull(result);

        assertEquals(
                1L,
                result.getId());

        assertEquals(
                "IT",
                result.getName());

        assertEquals(
                "IT001",
                result.getCode());

        verify(repository)
                .findById(1L);
    }

    @Test
    void testDepartmentNotFound() {

        when(repository.findById(100L))
                .thenReturn(Optional.empty());

        DepartmentNotFoundException exception =
                assertThrows(
                        DepartmentNotFoundException.class,
                        () -> service.getById(100L)
                );

        assertEquals(
                "Department not found",
                exception.getMessage());

        verify(repository)
                .findById(100L);
    }
}
