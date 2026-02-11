package com.dosecare.entity;

import com.dosecare.enums.DoseStatus;
import com.dosecare.enums.NotificationChannel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "dose_logs")
public class DoseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Medication medication;

    private LocalDateTime scheduledTime;
    private LocalDateTime responseTime;

    @Enumerated(EnumType.STRING)
    private DoseStatus status;

    @Enumerated(EnumType.STRING)
    private NotificationChannel responseSource;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
