package assurance;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class achievementtest {
	private UserAchiv user = new UserAchiv("John Doe"); 
    private AchievementsService achievementsService = new AchievementsService(); 
	@Given("I have completed the program {string}")
	public void i_have_completed_the_program(String string) {
        user.completeProgram(string);
	}

	@When("I view my achievements")
	public void i_view_my_achievements() {
        achievementsService.viewAchievements(user);
	}

	@Then("I should see a badge titled {string}")
	public void i_should_see_a_badge_titled(String string) {
	    String program = user.getCompletedPrograms().get(0); 
	    String actualBadgeTitle = achievementsService.getBadgeTitle(program);
	    Assert.assertEquals("Badge title does not match", string, actualBadgeTitle);
	}

	@Then("the description should be {string}")
	public void the_description_should_be(String string) {
	    String program = user.getCompletedPrograms().get(0); 
	    String actualBadgeDescription = achievementsService.getBadgeDescription(program);
	    Assert.assertEquals("Badge description does not match", string, actualBadgeDescription);
	}

	@Given("I have completed the programs {string}")
	public void i_have_completed_the_programs(String string) {
        String[] programList = string.split(",");
        for (String program : programList) {
            user.completeProgram(program.trim()); 
        }
	}

	@Then("I should see the following badges:")
	public void i_should_see_the_following_badges(io.cucumber.datatable.DataTable dataTable) {
		 List<Map<String, String>> expectedBadges = dataTable.asMaps(String.class, String.class);

		    // Actual badges from service
		    List<String[]> actualBadges = achievementsService.getAllBadges(user);

		    // Debugging: Print actual badges
		    System.out.println("Actual Badges List: ");
		    for (String[] badge : actualBadges) {
		        System.out.println("Title: " + badge[0] + ", Description: " + badge[1]);
		    }

		    // Debugging: Print expected badges
		    System.out.println("Expected Badges: ");
		    for (Map<String, String> expectedBadge : expectedBadges) {
		        System.out.println("Title: " + expectedBadge.get("Badge Title") + ", Description: " + expectedBadge.get("Description"));
		    }

		    // Transform expected badges
		    List<String> expected = expectedBadges.stream()
		        .map(badge -> badge.get("Badge Title").trim().replaceAll("\\s+", " ") + " - " +
		                      badge.get("Description").trim().replaceAll("\\s+", " ").replaceAll("\\.$", ""))
		        .collect(Collectors.toList());

		    // Transform actual badges
		    List<String> actual = actualBadges.stream()
		        .map(badge -> badge[0].trim().replaceAll("\\s+", " ") + " - " +
		                      badge[1].trim().replaceAll("\\s+", " ").replaceAll("\\.$", ""))
		        .collect(Collectors.toList());

		    // Debugging: Compare lists
		    System.out.println("Normalized Expected Badges: " + expected);
		    System.out.println("Normalized Actual Badges: " + actual);

		    // Assert that actual and expected lists match
		    if (!actual.containsAll(expected) || !expected.containsAll(actual)) {
		        System.out.println("MISMATCH DETECTED!");
		        System.out.println("Expected but not found in actual: " + expected.stream().filter(e -> !actual.contains(e)).toList());
		        System.out.println("Actual but not found in expected: " + actual.stream().filter(a -> !expected.contains(a)).toList());
		    }

		    Assert.assertTrue("Badges do not match!", actual.containsAll(expected) && expected.containsAll(actual));

	}

	@Given("I am currently enrolled in the program {string}")
	public void i_am_currently_enrolled_in_the_program(String string) {
		 // Enroll the user in the program (without completing it)
        user.enrollInProgram(string);
	}

	@Then("I should see a message {string}")
	public void i_should_see_a_message(String string) {
		// Fetch the actual message
      String actualMessage = achievementsService.getNoBadgeMessage(user);
        Assert.assertEquals("Message mismatch", string, actualMessage);
	} 

	@Given("I have completed {int} fitness programs")
	public void i_have_completed_fitness_programs(Integer int1) {
		// Complete the given number of programs
        for (int i = 0; i < int1; i++) {
            user.completeProgram("Program " + (i + 1));  // Example program names (Program 1, Program 2, etc.)
        }
	}

	@When("I view my achievements summary")
	public void i_view_my_achievements_summary() {
		  // View the user's achievements summary (total number of badges)
        achievementsService.viewAchievementsSummary(user);
	}

	@Then("I should see {string}")
	public void i_should_see(String string) {
		 // Fetch the actual summary message
        String actualSummary = achievementsService.getAchievementsSummary(user);
        Assert.assertEquals("Summary mismatch", string, actualSummary);
	}
}
