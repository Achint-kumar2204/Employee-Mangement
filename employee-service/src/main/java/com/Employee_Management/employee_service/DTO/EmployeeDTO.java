package com.Employee_Management.employee_service.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO {

    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    private Long departmentId;
}
