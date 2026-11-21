package com.datheon.studentgrade;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GradeManager gradeManager = new GradeManager();

        while (true) {

            System.out.println("\n===== Student Grade Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Record Marks");
            System.out.println("3. Compute Class Average & Median");
            System.out.println("4. Generate Report Cards");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();

                    try {
                        Student student = new Student(id, name);
                        gradeManager.addStudent(student);

                        System.out.println("Student added successfully.");

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();

                    System.out.print("Enter Subject: ");
                    String subject = scanner.nextLine();

                    System.out.print("Enter Marks: ");
                    double score = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        boolean recorded =
                                gradeManager.recordMarks(
                                        studentId,
                                        subject,
                                        score
                                );

                        if (recorded) {
                            System.out.println(
                                    "Marks recorded successfully."
                            );
                        } else {
                            System.out.println("Student not found.");
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {
                    double classAverage =
                            gradeManager.calculateClassAverage();

                    double classMedian =
                            gradeManager.calculateClassMedian();

                    System.out.printf(
                            "Class Average: %.2f%n",
                            classAverage
                    );

                    System.out.printf(
                            "Class Median : %.2f%n",
                            classMedian
                    );
                }

                case 4 -> {
                    gradeManager.generateReportCards();
                }

                case 5 -> {
                    System.out.println(
                            "Thank you for using Student Grade Management System."
                    );

                    scanner.close();
                    return;
                }

                default -> {
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
                }
            }
        }
    }
}
