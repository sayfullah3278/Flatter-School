package com.mdfaysalhossain.SMS.With.Maven.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "examRutineTable")
public class ExamRutineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long exid;

    private String exClass;
    private String exTime;

    private String exSubject1;
    private String exdate1;

    private String exSubject2;
    private String exdate2;

    private String exSubject3;
    private String exdate3;

    private String exSubject4;
    private String exdate4;

    private String exSubject5;
    private String exdate5;

    private String exSubject6;
    private String exdate6;

    private String exSubject7;
    private String exdate7;




    @OneToMany
    private List<SubjectModel> subjectModel;



}
