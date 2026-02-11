package com.dosecare.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "medications")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String medicationName;

    private String dosage;

    private String instructions;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;


    @CreationTimestamp
    @Column(updatable = false)
    private String createdAt;


    @UpdateTimestamp
    private String updatedAt;


}
