package assurance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enroll {

    private List<String> allPrograms; // List of all available fitness programs
    private List<String> enrolledPrograms; // List of programs the user has enrolled in
    private Map<String, String> programSchedules; // Map to store schedules for programs

    // Constructor to initialize data
    public Enroll() {
        allPrograms = new ArrayList<>();
        enrolledPrograms = new ArrayList<>();
        programSchedules = new HashMap<>();

        // Initialize programs (with difficulty level and tag)
        allPrograms.add("Beginner - Yoga for Beginners(Flexibility)");
        allPrograms.add("Beginner - Yoga for Beginners");
        allPrograms.add("Advanced - Powerlifting");
        allPrograms.add("Intermediate - Strength Training");
        allPrograms.add("Strength Training");
        allPrograms.add("Beginner - Cardio Basics(Weight Loss)");
        allPrograms.add("Intermediate - Strength Training(Strength Building)");
        allPrograms.add("Intermediate - Advanced Cardio(Weight Loss)");
        allPrograms.add("Advanced - Powerlifting(Strength Building)");
        allPrograms.add("Advanced - Marathon Training(Endurance)");
        allPrograms.add("Advanced - Cardio Program(Endurance)");
        

        // Initialize schedules for programs
        programSchedules.put("Beginner - Yoga for Beginners(Flexibility)", "Monday, Wednesday, Friday at 8 AM");
        programSchedules.put("Beginner - Cardio Basics(Weight Loss)", "Tuesday, Thursday at 6 PM");
        programSchedules.put("Intermediate - Strength Training(Strength Building)", "Monday, Wednesday at 7 PM");
        programSchedules.put("Intermediate - Advanced Cardio(Weight Loss)", "Tuesday, Thursday at 7 AM");
        programSchedules.put("Advanced - Powerlifting(Strength Building)", "Saturday at 9 AM");
        programSchedules.put("Advanced - Marathon Training(Endurance)", "Sunday at 7 AM");
    }

    // Method to get all available programs
    public List<String> getAllPrograms() {
        return new ArrayList<>(allPrograms);
    }
    
    public Map<String, String> getProgramSchedules() {
        return new HashMap<>(programSchedules); // Return a copy for immutability
    }
    
    // Method to enroll in a program
    public String enrollInProgram(String programName) {
    	String trimmedProgramName = programName.trim();
        for (String program : allPrograms) {
            if (program.equalsIgnoreCase(trimmedProgramName)) {
                if (!enrolledPrograms.contains(program)) {
                    enrolledPrograms.add(program);
                    return "You have successfully enrolled in " + programName;
                } else {
                    return "You are already enrolled in " + programName;
                }
            }
        }
        return "Program not found: " + programName;
    }

    // Method to get the list of enrolled programs
    public List<String> getEnrolledPrograms() {
        return new ArrayList<>(enrolledPrograms);
    }

    // Method to view the schedule for a specific program
    public String viewSchedule(String programName) {
        if (enrolledPrograms.contains(programName)) {
            String schedule = programSchedules.get(programName);
            if (schedule != null) {
                return "Schedule for " + programName + ": " + schedule;
            } else {
                return "No schedule available for " + programName;
            }
        } else {
            return "You are not enrolled in " + programName;
        }
    }
    public String getScheduleForProgram(String string) {
    	 // Mock implementation: Replace with actual logic to fetch the schedule
        if (string.equals("Beginner - Yoga for Beginners(Flexibility)")) {
            return "Monday 10 AM - 11 AM, Wednesday 5 PM - 6 PM";
        } else if (string.equals("Beginner - Cardio Basics(Weight Loss)")) {
            return "Tuesday 8 AM - 9 AM, Thursday 6 PM - 7 PM";
        } else if (string.equals("Intermediate - Strength Training(Strength Building)")) {
            return "Monday 6 AM - 7 AM, Friday 7 PM - 8 PM";
        } else if (string.equals("Advanced - Powerlifting(Strength Building)")) {
            return "Saturday 9 AM - 11 AM, Sunday 4 PM - 6 PM";
        }
        return null; // Return null if no schedule is found
	}
    
}