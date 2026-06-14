package com.Employee_Management.employee_service.Controller;

import com.Employee_Management.employee_service.DTO.EmployeeDTO;
import com.Employee_Management.employee_service.DTO.EmployeeResponseDTO;
import com.Employee_Management.employee_service.Services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(
        name = "Employee APIs",
        description = "Operations related to Employees"
)
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(
            EmployeeService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create Employee",
            description = "Creates a new Employee by accepting DTO in request body"
    )
    @PostMapping
    public EmployeeDTO create(
            @Valid
            @RequestBody EmployeeDTO dto) {

        return service.create(dto);
    }

    @Operation(
            summary = "Get All Employees",
            description = "Fetches all Employees and returns list of DTOs"
    )
    @GetMapping
    public List<EmployeeDTO> getAll() {
        return service.getAll();
    }

    @Operation(
            summary = "Get Employee By Id",
            description = "Fetches a Employee using its id and returns the DTO"
    )
    @GetMapping("/{id}")
    public EmployeeDTO getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @Operation(
            summary = "Get Employee By Id with department details",
            description = "Fetches a Employee using its id with department details and returns the DTO"
    )
    @GetMapping("/{id}/details")
    public EmployeeResponseDTO getEmployeeDetails(
            @PathVariable Long id) {

        return service.getEmployeeWithDepartment(id);
    }
}
