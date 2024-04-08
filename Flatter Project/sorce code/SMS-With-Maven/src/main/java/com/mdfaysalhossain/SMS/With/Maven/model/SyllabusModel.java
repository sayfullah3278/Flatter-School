package com.mdfaysalhossain.SMS.With.Maven.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "syllabusTable")

public class SyllabusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long syid;

    private String sclass;

    private String examCatagory;


    @Column(unique = true)
    private String subject;

    private String pageNo;

    private String discription;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject_id")
    private SubjectModel subjectModel;
}
