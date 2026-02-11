package com.dosecare.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "adherence_summary")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class AdherenceSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    private LocalDate periodStart;
    private LocalDate periodEnd;

    private int totalDoses;
    private int takenDoses;
    private double adherencePercentage;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

