package com.mdfaysalhossain.SMS.With.Maven.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "allUserTable")

public class UserModel {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long uid;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(unique = true, nullable = false)
//    private String email;
//
//    @Column(nullable = false)
//    private String password;
//
//    private String strole;


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private int uid;

        @Column(name = "email") // Specify the column name here
        private String email;

        @Column(name = "name") // Specify the column name here
        private String name;

        @Column(name = "password") // Specify the column name here
        private String password;

        @Column(name = "strole") // Specify the column name here
        private String strole;

        // Other fields and methods


}