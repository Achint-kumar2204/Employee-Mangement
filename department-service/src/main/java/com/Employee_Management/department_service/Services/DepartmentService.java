package com.Employee_Management.department_service.Services;

import com.Employee_Management.department_service.DTO.DepartmentDTO;
import com.Employee_Management.department_service.Entities.Department;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO create(DepartmentDTO dto);

    List<DepartmentDTO> getAll();

    DepartmentDTO getById(Long id);
}
