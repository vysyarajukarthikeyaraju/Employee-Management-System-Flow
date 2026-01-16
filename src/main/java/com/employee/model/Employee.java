
package com.employee.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Employee {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 private String email;
 private Double salary;
 private LocalDate joiningDate;

 @ManyToOne
 private Department department;
}
