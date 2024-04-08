package com.mdfaysalhossain.SMS.With.Maven.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "paymentTanle")

public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long pid;

    private String pclass;

    private String pRoll;

    private Date pDate;

    private String pMonth;

    @ManyToOne
    @JoinColumn(name = "Sid")
    private  StudentAddModel studentAddModel;


    @ManyToOne
    @JoinColumn(name = "tid")
    private  TeacherAddModel teacherAddModel;

    @ManyToOne
    @JoinColumn(name = "cid")
    private  ClassModel classModel;
}
