package com.Employee_Management.employee_service.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO implements Serializable {

    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    private Long departmentId;
}
