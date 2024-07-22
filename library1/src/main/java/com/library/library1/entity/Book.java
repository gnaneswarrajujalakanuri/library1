package com.library.library1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_seq")
    @SequenceGenerator(name = "book_seq" , sequenceName = "book_seq" , allocationSize = 1)
    private Long b_id;
    private String title;
    private String author;
    private String publisher;
    private Long owner_id;
}
