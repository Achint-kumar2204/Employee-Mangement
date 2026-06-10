package com.Employee_Management.employee_service.Services;


import com.Employee_Management.employee_service.DTO.EmployeeDTO;
import com.Employee_Management.employee_service.DTO.EmployeeResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO create(EmployeeDTO dto);

    List<EmployeeDTO> getAll();

    EmployeeDTO getById(Long id);

    EmployeeResponseDTO getEmployeeWithDepartment(Long id);
}
