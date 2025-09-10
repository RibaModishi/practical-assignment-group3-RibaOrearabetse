package com.studentgrademanagment;

import java.util.Scanner;

public class GradeManagementSystem {
    private GradedStudent[] students;  
    private int studentCount;          
    
    // Constructor - sets up the system with space for students
    public GradeManagementSystem(int capacity) {
        students = new GradedStudent[capacity];  
        studentCount = 0;  
    }
    
    // Add a new student to the system
    public void addStudent(GradedStudent student) {
        if (studentCount < students.length) {  
            students[studentCount] = student;  
            studentCount++;  
            System.out.println("Student added successfully!");  
        } else {
            System.out.println("System is at full capacity! Cannot add more students.");  
        }
    }
    
    // Show all students in the system
    public void displayAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");
        for (int i = 0; i < studentCount; i++) {
            System.out.println((i+1) + ". " + students[i]);  
        }
    }
    
    // Show detailed info about one specific student
    public void displayStudentDetails(int index) {
        if (index >= 0 && index < studentCount) {  
            System.out.println("\n=== STUDENT DETAILS ===");
            System.out.println(students[index].getGradeReport());  
        } else {
            System.out.println("Invalid student index!");  
        }
    }
    
    // Generate a big report for the whole class
    public void generateClassReport() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            CLASS GRADE REPORT");
        System.out.println("=".repeat(50));
        System.out.println("Total Students: " + studentCount);  
        System.out.println("=".repeat(50));
        
        double classTotal = 0;  
        int distinctionCount = 0;  
        int passCount = 0;         
        int failCount = 0;         
        
        // Go through each student
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i].getGradeReport());  
            System.out.println("-".repeat(50));  
            
            double average = students[i].calculateAverage();  
            classTotal += average;  
            
            
            if (average >= 75) {
                distinctionCount++;  
            } else if (average >= 50) {
                passCount++;        
            } else {
                failCount++;        
            }
        }
        
        double classAverage = studentCount > 0 ? classTotal / studentCount : 0;
        
        // Print the summary stats
        System.out.println("CLASS SUMMARY:");
        System.out.printf("Class Average: %.2f%%\n", classAverage);  
        System.out.printf("Distinctions: %d\n", distinctionCount);   
        System.out.printf("Passed: %d\n", passCount);                
        System.out.printf("Failed: %d\n", failCount);                
        System.out.printf("Overall Pass Rate: %.1f%%\n", 
                         (distinctionCount + passCount) * 100.0 / studentCount);  
        System.out.println("=".repeat(50));  
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  
        GradeManagementSystem system = new GradeManagementSystem(50);  
        
        
        initializeSampleData(system);
        
        int choice;
        do {
            // Show the menu options
            System.out.println("\n=== STUDENT GRADE MANAGEMENT SYSTEM ===");
            System.out.println("1. View All Students");
            System.out.println("2. View Student Details");
            System.out.println("3. Add New Student");
            System.out.println("4. Generate Class Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();  
            scanner.nextLine(); 
            
            // Do different things based on user choice
            switch (choice) {
                case 1:
                    system.displayAllStudents();  
                    break;
                case 2:
                    System.out.print("Enter student number: ");
                    int index = scanner.nextInt() - 1;  
                    system.displayStudentDetails(index);  
                    break;
                case 3:
                    addNewStudent(scanner, system);  
                    break;
                case 4:
                    system.generateClassReport();  
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");  
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");  
            }
        } while (choice != 5);  
        
        scanner.close();  
    }
    
    // Add some sample students so we don't start with empty system
    private static void initializeSampleData(GradeManagementSystem system) {
        String[] subjects = {"Mathematics", "Science", "English", "History"};  // Same subjects for everyone
        
        // Sample grades for different students
        double[] grades1 = {85.5, 92.0, 78.5, 88.0};  
        double[] grades2 = {45.0, 60.5, 52.0, 48.5};  
        double[] grades3 = {95.0, 88.5, 91.0, 93.5};  
        double[] grades4 = {75.0, 82.5, 79.0, 80.5};  
        
        
        GradedStudent student1 = new GradedStudent("S001", "John", "Doe", 18, subjects, grades1);
        GradedStudent student2 = new GradedStudent("S002", "Jane", "Smith", 17, subjects, grades2);
        GradedStudent student3 = new GradedStudent("S003", "Bob", "Johnson", 19, subjects, grades3);
        GradedStudent student4 = new GradedStudent("S004", "Alice", "Williams", 18, subjects, grades4);
        
        
        system.addStudent(student1);
        system.addStudent(student2);
        system.addStudent(student3);
        system.addStudent(student4);
    }
    
    private static void addNewStudent(Scanner scanner, GradeManagementSystem system) {
        System.out.println("\n=== ADD NEW STUDENT ===");
        
        // Get student info
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        
        String[] subjects = {"Mathematics", "Science", "English", "History"};  
        double[] grades = new double[subjects.length];  // Array for their grades
        
        // Get grades for each subject
        System.out.println("Enter grades for each subject:");
        for (int i = 0; i < subjects.length; i++) {
            System.out.print(subjects[i] + ": ");
            grades[i] = scanner.nextDouble();  
        }
        scanner.nextLine(); 
        
        GradedStudent newStudent = new GradedStudent(id, firstName, lastName, age, subjects, grades);
        system.addStudent(newStudent);
    }
}