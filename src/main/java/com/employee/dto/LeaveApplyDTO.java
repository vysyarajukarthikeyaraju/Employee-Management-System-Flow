package com.employee.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LeaveApplyDTO {

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or a future date")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be a future date")
    private LocalDate endDate;

    @NotBlank(message = "Leave reason must not be empty")
    private String reason;
}
