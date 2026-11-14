package org.example;

public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientRecord getPatientRecord(String requesterRole, String requesterId, String patientId) {
        boolean isAuthorizedStaff = "DOCTOR".equals(requesterRole) || "NURSE".equals(requesterRole);
        boolean isSelfAccess = "PATIENT".equals(requesterRole) && requesterId.equals(patientId);

        if (!isAuthorizedStaff && !isSelfAccess) {
            throw new SecurityException("Access denied: Role '" + requesterRole + "' is not authorized to view record '" + patientId + "'.");
        }

        PatientRecord record = patientRepository.findById(patientId);
        if (record == null) {
            throw new IllegalArgumentException("Patient record with ID '" + patientId + "' not found.");
        }

        return record;
    }
}

// Supporting Interfaces / Classes (can also be in separate files under src/main/java)
interface PatientRepository {
    PatientRecord findById(String id);
}

class PatientRecord {
    private final String patientId;
    private final String name;
    private final String diagnosis;

    public PatientRecord(String patientId, String name, String diagnosis) {
        this.patientId = patientId;
        this.name = name;
        this.diagnosis = diagnosis;
    }

    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public String getDiagnosis() { return diagnosis; }
}