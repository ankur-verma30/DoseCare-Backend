package com.dosecare.service;

import com.dosecare.dto.request.CreatePatientRequest;
import com.dosecare.dto.response.PatientResponse;

import java.util.List;

public interface PatientService {

    public PatientResponse createPatient(CreatePatientRequest createPatientRequest,Long caregiverId) throws Exception;


    public List<PatientResponse> getMyPatients(Long caregiverId);

    public PatientResponse getPatientById(Long patientId, Long caregiverId);

    public PatientResponse updatePatient(Long patientId, Long caregiverId, CreatePatientRequest createPatientRequest);



}
