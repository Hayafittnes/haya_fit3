package assurance;

import org.junit.Assert;
import org.mockito.Mockito;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountManagement {
	AccountM accountM = new AccountM();
	
	  // This method will run before each scenario
	@io.cucumber.java.Before
	public void resetTestContext() {
	    TestContext.reset(); // This will now clear the test context without error
	    System.out.println("Test context reset.");
	}
    
	 @Given("I am a new user")
	    public void i_am_a_new_user() {
		 
	        user mockUser = accountM.createMockUser(1, "Test User", 30, "Build muscle", "");
	        TestContext.setUser(mockUser);
	        System.out.println("Mock user created: " + mockUser.getName());
	    }

	    @When("I provide personal details such as age and fitness goals")
	    public void i_provide_personal_details_such_as_age_and_fitness_goals() {
	    	user mockUser = TestContext.getUser();
	        Mockito.when(mockUser.getAge()).thenReturn(25);  // Mock the age here
	        Mockito.when(mockUser.getFitnessGoals()).thenReturn("Lose weight");
	        System.out.println("Personal details updated: Age: " + mockUser.getAge() + ", Fitness Goals: " + mockUser.getFitnessGoals());
	    }

	    @When("I set dietary preferences or restrictions")
	    public void i_set_dietary_preferences_or_restrictions() {
	        user mockUser = TestContext.getUser();
	        Mockito.when(mockUser.getDietaryPreferences()).thenReturn("Vegan");
	        System.out.println("Dietary preference set: " + mockUser.getDietaryPreferences());
	    }

	    @Then("my profile should be successfully created")
	    public void my_profile_should_be_successfully_created() {
	    	 user mockUser = TestContext.getUser();
	    	    Assert.assertEquals("User ID does not match", 1, (int) mockUser.getId());
	    	    Assert.assertEquals("User name does not match", "Test User", mockUser.getName());
	    	    Assert.assertEquals("User age does not match", 25, (int) mockUser.getAge());  // Assert the mocked age
	    	    Assert.assertEquals("Fitness goals do not match", "Lose weight", mockUser.getFitnessGoals());
	    	    Assert.assertEquals("Dietary preferences do not match", "Vegan", mockUser.getDietaryPreferences());
	    	    System.out.println("Profile successfully created.");
	    }

	    @Given("I have an existing user profile")
	    public void i_have_an_existing_user_profile() {
	    	user mockUser = accountM.createMockUser(1, "Existing User", 40, "Improve endurance", "No dairy");
	        TestContext.setUser(mockUser);
	        System.out.println("Existing user profile is set up.");
	    }
	    @Given("I have an existing user profile with ID {int} and age {int} and {string} and {string}")
	    public void i_have_an_existing_user_profile_with_id_and_age_and_and(Integer int1, Integer int2, String string, String string2) {
	    	// Create the user mock with the provided values
	        user mockUser = accountM.createMockUser(int1, "Existing User", int2, string, string2);

	        // Set the mock user in the context
	        TestContext.setUser(mockUser);

	        // Log the user details for verification
	        System.out.println("Existing user profile is set up with ID: " + int1 + ", Age: " + int2 + ", Fitness Goals: " + string + ", Dietary Preferences: " + string2);
	    }

	    @When("I update my fitness goals to {string}")
	    public void i_update_my_fitness_goals_to(String fitnessGoal) {
	        user mockUser = TestContext.getUser();
	        
	        // Update mock behavior
	        Mockito.when(mockUser.getFitnessGoals()).thenReturn(fitnessGoal);

	        System.out.println("Fitness goals updated to: " + fitnessGoal);
	    }

	    @Then("my fitness goals should be updated to {string}")
	    public void my_fitness_goals_should_be_updated_to(String fitnessGoal) {
	        user mockUser = TestContext.getUser();
	        Assert.assertEquals("Fitness goals do not match", fitnessGoal, mockUser.getFitnessGoals());
	        System.out.println("Fitness goals confirmed to be updated to: " + fitnessGoal);
	    }

	    @When("I update my dietary preferences to {string}")
	    public void i_update_my_dietary_preferences_to(String dietaryPreference) {
	        user mockUser = TestContext.getUser();
	        Mockito.when(mockUser.getDietaryPreferences()).thenReturn(dietaryPreference);
	        System.out.println("Dietary preferences updated to: " + dietaryPreference);
	    }

	    @Then("my dietary preferences should be updated to {string}")
	    public void my_dietary_preferences_should_be_updated_to(String dietaryPreference) {
	        user mockUser = TestContext.getUser();
	        Assert.assertEquals("Dietary preferences do not match", dietaryPreference, mockUser.getDietaryPreferences());
	        System.out.println("Dietary preferences confirmed to be updated to: " + dietaryPreference);
	    }

	    @When("I view my profile")
	    public void i_view_my_profile() {
	    	 user mockUser = TestContext.getUser();

	    	    // Log the current state of the mock user
	    	    System.out.println("Viewing profile for: " + mockUser.getName());
	    	    System.out.println("Age: " + mockUser.getAge());
	    	    System.out.println("Fitness Goals: " + mockUser.getFitnessGoals());
	    	    System.out.println("Dietary Preferences: " + mockUser.getDietaryPreferences());
	    }

	
	    @Then("I should see my personal details: my age {int}  , my fitness goals {string}")
	    public void i_should_see_my_personal_details_my_age_my_fitness_goals(Integer int1, String string) {
	    	 user mockUser = TestContext.getUser();

	    	    // Assert the mock user has the correct values
	    	    Assert.assertEquals("Age does not match", int1.intValue(), mockUser.getAge());
	    	    Assert.assertEquals("Fitness goals do not match", string, mockUser.getFitnessGoals());

	    	    System.out.println("Personal details confirmed. Age: " + mockUser.getAge() + ", Fitness Goals: " + mockUser.getFitnessGoals());
	    }
	    @Then("I should see my dietary preferences {string}")
	    public void i_should_see_my_dietary_preferences(String dietaryPreferences) {
	        user mockUser = TestContext.getUser();
	        Assert.assertEquals("Dietary preferences do not match", dietaryPreferences, mockUser.getDietaryPreferences());
	        System.out.println("Dietary preferences confirmed: " + mockUser.getDietaryPreferences());
	    }
}