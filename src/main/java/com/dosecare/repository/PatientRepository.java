package com.dosecare.repository;

import com.dosecare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByCaregiverId(Long caregiverId);

    Optional<Patient> findByCaregiverIdAndId(Long caregiverId, Long patientId);

}