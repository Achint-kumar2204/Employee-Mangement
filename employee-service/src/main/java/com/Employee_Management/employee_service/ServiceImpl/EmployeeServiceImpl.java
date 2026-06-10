package com.Employee_Management.employee_service.ServiceImpl;

import com.Employee_Management.employee_service.Client.DepartmentClient;
import com.Employee_Management.employee_service.DTO.DepartmentDTO;
import com.Employee_Management.employee_service.DTO.EmployeeDTO;
import com.Employee_Management.employee_service.DTO.EmployeeResponseDTO;
import com.Employee_Management.employee_service.Entities.Employee;
import com.Employee_Management.employee_service.Exceptions.EmployeeNotFoundException;
import com.Employee_Management.employee_service.Exceptions.InvalidDepartmentException;
import com.Employee_Management.employee_service.Repositories.EmployeeRepo;
import com.Employee_Management.employee_service.Services.EmployeeService;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo repository;

    private final DepartmentClient departmentClient;

    public EmployeeServiceImpl(
            EmployeeRepo repository,
            DepartmentClient departmentClient) {

        this.repository = repository;
        this.departmentClient = departmentClient;
    }

    @Override
    public EmployeeDTO create(EmployeeDTO dto) {

        try {
            departmentClient.getDepartmentById(
                    dto.getDepartmentId());
        }
        catch (FeignException.NotFound ex) {
            throw new InvalidDepartmentException(
                    "Department does not exist");
        }

        Employee employee = new Employee();

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartmentId(
                dto.getDepartmentId());

        Employee saved =
                repository.save(employee);

        return new EmployeeDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getDepartmentId());
    }

    @Override
    public List<EmployeeDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(emp -> new EmployeeDTO(
                        emp.getId(),
                        emp.getName(),
                        emp.getEmail(),
                        emp.getDepartmentId()))
                .toList();
    }

    @Override
    public EmployeeDTO getById(Long id) {

        Employee employee =
                repository.findById(id)
                        .orElseThrow(() ->
                                new EmployeeNotFoundException(
                                        "Employee not found"));

        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartmentId());
    }

    @Override
    public EmployeeResponseDTO getEmployeeWithDepartment(Long id) {

        Employee employee =
                repository.findById(id)
                        .orElseThrow(() ->
                                new EmployeeNotFoundException(
                                        "Employee not found"));

        DepartmentDTO department =
                departmentClient.getDepartmentById(
                        employee.getDepartmentId());

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                department
        );
    }
}
