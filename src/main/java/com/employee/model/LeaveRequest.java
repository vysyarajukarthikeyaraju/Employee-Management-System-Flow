
package com.employee.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="leave_request")
public class LeaveRequest {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne
 private Employee employee;

 private LocalDate startDate;
 private LocalDate endDate;
 private String status;
 private String reason;
}
