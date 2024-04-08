package com.mdfaysalhossain.SMS.With.Maven.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "attendanceTable")
public class StuAttendanceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aid;

    private  long ssid;


    private   String aDates;


    private  String aattendance;


    @ManyToOne
    @JoinColumn(name = "sid", referencedColumnName = "sid")
    private StudentAddModel student;

}
