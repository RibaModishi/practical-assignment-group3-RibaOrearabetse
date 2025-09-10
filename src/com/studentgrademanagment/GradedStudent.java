package com.studentgrademanagment;

public class GradedStudent extends Student {
    private double[] grades;
    private String[] subjects;
    
    public GradedStudent(String studentId, String firstName, String lastName, 
                        int age, String[] subjects, double[] grades) {
        super(studentId, firstName, lastName, age);
        this.subjects = subjects;
        this.grades = grades;
    }
    
    public double[] getGrades() { return grades.clone(); } // Return copy for information hiding
    public String[] getSubjects() { return subjects.clone(); } // Return copy for information hiding
    
    public double calculateAverage() {
        return GradeCalculator.calculateAverage(grades);
    }
    
    public String getPassStatus() {
        return GradeCalculator.getPassStatus(calculateAverage());
    }
    
    public String getGradeReport() {
        StringBuilder report = new StringBuilder();
        report.append("Student: ").append(getFullName()).append("\n");
        report.append("ID: ").append(getStudentId()).append("\n");
        report.append("Age: ").append(getAge()).append("\n");
        report.append("--------------------------------\n");
        
        for (int i = 0; i < subjects.length; i++) {
            report.append(String.format("%-15s: %6.2f%%\n", subjects[i], grades[i]));
        }
        
        report.append("--------------------------------\n");
        report.append(String.format("Average: %12.2f%%\n", calculateAverage()));
        report.append("Status: ").append(getPassStatus());
        
        return report.toString();
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Average: " + String.format("%.2f%%", calculateAverage());
    }
}