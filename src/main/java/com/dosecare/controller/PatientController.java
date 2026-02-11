package com.dosecare.controller;


import com.dosecare.dto.request.CreatePatientRequest;
import com.dosecare.dto.response.PatientResponse;
import com.dosecare.service.PatientService;
import com.dosecare.utils.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
@PreAuthorize("hasRole('CAREGIVER')")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@RequestBody CreatePatientRequest createPatientRequest,
                                                         @AuthenticationPrincipal UserPrincipal userPrincipal) throws Exception {
        return ResponseEntity.ok(patientService.createPatient(createPatientRequest,userPrincipal.getId()));
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long patientId,
                                                         @RequestBody CreatePatientRequest createPatientRequest,
    @AuthenticationPrincipal  UserPrincipal userPrincipal) {
        return ResponseEntity.ok(patientService.updatePatient(patientId, userPrincipal.getId(),createPatientRequest));
    }


    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable Long patientId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(patientService.getPatientById(patientId,userPrincipal.getId()));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getMyPatients(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(patientService.getMyPatients(userPrincipal.getId()));
    }
}
