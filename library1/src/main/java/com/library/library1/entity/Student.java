package com.library.library1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "student_seq")
    @SequenceGenerator(name = "student_seq" , sequenceName = "student_seq" , allocationSize = 1)
    private Long student_id;

    private String student_name;
    private String student_password;
    private String student_phone;
    private String student_email;

}
