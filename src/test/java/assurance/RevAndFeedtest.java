package assurance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RevAndFeedtest {
	  private FeedbackManager feedbackManager;
	    private String responseMessage;
	    private int rating;

	    public RevAndFeedtest() {
	        feedbackManager = new FeedbackManager();
	    }
	    @Given("I have done the program {string}")
	    public void i_have_done_the_program(String string) {
	    	 feedbackManager.markProgramAsCompleted(string);
	    }

    @When("I rate the program {int} stars")
    public void i_rate_the_program_stars(Integer int1) {
    	this.rating = int1; // Save rating
    }

    @When("I submit the review {string}")
    public void i_submit_the_review(String string) {
        responseMessage = feedbackManager.rateAndReviewProgram(
            "Beginner - Yoga for Beginners", rating, string);
    }

    @Then("the review should be successfully saved")
    public void the_review_should_be_successfully_saved() {
    	 String feedback = feedbackManager.getCompletedPrograms()
                 .get("Beginner - Yoga for Beginners");
assertNotNull("Feedback should not be null", feedback);
assertTrue("Feedback should contain the submitted review",
feedback.contains("Excellent program for beginners, highly recommended!"));
    }
    @Then("I should see a noute {string}")
    public void i_should_see_a_noute(String string) {
    	 assertEquals("Note message mismatch", string, responseMessage);
    }


    @When("I submit the suggestion {string}")
    public void i_submit_the_suggestion(String string) {
    	 responseMessage = feedbackManager.submitSuggestion(
    	            "Intermediate - Strength Training", string);
    }

    @Then("the suggestion should be successfully sent to the instructor")
    public void the_suggestion_should_be_successfully_sent_to_the_instructor() {
    	  assertTrue("Suggestion should be recorded",
                  feedbackManager.getSuggestions()
                                 .containsKey("Intermediate - Strength Training"));
    }

    @When("I try to rate or review the program")
    public void i_try_to_rate_or_review_the_program() {
    	 responseMessage = feedbackManager.tryToReviewIncompleteProgram("Advanced - Powerlifting");
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String string) {
    	assertEquals(string, responseMessage); // Ensure this compares the correct strings
    }
    
}