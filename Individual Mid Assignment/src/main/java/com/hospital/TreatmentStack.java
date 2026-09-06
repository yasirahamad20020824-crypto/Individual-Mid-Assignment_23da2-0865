package com.hospital;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreatmentStack {
    private final Deque<TreatmentRecord> completedTreatments = new ArrayDeque<>();

    public void push(TreatmentRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Treatment record cannot be null.");
        }
        completedTreatments.push(record);
    }

    public TreatmentRecord pop() {
        return completedTreatments.poll();
    }

    public TreatmentRecord peek() {
        return completedTreatments.peek();
    }

    public boolean isEmpty() {
        return completedTreatments.isEmpty();
    }

    public List<TreatmentRecord> toList() {
        return new ArrayList<>(completedTreatments);
    }
}
