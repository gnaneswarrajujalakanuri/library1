package com.library.library1.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "bookallotment")
@Data
public class BookAllotment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bookallotment_seq")
    @SequenceGenerator(name = "bookallotment_seq" , sequenceName = "bookallotment_seq",allocationSize = 1)
    private Long ba_id;
    private Long b_id;
    private Long owner_id;
    private Date allot_date;
    private Date return_date;

}
