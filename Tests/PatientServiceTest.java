package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    private PatientRepository mockRepo;
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        // Create mock repository
        mockRepo = mock(PatientRepository.class);
        patientService = new PatientService(mockRepo);

        // Setup mock return values
        PatientRecord mockRecord = new PatientRecord("P-1001", "Jane Doe", "Hypertension");
        when(mockRepo.findById("P-1001")).thenReturn(mockRecord);
        when(mockRepo.findById("P-9999")).thenReturn(null);
    }

    @Test
    void testDoctorCanAccessPatientRecord() {
        // TC-1: Authorized doctor access
        PatientRecord result = patientService.getPatientRecord("DOCTOR", "D-501", "P-1001");

        assertNotNull(result);
        assertEquals("P-1001", result.getPatientId());
        assertEquals("Hypertension", result.getDiagnosis());
    }

    @Test
    void testPatientCanAccessOwnRecord() {
        // TC-2: Patient self-access
        PatientRecord result = patientService.getPatientRecord("PATIENT", "P-1001", "P-1001");

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
    }

    @Test
    void testReceptionistDeniedMedicalRecord() {
        // TC-3: Unauthorized role access denial
        SecurityException exception = assertThrows(SecurityException.class, () -> {
            patientService.getPatientRecord("RECEPTIONIST", "R-201", "P-1001");
        });

        assertTrue(exception.getMessage().contains("Access denied"));
    }
}