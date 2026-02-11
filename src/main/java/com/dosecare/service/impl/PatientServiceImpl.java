package com.dosecare.service.impl;

import com.dosecare.dto.request.CreatePatientRequest;
import com.dosecare.dto.response.PatientResponse;
import com.dosecare.entity.Patient;
import com.dosecare.entity.User;
import com.dosecare.repository.PatientRepository;
import com.dosecare.repository.UserRepository;
import com.dosecare.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    @Override
    public PatientResponse createPatient(CreatePatientRequest createPatientRequest, Long caregiverId) throws Exception {
        User caregiver = userRepository.findById(caregiverId).orElseThrow(() -> new Exception("Caregiver not found"));


        Patient patient=Patient.builder()
                .fullName(createPatientRequest.getFullName())
                .dateOfBirth(createPatientRequest.getDateOfBirth())
                .gender(createPatientRequest.getGender())
                .bloodGroup(createPatientRequest.getBloodGroup())
                .medicalConditions(createPatientRequest.getMedicalConditions())
                .emergencyContactName(createPatientRequest.getEmergencyContactName())
                .emergencyContactPhone(createPatientRequest.getEmergencyContactPhone())
                .caregiver(caregiver)
                .build();


        patientRepository.save(patient);
        return mapToResponse(patient);
    }


    @Override
    public List<PatientResponse> getMyPatients(Long caregiverId) {
        return patientRepository.findByCaregiverId(caregiverId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PatientResponse getPatientById(Long patientId, Long caregiverId) {
        Patient patient = patientRepository.findByCaregiverIdAndId(patientId, caregiverId).orElseThrow(() -> new RuntimeException(
                "Patient not found or unauthorized"));

        return mapToResponse(patient);
    }


    @Override
    public PatientResponse updatePatient(Long patientId, Long caregiverId, CreatePatientRequest createPatientRequest) {
       Patient patient = patientRepository.findByCaregiverIdAndId(patientId, caregiverId).orElseThrow(() -> new RuntimeException(
               "Patient not found or unauthorized"));

       patient.setFullName(createPatientRequest.getFullName());
       patient.setDateOfBirth(createPatientRequest.getDateOfBirth());
       patient.setGender(createPatientRequest.getGender());
       patient.setBloodGroup(createPatientRequest.getBloodGroup());
       patient.setMedicalConditions(createPatientRequest.getMedicalConditions());
       patient.setEmergencyContactName(createPatientRequest.getEmergencyContactName());
       patient.setEmergencyContactPhone(createPatientRequest.getEmergencyContactPhone());

       patientRepository.save(patient);
       return mapToResponse(patient);
    }


    private PatientResponse mapToResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .bloodGroup(patient.getBloodGroup())
                .medicalConditions(patient.getMedicalConditions())
                .emergencyContactName(patient.getEmergencyContactName())
                .emergencyContactPhone(patient.getEmergencyContactPhone())
                .caregiverId(patient.getCaregiver().getId())
                .build();
    }

}

