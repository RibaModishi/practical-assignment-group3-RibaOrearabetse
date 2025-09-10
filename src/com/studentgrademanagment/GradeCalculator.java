package com.studentgrademanagment;

public class GradeCalculator {
    
    /**
     * Calculates the average of student grades
     * @param grades - array of student grades (0-100 scale)
     * @return average grade, returns 0 if no grades provided
     * 
     * This method takes all the grades, adds them up, and divides by 
     * the number of grades to find the average. Pretty straightforward math!
     */
    public static double calculateAverage(double[] grades) {
        // Check if grades array is empty or null - don't want to divide by zero!
        if (grades == null || grades.length == 0) {
            return 0;
        }
        
        // Sum up all the grades 
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        
        // Return the average 
        return sum / grades.length;
    }
    
    /**
     * Determines if student passed based on average grade
     * @param average - the calculated average grade
     * @return "DISTINCTION", "PASS", or "FAIL"
     * 
     * This tells you if you passed or failed. DISTINCTION if you're 
     * really smart (75+), PASS if you did okay (50-74), FAIL if you 
     * need to study more
     */
    public static String getPassStatus(double average) {
        if (average >= 75) {
            return "DISTINCTION";  
        } else if (average >= 50) {
            return "PASS";         
        } else {
            return "FAIL";         
        }
    }
    
    /**
     * Converts numerical score to letter grade
     * @param score - individual assignment/test score (0-100)
     * @return letter grade (A+, A, B, C, D, F)
     * 
     * This gives you that letter grade we all stress about.
     * A+ for perfectionists, F for when you didn't study enough
     */
    public static String getGradeLetter(double score) {
        if (score >= 90) return "A+";  
        if (score >= 80) return "A";   
        if (score >= 70) return "B";   
        if (score >= 60) return "C";   
        if (score >= 50) return "D";   
        return "F";                    
    }
}