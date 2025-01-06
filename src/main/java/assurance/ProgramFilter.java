package assurance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramFilter {

    private List<String> allPrograms; // List of all fitness programs
    private List<String> filteredPrograms; // List of filtered programs

    // Constructor to initialize the program list
    public ProgramFilter() {
        allPrograms = new ArrayList<>();
        allPrograms.add("Beginner - Yoga for Beginners");
        allPrograms.add("Beginner - Cardio Basics");
        allPrograms.add("Intermediate - Strength Training");
        allPrograms.add("Intermediate - Advanced Cardio");
        allPrograms.add("Advanced - Powerlifting");
        allPrograms.add("Advanced - Marathon Training");
    }

    // Method to return all programs
    public List<String> getAllPrograms() {
        return new ArrayList<>(allPrograms); // Return a copy of the list
    }

    // Method to filter programs by difficulty level
    public List<String> filterProgramsByDifficulty(String difficulty) {
        filteredPrograms = allPrograms.stream()
                                      .filter(program -> program.startsWith(difficulty))
                                      .collect(Collectors.toList());
        return filteredPrograms;
    }

    // Method to display programs (for testing/debugging)
    public void printPrograms(List<String> programs) {
        if (programs == null || programs.isEmpty()) {
            System.out.println("No programs found.");
        } else {
            for (String program : programs) {
                System.out.println(program);
            }
        }
    }

    // Main method to test the functionality
    public static void main(String[] args) {
    	ProgramFilter app = new ProgramFilter();

        // Display all programs
        System.out.println("Available Programs:");
        app.printPrograms(app.getAllPrograms());

        // Filter and display Beginner programs
        System.out.println("\nFiltered Programs (Beginner):");
        app.printPrograms(app.filterProgramsByDifficulty("Beginner"));

        // Filter and display Intermediate programs
        System.out.println("\nFiltered Programs (Intermediate):");
        app.printPrograms(app.filterProgramsByDifficulty("Intermediate"));

        // Filter and display Advanced programs
        System.out.println("\nFiltered Programs (Advanced):");
        app.printPrograms(app.filterProgramsByDifficulty("Advanced"));
    }
}