package com.Employee_Management.employee_service.Controller;

import com.Employee_Management.employee_service.DTO.EmployeeDTO;
import com.Employee_Management.employee_service.DTO.EmployeeResponseDTO;
import com.Employee_Management.employee_service.Services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(
            EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeDTO create(
            @Valid
            @RequestBody EmployeeDTO dto) {

        return service.create(dto);
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getById(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @GetMapping("/{id}/details")
    public EmployeeResponseDTO getEmployeeDetails(
            @PathVariable Long id) {

        return service.getEmployeeWithDepartment(id);
    }
}
