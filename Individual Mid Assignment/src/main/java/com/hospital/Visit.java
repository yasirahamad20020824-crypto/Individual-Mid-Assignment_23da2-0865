package com.hospital;

public class Visit {
    private final int visitId;
    private final String visitDate;
    private final String doctorName;
    private final String diagnosis;
    private final String treatment;

    public Visit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        if (visitId <= 0) {
            throw new IllegalArgumentException("Visit ID must be positive.");
        }
        this.visitId = visitId;
        this.visitDate = requireText(visitDate, "Visit date");
        this.doctorName = requireText(doctorName, "Doctor name");
        this.diagnosis = requireText(diagnosis, "Diagnosis");
        this.treatment = requireText(treatment, "Treatment");
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    public int getVisitId() {
        return visitId;
    }

    @Override
    public String toString() {
        return "Visit ID: " + visitId + " | Date: " + visitDate + " | Doctor: " + doctorName
                + " | Diagnosis: " + diagnosis + " | Treatment: " + treatment;
    }
}
