package com.studentgrademanagment;

public class GradeCalculator {
    
    public static double calculateAverage(double[] grades) {
        if (grades == null || grades.length == 0) {
            return 0;
        }
        
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }
    
    public static String getPassStatus(double average) {
        if (average >= 75) {
            return "DISTINCTION";
        } else if (average >= 50) {
            return "PASS";
        } else {
            return "FAIL";
        }
    }
    
    public static String getGradeLetter(double score) {
        if (score >= 90) return "A+";
        if (score >= 80) return "A";
        if (score >= 70) return "B";
        if (score >= 60) return "C";
        if (score >= 50) return "D";
        return "F";
    }
}