package com.dosecare.entity;

import com.dosecare.enums.NotificationChannel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "reminder_logs")
public class ReminderLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DoseLog doseLog;

    @Enumerated(EnumType.STRING)
    private NotificationChannel channel;

    private LocalDateTime sentAt;

    private String deliveryStatus;

    @Column(length = 500)
    private String providerResponse;
}

