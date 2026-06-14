package com.Employee_Management.employee_service;


import com.Employee_Management.employee_service.Client.DepartmentClient;
import com.Employee_Management.employee_service.DTO.DepartmentDTO;
import com.Employee_Management.employee_service.DTO.EmployeeDTO;
import com.Employee_Management.employee_service.DTO.EmployeeResponseDTO;
import com.Employee_Management.employee_service.Entities.Employee;
import com.Employee_Management.employee_service.Exceptions.EmployeeNotFoundException;
import com.Employee_Management.employee_service.Repositories.EmployeeRepo;
import com.Employee_Management.employee_service.ServiceImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo repository;

    @Mock
    private DepartmentClient departmentClient;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    void testGetEmployeeWithDeatil(){

        Employee employee = new Employee(
                1L,
                "Achint Kumar",
                "achint@gmail.com",
                1L
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        DepartmentDTO departmentDTO = new DepartmentDTO(
                1L,
                "IT",
                "IT001"
        );

        when(departmentClient.getDepartmentById(1L))
                .thenReturn(departmentDTO);

        EmployeeResponseDTO result = service.getEmployeeWithDepartment(1L);

        assertNotNull(result);

        assertEquals("IT001",result.getDepartment().getCode());

        assertEquals("Achint Kumar", result.getName());

        verify(repository).findById(1L);

        verify(departmentClient).getDepartmentById(1L);

    }

    @Test
    void testEmployeeNotFound(){

        when(repository.findById(100L))
                .thenReturn(Optional.empty());

        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class,
                () -> service.getEmployeeWithDepartment(100L));

        assertEquals("Employee not found",
                exception.getMessage());

        verify(repository).findById(100L);

        verify(departmentClient, never()).getDepartmentById(100L);

    }

}
