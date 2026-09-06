package com.hospital;

import java.util.ArrayList;
import java.util.List;

public class PatientBST {
    private Node root;

    private static class Node {
        private Patient patient;
        private Node left;
        private Node right;

        private Node(Patient patient) {
            this.patient = patient;
        }
    }

    public boolean insert(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        if (root == null) {
            root = new Node(patient);
            return true;
        }
        return insert(root, patient);
    }

    private boolean insert(Node current, Patient patient) {
        if (patient.getPatientId() == current.patient.getPatientId()) {
            return false;
        }
        if (patient.getPatientId() < current.patient.getPatientId()) {
            if (current.left == null) {
                current.left = new Node(patient);
                return true;
            }
            return insert(current.left, patient);
        }
        if (current.right == null) {
            current.right = new Node(patient);
            return true;
        }
        return insert(current.right, patient);
    }

    public Patient search(int patientId) {
        Node current = root;
        while (current != null) {
            if (patientId == current.patient.getPatientId()) {
                return current.patient;
            }
            current = patientId < current.patient.getPatientId() ? current.left : current.right;
        }
        return null;
    }

    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = delete(root, patientId);
        return true;
    }

    private Node delete(Node current, int patientId) {
        if (patientId < current.patient.getPatientId()) {
            current.left = delete(current.left, patientId);
        } else if (patientId > current.patient.getPatientId()) {
            current.right = delete(current.right, patientId);
        } else {
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            Node successor = smallest(current.right);
            current.patient = successor.patient;
            current.right = delete(current.right, successor.patient.getPatientId());
        }
        return current;
    }

    private Node smallest(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public List<Patient> inOrder() {
        List<Patient> patients = new ArrayList<>();
        inOrder(root, patients);
        return patients;
    }

    private void inOrder(Node current, List<Patient> patients) {
        if (current == null) {
            return;
        }
        inOrder(current.left, patients);
        patients.add(current.patient);
        inOrder(current.right, patients);
    }
}
