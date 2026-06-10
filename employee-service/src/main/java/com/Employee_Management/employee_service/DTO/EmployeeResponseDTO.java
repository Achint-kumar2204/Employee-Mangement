package com.Employee_Management.employee_service.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Long id;

    private String name;

    private String email;

    private DepartmentDTO department;
}
