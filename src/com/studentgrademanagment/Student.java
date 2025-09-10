package com.studentgrademanagment;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private int age;
    
    public Student(String studentId, String firstName, String lastName, int age) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    
    // Getters with information hiding
    public String getStudentId() { return studentId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + getFullName() + ", Age: " + age;
    }
}