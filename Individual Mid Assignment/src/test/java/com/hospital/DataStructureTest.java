package com.hospital;

import java.util.List;

public class DataStructureTest {
    public static void main(String[] args) {
        testPatientBst();
        testEmergencyQueue();
        testTreatmentStack();
        testVisitHistory();
        System.out.println("All data structure tests passed.");
    }

    private static void testPatientBst() {
        PatientBST tree = new PatientBST();
        Patient patient30 = patient(30);
        Patient patient10 = patient(10);
        Patient patient40 = patient(40);
        Patient patient5 = patient(5);
        assertTrue(tree.insert(patient30), "root insertion");
        assertTrue(tree.insert(patient10), "left insertion");
        assertTrue(tree.insert(patient40), "right insertion");
        assertTrue(tree.insert(patient5), "nested insertion");
        assertFalse(tree.insert(patient(10)), "duplicate IDs are rejected");
        assertEquals(List.of(patient5, patient10, patient30, patient40), tree.inOrder(), "in-order traversal");
        assertEquals(patient40, tree.search(40), "BST search");
        assertTrue(tree.delete(30), "delete node with two children");
        assertTrue(tree.search(30) == null, "deleted patient is absent");
        assertFalse(tree.delete(999), "missing patient deletion");
    }

    private static void testEmergencyQueue() {
        EmergencyQueue queue = new EmergencyQueue();
        Patient first = patient(1);
        Patient second = patient(2);
        assertTrue(queue.dequeue() == null, "empty queue dequeue");
        queue.enqueue(first);
        queue.enqueue(second);
        assertEquals(first, queue.dequeue(), "queue FIFO first item");
        assertEquals(second, queue.dequeue(), "queue FIFO second item");
        assertTrue(queue.isEmpty(), "queue empty after dequeue");
    }

    private static void testTreatmentStack() {
        TreatmentStack stack = new TreatmentStack();
        TreatmentRecord first = record(1);
        TreatmentRecord second = record(2);
        assertTrue(stack.pop() == null, "empty stack pop");
        stack.push(first);
        stack.push(second);
        assertEquals(second, stack.pop(), "stack LIFO latest item");
        assertEquals(first, stack.pop(), "stack LIFO earlier item");
        assertTrue(stack.isEmpty(), "stack empty after pop");
    }

    private static void testVisitHistory() {
        VisitHistory history = new VisitHistory();
        Visit first = visit(1);
        Visit second = visit(2);
        assertTrue(history.add(first), "first visit insertion");
        assertTrue(history.add(second), "second visit insertion");
        assertFalse(history.add(visit(1)), "duplicate visit IDs are rejected");
        assertEquals(first, history.find(1), "visit search");
        assertTrue(history.remove(1), "visit removal");
        assertTrue(history.find(1) == null, "removed visit is absent");
        assertEquals(List.of(second), history.toList(), "linked list history");
        assertFalse(history.remove(999), "missing visit removal");
    }

    private static Patient patient(int id) {
        return new Patient(id, "Patient " + id, 20, "0700000000", "Check-up");
    }

    private static Visit visit(int id) {
        return new Visit(id, "2026-09-01", "Dr. Test", "Routine", "Observation");
    }

    private static TreatmentRecord record(int id) {
        return new TreatmentRecord(id, "Patient " + id, "Medication", "2026-09-01");
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + ": expected " + expected + ", got " + actual);
        }
    }
}
