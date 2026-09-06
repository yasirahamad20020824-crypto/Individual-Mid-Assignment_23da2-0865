package com.hospital;

public record TreatmentRecord(int patientId, String patientName, String treatment, String completionDate) {
    public TreatmentRecord {
        if (patientId <= 0) {
            throw new IllegalArgumentException("Patient ID must be positive.");
        }
        if (patientName == null || patientName.isBlank() || treatment == null || treatment.isBlank()
                || completionDate == null || completionDate.isBlank()) {
            throw new IllegalArgumentException("Treatment record fields cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId + " | Name: " + patientName + " | Treatment: " + treatment
                + " | Completed: " + completionDate;
    }
}
