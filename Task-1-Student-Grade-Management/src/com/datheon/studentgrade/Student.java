package com.datheon.studentgrade;

import java.util.LinkedHashMap;
import java.util.Map;

public class Student {

    private String id;
    private String name;
    private Map<String, Double> grades;

    public Student(String id, String name) {
        setId(id);
        setName(name);
        grades = new LinkedHashMap<>();
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    public void setGrade(String subject, double score) {

        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be empty");
        }

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                    "Score must be between 0 and 100"
            );
        }

        grades.put(subject, score);
    }

    public double calculateTotal() {
        double total = 0;

        for (double score : grades.values()) {
            total += score;
        }

        return total;
    }

    public double calculateAverage() {

        if (grades.isEmpty()) {
            return 0;
        }

        return calculateTotal() / grades.size();
    }

    public String calculateGrade() {

        double average = calculateAverage();

        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public double calculateGPA() {

        String grade = calculateGrade();

        switch (grade) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            default:
                return 0.0;
        }
    }
}
