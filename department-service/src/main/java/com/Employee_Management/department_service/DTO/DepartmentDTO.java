package com.Employee_Management.department_service.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentDTO implements Serializable {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;
}
