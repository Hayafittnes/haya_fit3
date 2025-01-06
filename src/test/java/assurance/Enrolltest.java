package assurance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.ArrayList;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 


public class Enrolltest  {
	private Enroll programManager = new Enroll();
    private String enrollmentMessage;
    private String scheduleMessage;	

    private Enroll enroll;

    // Constructor or setup method
    public Enrolltest() {
        enroll = new Enroll(); // Initialize Enroll object
    }
    
    
	@Given("I have filtered programs by difficulty level {string}")
	public void i_have_filtered_programs_by_difficulty_level(String string) {
		  List<String> filteredPrograms = new ArrayList<>();
		    for (String program : programManager.getAllPrograms()) {
		        if (program.startsWith(string)) {
		            filteredPrograms.add(program);
		        }
		    }

		    Assert.assertFalse("No programs found for difficulty level: " + string, filteredPrograms.isEmpty());
		    System.out.println("Filtered programs for \"" + string + "\": " + filteredPrograms);
	}

	@When("I select the program {string} to enroll")
	public void i_select_the_program_to_enroll(String string) {
		enrollmentMessage = programManager.enrollInProgram(string);
        System.out.println(enrollmentMessage);
	}

	@Then("I should see a confirmation message that says {string}")
	public void i_should_see_a_confirmation_message_that_says(String string) {
	 assertEquals(string, enrollmentMessage);
	
	}
		 
	

	@Then("the program {string} should be added to my enrolled programs list")
	public void the_program_should_be_added_to_my_enrolled_programs_list(String string) {
		  assertTrue(programManager.getEnrolledPrograms().contains(string));
	        System.out.println("Program successfully added to enrolled list: " + string);
	}

	@Given("I am enrolled in the program {string}")
	public void i_am_enrolled_in_the_program(String string) {
		  enrollmentMessage = programManager.enrollInProgram(string);
	        assertEquals("You have successfully enrolled in " + string, enrollmentMessage);
	}

	@When("I view the schedule for {string}")
	public void i_view_the_schedule_for(String string) {
		 scheduleMessage = programManager.viewSchedule(string);
	        System.out.println(scheduleMessage);
	}

	@Then("I should see the {string} schedule, including the days and times of the sessions")
	public void i_should_see_the_schedule_including_the_days_and_times_of_the_sessions(String string) {
		    // Retrieve the expected schedule using the getter
		    String expectedSchedule = programManager.getProgramSchedules().get(string);

		    // Retrieve the actual schedule from the viewSchedule method
		    String actualSchedule = programManager.viewSchedule(string);

		    // Debugging information
		    System.out.println("Program Name: " + string);
		    System.out.println("Expected Schedule: " + expectedSchedule);
		    System.out.println("Actual Schedule: " + actualSchedule);

		    // Assert the expected schedule is found in the actual schedule message
		    Assert.assertNotNull("Expected schedule is null", expectedSchedule);
		    Assert.assertTrue("Expected schedule not found in the actual schedule view result",
		        actualSchedule.contains(expectedSchedule));

}
}


