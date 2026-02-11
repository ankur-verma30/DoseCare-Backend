package com.dosecare.entity;

import com.dosecare.enums.BloodGroup;
import com.dosecare.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


//    user is mapped with patient
    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    private LocalDate dateOfBirth;

    private Gender gender;

    private BloodGroup bloodGroup;

    @Column(length = 1000)
    private String medicalNotes;

    private String emergencyContactName;

    private String emergencyContactPhone;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }

}
