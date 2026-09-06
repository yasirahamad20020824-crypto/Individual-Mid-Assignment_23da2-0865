package com.hospital;

import java.util.List;
import java.util.Scanner;

public class HospitalManagementSystem {
    private final PatientBST patientRecords = new PatientBST();
    private final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private final TreatmentStack treatmentHistory = new TreatmentStack();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new HospitalManagementSystem().run();
    }

    private void run() {
        boolean running = true;
        System.out.println("=== Mini Hospital Emergency Management System ===");
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> registerPatient();
                    case 2 -> searchPatient();
                    case 3 -> deletePatient();
                    case 4 -> displayPatients();
                    case 5 -> enqueuePatient();
                    case 6 -> treatNextPatient();
                    case 7 -> displayWaitingPatients();
                    case 8 -> completeTreatment();
                    case 9 -> popTreatment();
                    case 10 -> displayTreatmentHistory();
                    case 11 -> addVisit();
                    case 12 -> removeVisit();
                    case 13 -> searchVisit();
                    case 14 -> displayVisitHistory();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
            System.out.println();
        }
        System.out.println("System closed.");
    }

    private void printMenu() {
        System.out.println("\n1. Register patient          8. Complete treatment");
        System.out.println("2. Search patient            9. Pop latest treatment");
        System.out.println("3. Delete patient           10. Display treatment history");
        System.out.println("4. Display patients          11. Add patient visit");
        System.out.println("5. Enqueue emergency patient 12. Remove patient visit");
        System.out.println("6. Treat next patient        13. Search patient visit");
        System.out.println("7. Display waiting queue     14. Display visit history");
        System.out.println("0. Exit");
    }

    private void registerPatient() {
        Patient patient = new Patient(readInt("Patient ID: "), readText("Name: "), readInt("Age: "),
                readText("Contact number: "), readText("Medical condition: "));
        if (patientRecords.insert(patient)) {
            System.out.println("Patient registered successfully.");
        } else {
            System.out.println("A patient with that ID already exists.");
        }
    }

    private void searchPatient() {
        Patient patient = patientRecords.search(readInt("Patient ID: "));
        System.out.println(patient == null ? "Patient not found." : patient);
    }

    private void deletePatient() {
        System.out.println(patientRecords.delete(readInt("Patient ID: "))
                ? "Patient deleted successfully." : "Patient not found.");
    }

    private void displayPatients() {
        printList(patientRecords.inOrder(), "No patient records found.");
    }

    private void enqueuePatient() {
        Patient patient = patientRecords.search(readInt("Patient ID: "));
        if (patient == null) {
            System.out.println("Patient not found. Register the patient first.");
            return;
        }
        emergencyQueue.enqueue(patient);
        System.out.println(patient.getName() + " added to the emergency queue.");
    }

    private void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        System.out.println(patient == null ? "The emergency queue is empty." : "Now treating: " + patient);
    }

    private void displayWaitingPatients() {
        printList(emergencyQueue.toList(), "The emergency queue is empty.");
    }

    private void completeTreatment() {
        Patient patient = patientRecords.search(readInt("Patient ID: "));
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        TreatmentRecord record = new TreatmentRecord(patient.getPatientId(), patient.getName(),
                readText("Treatment provided: "), readText("Completion date (YYYY-MM-DD): "));
        treatmentHistory.push(record);
        System.out.println("Treatment record pushed onto the history stack.");
    }

    private void popTreatment() {
        TreatmentRecord record = treatmentHistory.pop();
        System.out.println(record == null ? "Treatment history is empty." : "Removed: " + record);
    }

    private void displayTreatmentHistory() {
        printList(treatmentHistory.toList(), "Treatment history is empty.");
    }

    private void addVisit() {
        Patient patient = findPatientForVisit();
        if (patient == null) {
            return;
        }
        Visit visit = new Visit(readInt("Visit ID: "), readText("Visit date: "), readText("Doctor name: "),
                readText("Diagnosis: "), readText("Treatment: "));
        System.out.println(patient.getVisitHistory().add(visit)
                ? "Visit added to patient history." : "A visit with that ID already exists.");
    }

    private void removeVisit() {
        Patient patient = findPatientForVisit();
        if (patient != null) {
            System.out.println(patient.getVisitHistory().remove(readInt("Visit ID: "))
                    ? "Visit removed." : "Visit not found.");
        }
    }

    private void searchVisit() {
        Patient patient = findPatientForVisit();
        if (patient != null) {
            Visit visit = patient.getVisitHistory().find(readInt("Visit ID: "));
            System.out.println(visit == null ? "Visit not found." : visit);
        }
    }

    private void displayVisitHistory() {
        Patient patient = findPatientForVisit();
        if (patient != null) {
            printList(patient.getVisitHistory().toList(), "No visits found for this patient.");
        }
    }

    private Patient findPatientForVisit() {
        Patient patient = patientRecords.search(readInt("Patient ID: "));
        if (patient == null) {
            System.out.println("Patient not found.");
        }
        return patient;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        if (value.isBlank()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }
        return value;
    }

    private <T> void printList(List<T> values, String emptyMessage) {
        if (values.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        values.forEach(System.out::println);
    }
}
