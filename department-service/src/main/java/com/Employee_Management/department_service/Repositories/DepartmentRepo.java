package com.Employee_Management.department_service.Repositories;

import com.Employee_Management.department_service.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
