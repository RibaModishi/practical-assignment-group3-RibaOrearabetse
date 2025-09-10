package com.studentgrademanagment;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GradeManagementSystemIT {
    
    private GradeManagementSystem gradeManagementSystem;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @Before
    public void setUp() {
        gradeManagementSystem = new GradeManagementSystem(10);
        System.setOut(new PrintStream(outContent));
    }
    
    @Test
    public void testAddStudent() {
        System.out.println("Executing testAddStudent...");
        String[] subjects = {"Math"};
        double[] grades = {85.0};
        GradedStudent student = new GradedStudent("S001", "John", "Doe", 20, subjects, grades);
        gradeManagementSystem.addStudent(student);
        
        
        String expectedOutput = "Student added successfully!";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testDisplayAllStudents() {
        System.out.println("Executing testDisplayAllStudents...");
        String[] subjects = {"Math"};
        double[] grades = {85.0};
        GradedStudent student1 = new GradedStudent("S001", "John", "Doe", 20, subjects, grades);
        GradedStudent student2 = new GradedStudent("S002", "Jane", "Smith", 21, subjects, grades);
        
        gradeManagementSystem.addStudent(student1);
        gradeManagementSystem.addStudent(student2);
        
        outContent.reset(); 
        gradeManagementSystem.displayAllStudents();
        
        String expectedHeader = "=== ALL STUDENTS ===";
        String student1Output = "1. ID: S001, Name: John Doe, Age: 20, Average: 85.00%";
        String student2Output = "2. ID: S002, Name: Jane Smith, Age: 21, Average: 85.00%";
        
        assertTrue(outContent.toString().contains(expectedHeader));
        assertTrue(outContent.toString().contains(student1Output));
        assertTrue(outContent.toString().contains(student2Output));
    }

    @Test
    public void testDisplayStudentDetails() {
        System.out.println("Executing testDisplayStudentDetails...");
        String[] subjects = {"Math"};
        double[] grades = {85.0};
        GradedStudent student1 = new GradedStudent("S001", "John", "Doe", 20, subjects, grades);
        gradeManagementSystem.addStudent(student1);
        
        outContent.reset();
        gradeManagementSystem.displayStudentDetails(0); 
        
        String expectedHeader = "=== STUDENT DETAILS ===";
        assertTrue(outContent.toString().contains(expectedHeader));
        assertTrue(outContent.toString().contains("Student: John Doe"));
        assertTrue(outContent.toString().contains("Average:"));
        
        outContent.reset();
        gradeManagementSystem.displayStudentDetails(99); 
        String expectedError = "Invalid student index!";
        assertTrue(outContent.toString().contains(expectedError));
    }

    @Test
    public void testGenerateClassReport() {
        System.out.println("Executing testGenerateClassReport...");
        String[] subjects = {"Math"};
        double[] grades1 = {90.0};
        double[] grades2 = {60.0};
        GradedStudent student1 = new GradedStudent("S001", "John", "Doe", 20, subjects, grades1);
        GradedStudent student2 = new GradedStudent("S002", "Jane", "Smith", 21, subjects, grades2);
        
        gradeManagementSystem.addStudent(student1);
        gradeManagementSystem.addStudent(student2);
        
        outContent.reset();
        gradeManagementSystem.generateClassReport();
        
        String expectedReportHeader = "CLASS GRADE REPORT";
        String expectedTotalStudents = "Total Students: 2";
        String expectedDistinctions = "Distinctions: 1";
        String expectedPassed = "Passed: 1";
        String expectedFailed = "Failed: 0";
        
        assertTrue(outContent.toString().contains(expectedReportHeader));
        assertTrue(outContent.toString().contains(expectedTotalStudents));
        assertTrue(outContent.toString().contains(expectedDistinctions));
        assertTrue(outContent.toString().contains(expectedPassed));
        assertTrue(outContent.toString().contains(expectedFailed));
    }
}