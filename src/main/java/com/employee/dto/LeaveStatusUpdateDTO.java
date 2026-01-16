package com.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LeaveStatusUpdateDTO {

    @NotBlank(message = "Leave status is required")
    @Pattern(
        regexp = "APPROVED|REJECTED",
        message = "Status must be either APPROVED or REJECTED"
    )
    private String status;
}
