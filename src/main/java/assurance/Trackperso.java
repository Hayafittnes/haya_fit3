package assurance;

import java.util.HashMap;
import java.util.HashSet;

public class Trackperso {
    private double weight;
    private double bmi;
    private final HashMap<String, HashMap<String, String>> attendanceRecords;
 // Static collection to track enrolled programs
    private static HashSet<String> enrolledPrograms = new HashSet<>();
    public Trackperso() {
        this.attendanceRecords = new HashMap<>();
    }

    // Update weight
    public void updateWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    // Update BMI
    public void updateBMI(double bmi) {
        if (bmi > 0) {
            this.bmi = bmi;
        } else {
            throw new IllegalArgumentException("BMI must be positive.");
        }
    }

    public double getBMI() {
        return bmi;
    }

    // Mark attendance
    public void markAttendance(String program, String date, String status) {
        if (program == null || date == null || status == null) {
            throw new IllegalArgumentException("Program, date, and status cannot be null.");
        }
        attendanceRecords.putIfAbsent(program, new HashMap<>());
        attendanceRecords.get(program).put(date, status);
    }

    public String getAttendance(String program, String date) {
        if (program == null || date == null) {
            throw new IllegalArgumentException("Program and date cannot be null.");
        }
        return attendanceRecords.getOrDefault(program, new HashMap<>()).getOrDefault(date, "No record");
    }

    public int getTotalAttendance(String program) {
        if (program == null) {
            throw new IllegalArgumentException("Program cannot be null.");
        }
        return attendanceRecords.getOrDefault(program, new HashMap<>()).size();
    }

    // Get milestones as a string (simplified for display purposes)
    public String getTrackedMilestones() {
        StringBuilder milestones = new StringBuilder();
        milestones.append("Weight: ").append(weight).append(" kg\n");
        milestones.append("BMI: ").append(bmi).append("\n");
        for (String program : attendanceRecords.keySet()) {
            if (program != null) {
                int totalSessions = getTotalAttendance(program);
                milestones.append(program).append(": ").append(totalSessions).append(" sessions attended\n");
            }
        }
        return milestones.toString();
    }

    public String getTotalAttendanceRecord(String program) {
        if (program == null) {
            throw new IllegalArgumentException("Program cannot be null.");
        }
        int totalSessions = getTotalAttendance(program);
        return totalSessions + " sessions attended";
    }

	public static void enrollInProgram(String programName) {
		// Check if program name is valid
        if (programName == null || programName.isEmpty()) {
            throw new IllegalArgumentException("Program name cannot be null or empty.");
        }

        // Add the program to the set (this automatically handles duplicates)
        enrolledPrograms.add(programName);
        
        // Print feedback about successful enrollment
        System.out.println("Successfully enrolled in: " + programName);		
	}
	 // Static method to check if a program is enrolled
    public static boolean isEnrolledInProgram(String programName) {
        return enrolledPrograms.contains(programName);
    }

    // Static method to display all enrolled programs
    public static void displayEnrolledPrograms() {
        System.out.println("Enrolled Programs: " + enrolledPrograms);
    }
    

    public static void main(String[] args) {
        // Test the methods
        enrollInProgram("Yoga for Beginners");
        enrollInProgram("Advanced Cardio Program");

        // Check if a user is enrolled in a specific program
        System.out.println("Enrolled in Yoga for Beginners: " + isEnrolledInProgram("Yoga for Beginners"));
        System.out.println("Enrolled in Weight Lifting: " + isEnrolledInProgram("Weight Lifting"));

        // Display all enrolled programs
        displayEnrolledPrograms();
}


}