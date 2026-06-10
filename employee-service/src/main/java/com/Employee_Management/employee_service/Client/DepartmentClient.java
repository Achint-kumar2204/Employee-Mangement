package com.Employee_Management.employee_service.Client;

import com.Employee_Management.employee_service.DTO.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentClient {

    @GetMapping("/departments/{id}")
    DepartmentDTO getDepartmentById(
            @PathVariable Long id);
}
