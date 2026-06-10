package com.Employee_Management.department_service.Controllers;


import com.Employee_Management.department_service.DTO.DepartmentDTO;
import com.Employee_Management.department_service.Entities.Department;
import com.Employee_Management.department_service.Services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public DepartmentDTO create(
            @Valid @RequestBody DepartmentDTO dto) {

        return service.create(dto);
    }

    @GetMapping
    public List<DepartmentDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DepartmentDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
