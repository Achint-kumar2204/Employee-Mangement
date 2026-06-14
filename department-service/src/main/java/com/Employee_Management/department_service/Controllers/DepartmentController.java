package com.Employee_Management.department_service.Controllers;


import com.Employee_Management.department_service.DTO.DepartmentDTO;
import com.Employee_Management.department_service.Entities.Department;
import com.Employee_Management.department_service.Services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@Tag(
        name = "Department APIs",
        description = "Operations related to departments"
)
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create Department",
            description = "Creates a new department by accepting DTO in request body"
    )
    @PostMapping
    public DepartmentDTO create(
            @Valid @RequestBody DepartmentDTO dto) {

        return service.create(dto);
    }

    @Operation(
            summary = "Get All Departments",
            description = "Fetches all departments and returns list of DTOs"
    )
    @GetMapping
    public List<DepartmentDTO> getAll() {
        return service.getAll();
    }

    @Operation(
            summary = "Get Department By Id",
            description = "Fetches a department using its id and returns the DTO"
    )
    @GetMapping("/{id}")
    public DepartmentDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
