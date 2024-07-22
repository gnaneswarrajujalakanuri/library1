package com.library.library1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq" , sequenceName = "employee_seq" , allocationSize = 1)
    private Long e_id;
    private String e_name;
    private String e_phone;
    private String e_email;
    private String e_password;
}
