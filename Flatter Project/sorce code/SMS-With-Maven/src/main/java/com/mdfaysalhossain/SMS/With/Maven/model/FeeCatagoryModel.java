package com.mdfaysalhossain.SMS.With.Maven.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class FeeCatagoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feeid;

    private long ssid;

    private String feeCatagory;

    private String feeMonth;

    private String feeAmount;

    private String paymentDate;

    @PrePersist
    public void setCurrentDate() {
        this.paymentDate = LocalDate.now().toString();
    }

    @ManyToOne
    @JoinColumn(name = "sid")
    private StudentAddModel studentAddModel;


}
