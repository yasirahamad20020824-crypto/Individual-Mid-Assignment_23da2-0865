package com.hospital;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class EmergencyQueue {
    private final Queue<Patient> waitingPatients = new ArrayDeque<>();

    public void enqueue(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        waitingPatients.offer(patient);
    }

    public Patient dequeue() {
        return waitingPatients.poll();
    }

    public Patient peek() {
        return waitingPatients.peek();
    }

    public boolean isEmpty() {
        return waitingPatients.isEmpty();
    }

    public int size() {
        return waitingPatients.size();
    }

    public List<Patient> toList() {
        return new ArrayList<>(waitingPatients);
    }
}
