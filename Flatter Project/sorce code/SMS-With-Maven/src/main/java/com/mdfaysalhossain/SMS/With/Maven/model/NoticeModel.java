package com.mdfaysalhossain.SMS.With.Maven.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "noticeTable")


public class NoticeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long nid;

    private String ntitel;

    private String nShortDiscription;

    private String nlongDiscription;

    private String nImage;


}