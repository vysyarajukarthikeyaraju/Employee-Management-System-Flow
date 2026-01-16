package com.employee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentDTO {

    @NotBlank(message = "Department name must not be empty")
    private String name;

    @NotBlank(message = "Department location must not be empty")
    private String location;
}
