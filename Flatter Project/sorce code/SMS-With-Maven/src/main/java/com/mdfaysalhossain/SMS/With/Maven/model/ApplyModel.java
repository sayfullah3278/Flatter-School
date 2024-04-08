package com.mdfaysalhossain.SMS.With.Maven.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Table(name = "applyTable")
public class ApplyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appid;

    private String appFirstName;

    private String applastName;

    private String appEmail;

    private String appPhone;

    private  String appClss;

    private String  appCatogory;

    private String approved;



}
