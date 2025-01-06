package assurance;

import java.util.HashMap;
import java.util.Map;

public class FeedbackManager {
    private Map<String, String> completedPrograms; // Store reviews for completed programs
    private Map<String, String> suggestions;      // Store suggestions for programs

    public FeedbackManager() {
        completedPrograms = new HashMap<>();
        suggestions = new HashMap<>();
    }

    // Mark a program as completed
    public void markProgramAsCompleted(String programName) {
        if (!completedPrograms.containsKey(programName)) {
            completedPrograms.put(programName, ""); // Initialize with no feedback
        }
    }

    // Rate and review a program
    public String rateAndReviewProgram(String programName, int rating, String review) {
        if (!completedPrograms.containsKey(programName)) {
            return "Error: Program not completed.";
        }
        completedPrograms.put(programName, "Rating: " + rating + ", Review: " + review);
        return "Thank you for your feedback on " + programName + ".";
    }

    // Submit a suggestion for a program
    public String submitSuggestion(String programName, String suggestion) {
        suggestions.put(programName, suggestion);
        return "Your suggestion has been submitted. Thank you for your input!";
    }

    // Attempt to review an incomplete program
    public String tryToReviewIncompleteProgram(String programName) {
        return "You can only rate or review completed programs.";
    }

    // Get completed programs
    public Map<String, String> getCompletedPrograms() {
        return completedPrograms;
    }

    // Get suggestions
    public Map<String, String> getSuggestions() {
        return suggestions;
    }
}