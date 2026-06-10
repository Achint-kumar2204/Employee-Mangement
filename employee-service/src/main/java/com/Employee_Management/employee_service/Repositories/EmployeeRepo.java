package com.Employee_Management.employee_service.Repositories;

import com.Employee_Management.employee_service.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
