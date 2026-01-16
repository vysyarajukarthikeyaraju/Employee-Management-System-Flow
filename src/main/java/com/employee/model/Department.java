
package com.employee.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Department {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 private String location;
}
