package com.mdfaysalhossain.SMS.With.Maven.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "TeacherInfoTable")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "teacherTable")

public class TeacherAddModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    @Column(nullable = false)
    private String tname;

    @Column(unique = true, nullable = false)
    private String temail;

    @Column(nullable = false)
    private String tpassword;

    private String tphone;

    private String tfathersname;

    private String tmothersname;

    private String tgender;

    private String tdob;

    private String tjoiningDate;

    private String tsalary;

    private  String trole;

    private String tphoto;

    private String tcv;

    private String tdesignation;


    @OneToMany
    @MapKeyColumn(name = "pid")
    private List<PaymentModel> paymentModel;

}
