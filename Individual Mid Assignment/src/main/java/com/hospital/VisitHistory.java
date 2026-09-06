package com.hospital;

import java.util.ArrayList;
import java.util.List;

public class VisitHistory {
    private VisitNode head;

    private static class VisitNode {
        private final Visit visit;
        private VisitNode next;

        private VisitNode(Visit visit) {
            this.visit = visit;
        }
    }

    public boolean add(Visit visit) {
        if (visit == null) {
            throw new IllegalArgumentException("Visit cannot be null.");
        }
        if (find(visit.getVisitId()) != null) {
            return false;
        }
        VisitNode newNode = new VisitNode(visit);
        if (head == null) {
            head = newNode;
            return true;
        }
        VisitNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        return true;
    }

    public Visit find(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }
        return null;
    }

    public boolean remove(int visitId) {
        VisitNode previous = null;
        VisitNode current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public List<Visit> toList() {
        List<Visit> visits = new ArrayList<>();
        VisitNode current = head;
        while (current != null) {
            visits.add(current.visit);
            current = current.next;
        }
        return visits;
    }
}
