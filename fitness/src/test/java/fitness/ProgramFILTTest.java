package fitness;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProgramFILTTest {

    private List<String> allPrograms; // Mock list of all programs
    private List<String> filteredPrograms; // Mock list of filtered programs

	@Given("I am on the programs page")
	public void i_am_on_the_programs_page() {
		// Initialize the mock list of programs
		allPrograms = new ArrayList<>();
		allPrograms.add("Beginner - Yoga for Beginners (Flexibility)");
		allPrograms.add("Beginner - Cardio Basics (Weight Loss)");
		allPrograms.add("Intermediate - Strength Training (Strength Building)");
		allPrograms.add("Intermediate - Advanced Cardio (Weight Loss)");
		allPrograms.add("Advanced - Powerlifting (Strength Building)");
		allPrograms.add("Advanced - Marathon Training (Endurance)");

        // Print available programs for debugging
        System.out.println("Programs available on the page:");
        allPrograms.forEach(System.out::println);
	}

	@When("I filter programs by difficulty level {string}")
	public void i_filter_programs_by_difficulty_level(String string) {
		  // Filter programs based on difficulty level
        filteredPrograms = new ArrayList<>();
        for (String program : allPrograms) {
            if (program.startsWith(string)) {
                filteredPrograms.add(program);
            }
        }

        // Print filtered programs for debugging
        System.out.println("Filtered programs for difficulty level \"" + string + "\":");
        filteredPrograms.forEach(System.out::println);

	}

	@Then("I should see a list of {string} level programs")
	public void i_should_see_a_list_of_level_programs(String string) {
		  // Validate that filtered programs match the selected difficulty level
        if (filteredPrograms == null || filteredPrograms.isEmpty()) {
            throw new AssertionError("No programs found for difficulty level: " + string);
        }

        for (String program : filteredPrograms) {
            if (!program.startsWith(string)) {
                throw new AssertionError("Program does not match the difficulty level: " + program);
            }
        }

        // Confirm the filtered list
        System.out.println("Displayed programs match the difficulty level: " + string);
    }
	
	 // Focus area filtering logic
    @When("I filter programs by focus area {string}")
    public void i_filter_programs_by_focus_area(String focusArea) {
        // Filter programs based on focus area
        filteredPrograms = new ArrayList<>();
        for (String program : allPrograms) {
        	if (program.toLowerCase().contains(focusArea.toLowerCase())) {
                filteredPrograms.add(program);
            }
        }

        // Debugging: Print filtered programs
        System.out.println("Filtered programs for focus area \"" + focusArea + "\":");
        filteredPrograms.forEach(System.out::println);
    }

    @Then("I should see a list of programs focused on {string}")
    public void i_should_see_a_list_of_programs_focused_on(String focusArea) {
        // Validate that all filtered programs match the focus area
        if (filteredPrograms == null || filteredPrograms.isEmpty()) {
            throw new AssertionError("No programs found for focus area: " + focusArea);
        }

        for (String program : filteredPrograms) {
            if (!program.toLowerCase().contains(focusArea.toLowerCase())) {
                throw new AssertionError("Program does not match the focus area: " + program);
            }
        }

        // Confirm success
        System.out.println("Displayed programs match the focus area: " + focusArea);
    }
    
	}

