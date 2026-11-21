package com.datheon.studentgrade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class GradeManager {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean recordMarks(String studentId, String subject, double marks) {

        for (Student student : students) {

            if (studentId.equals(student.getId())) {
                student.setGrade(subject, marks);
                return true;
            }
        }

        return false;
    }

    public double calculateClassAverage() {

        if (students.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Student student : students) {
            total += student.calculateAverage();
        }

        return total / students.size();
    }

    public double calculateClassMedian() {

        if (students.isEmpty()) {
            return 0;
        }

        ArrayList<Double> averages = new ArrayList<>();

        for (Student student : students) {
            averages.add(student.calculateAverage());
        }

        Collections.sort(averages);

        int size = averages.size();

        if (size % 2 == 1) {
            return averages.get(size / 2);
        }

        double middle1 = averages.get(size / 2 - 1);
        double middle2 = averages.get(size / 2);

        return (middle1 + middle2) / 2;
    }

    public void generateReportCards() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students) {

            System.out.println("\n==============================================");
            System.out.println("                REPORT CARD");
            System.out.println("==============================================");

            System.out.println("Student ID : " + student.getId());
            System.out.println("Name       : " + student.getName());

            System.out.println("----------------------------------------------");

            System.out.println(
                    String.format("%-20s %-10s", "Subject", "Score")
            );

            System.out.println("----------------------------------------------");

            for (Map.Entry<String, Double> entry :
                    student.getGrades().entrySet()) {

                System.out.println(
                        String.format(
                                "%-20s %-10.2f",
                                entry.getKey(),
                                entry.getValue()
                        )
                );
            }

            System.out.println("----------------------------------------------");

            System.out.println(
                    String.format(
                            "%-20s %-10.2f",
                            "Total",
                            student.calculateTotal()
                    )
            );

            System.out.println(
                    String.format(
                            "%-20s %-10.2f",
                            "Average",
                            student.calculateAverage()
                    )
            );

            System.out.println(
                    String.format(
                            "%-20s %-10s",
                            "Grade",
                            student.calculateGrade()
                    )
            );

            System.out.println(
                    String.format(
                            "%-20s %-10.2f",
                            "GPA",
                            student.calculateGPA()
                    )
            );

            System.out.println("==============================================");
        }
    }
}