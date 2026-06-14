package com.Employee_Management.department_service.Service.Impl;

import com.Employee_Management.department_service.DTO.DepartmentDTO;
import com.Employee_Management.department_service.Entities.Department;
import com.Employee_Management.department_service.Exception.DepartmentNotFoundException;
import com.Employee_Management.department_service.Repositories.DepartmentRepo;
import com.Employee_Management.department_service.Services.DepartmentService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo repository;

    public DepartmentServiceImpl(DepartmentRepo repository) {
        this.repository = repository;
    }

    @Override
    public DepartmentDTO create(DepartmentDTO dto) {
        Department department = new Department();

        department.setName(dto.getName());
        department.setCode(dto.getCode());

        Department saved = repository.save(department);

        return new DepartmentDTO(
                saved.getId(),
                saved.getName(),
                saved.getCode()
        );
    }

    @Override
    public List<DepartmentDTO> getAll() {
        List<Department> department =  repository.findAll();

        return department.stream()
                .map(dept -> new DepartmentDTO(dept.getId(), dept.getName(), dept.getCode()))
                .toList();
    }

    @Cacheable(value = "departments", key = "#id")
    @Override
    public DepartmentDTO getById(Long id) {

        System.out.println("FETCHING FROM DB...");

        Department department =  repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getCode()
        );
    }
}
