package fitness;

import org.mockito.Mockito;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountManagement {
	@Given("I am a new user")
	public void i_am_a_new_user() {
	    System.out.println("Creating a mock user...");

	    // Create a mock User object
	    user mockUser = Mockito.mock(user.class);

	    // Define behavior for the mock object
	    Mockito.when(mockUser.getId()).thenReturn(1);  // Mock user ID
	    Mockito.when(mockUser.getName()).thenReturn("Test User");  // Mock user name
	    Mockito.when(mockUser.getAge()).thenReturn(25);  // Mock age
	    Mockito.when(mockUser.getFitnessGoals()).thenReturn("Build muscle");  // Mock fitness goal

	    // Store the mock user in a context for use in other steps
	    TestContext.setUser(mockUser);  // Assuming TestContext is a shared test data holder

	    // Print details for debugging
	    System.out.println("Mock user created:");
	    System.out.println("ID: " + mockUser.getId());
	    System.out.println("Name: " + mockUser.getName());
	    System.out.println("Age: " + mockUser.getAge());
	    System.out.println("Fitness Goals: " + mockUser.getFitnessGoals());
	}

	@When("I provide personal details such as age and fitness goals")
	public void i_provide_personal_details_such_as_age_and_fitness_goals() {
		  System.out.println("Providing personal details for the mock user...");

	        // Retrieve the mock user from the TestContext
	        user mockUser = TestContext.getUser();

	        // Update the personal details
	        Mockito.when(mockUser.getAge()).thenReturn(30);  // Update mock age
	        Mockito.when(mockUser.getFitnessGoals()).thenReturn("Lose weight");  // Update fitness goal

	        // Print updated details for debugging
	        System.out.println("Personal details updated:");
	        System.out.println("Age: " + mockUser.getAge());
	        System.out.println("Fitness Goals: " + mockUser.getFitnessGoals());
	
	}

	@When("I set dietary preferences or restrictions")
	public void i_set_dietary_preferences_or_restrictions() {
		System.out.println("Setting dietary preferences or restrictions for the mock user...");
	    
	    // Retrieve the mock user from the TestContext
	    user mockUser = TestContext.getUser();

	    // Define or update dietary preferences for the mock user
	    String dietaryPreference = "Vegan";  // Example dietary preference
	    // Mock method for setting dietary preference (this can be a setter in User class)
	    Mockito.when(mockUser.getDietaryPreferences()).thenReturn(dietaryPreference);

	    // Print updated dietary preferences for debugging
	    System.out.println("Dietary preference set: " + mockUser.getDietaryPreferences());
	}

	@Then("my profile should be successfully created")
	public void my_profile_should_be_successfully_created() {
		 // Retrieve the mock user from the TestContext
	    user mockUser = TestContext.getUser();

	    // Verify that the profile was created and contains the necessary information
	    assert mockUser.getId() == 1;
	    assert mockUser.getName().equals("Test User");
	    assert mockUser.getAge() == 25;
	    assert mockUser.getFitnessGoals().equals("Build muscle");
	    assert mockUser.getDietaryPreferences().equals("Vegan");  // Verify dietary preferences

	    // Print confirmation
	    System.out.println("Profile successfully created with dietary preferences: " + mockUser.getDietaryPreferences());
	}
	@Given("I have an existing user profile")
	public void i_have_an_existing_user_profile() {
		// Create an existing user profile (mock)
        user mockUser = Mockito.mock(user.class);
        Mockito.when(mockUser.getId()).thenReturn(1);
        Mockito.when(mockUser.getName()).thenReturn("Existing User");
        Mockito.when(mockUser.getAge()).thenReturn(30);
        Mockito.when(mockUser.getFitnessGoals()).thenReturn("Lose weight");
        Mockito.when(mockUser.getDietaryPreferences()).thenReturn("Vegan");

        // Store the mock user in the context
        TestContext.setUser(mockUser);
        System.out.println("Existing user profile is set up.");
	}

	@When("I update my fitness goals to {string}")
	public void i_update_my_fitness_goals_to(String string) {
		 // Retrieve the mock user from the TestContext
        user mockUser = TestContext.getUser();

        // Update fitness goals for the mock user
        Mockito.when(mockUser.getFitnessGoals()).thenReturn(string);

        // Print updated fitness goals for debugging
        System.out.println("Fitness goals updated to: " + string);
	}

	@Then("my fitness goals should be updated to {string}")
	public void my_fitness_goals_should_be_updated_to(String string) {
		  // Retrieve the mock user from the TestContext
        user mockUser = TestContext.getUser();

        // Verify that the fitness goals have been updated
        assert mockUser.getFitnessGoals().equals(string);

        // Print confirmation
        System.out.println("Fitness goals confirmed to be updated to: " + mockUser.getFitnessGoals());
	}

	@When("I update my dietary preferences to {string}")
	public void i_update_my_dietary_preferences_to(String string) {
		 // Retrieve the mock user from the TestContext
        user mockUser = TestContext.getUser();

        // Update dietary preferences for the mock user
        Mockito.when(mockUser.getDietaryPreferences()).thenReturn(string);

        // Print updated dietary preferences for debugging
        System.out.println("Dietary preferences updated to: " + string);
	}

	@Then("my dietary preferences should be updated to {string}")
	public void my_dietary_preferences_should_be_updated_to(String string) {
	        // Retrieve the mock user from the TestContext
	        user mockUser = TestContext.getUser();

	        // Verify that the dietary preferences have been updated
	        assert mockUser.getDietaryPreferences().equals(string);

	        // Print confirmation
	        System.out.println("Dietary preferences confirmed to be updated to: " + mockUser.getDietaryPreferences());
	}

	@When("I view my profile")
	public void i_view_my_profile() {
		// Retrieve the mock user from the TestContext
        user mockUser = TestContext.getUser();

        // Print the profile details for viewing
        System.out.println("Viewing profile for: " + mockUser.getName());
        System.out.println("Age: " + mockUser.getAge());
        System.out.println("Fitness Goals: " + mockUser.getFitnessGoals());
        System.out.println("Dietary Preferences: " + mockUser.getDietaryPreferences());
	}


@Then("I should see my personal details: my age {int}  , my fitness goals {string}")
public void i_should_see_my_personal_details_my_age_my_fitness_goals(Integer int1, String string) {
	// Retrieve the mock user from the TestContext
    user mockUser = TestContext.getUser();

    // Verify the personal details
    assert mockUser.getAge() == int1;
    assert mockUser.getFitnessGoals().equals(string);

    // Print confirmation
    System.out.println("Personal details confirmed. Age: " + mockUser.getAge() + ", Fitness Goals: " + mockUser.getFitnessGoals());
}

@Then("I should see my dietary preferences {string}")
public void i_should_see_my_dietary_preferences(String string) {
	 // Retrieve the mock user from the TestContext
    user mockUser = TestContext.getUser();

    // Verify dietary preferences
    assert mockUser.getDietaryPreferences().equals(string);

    // Print confirmation
    System.out.println("Dietary preferences confirmed: " + mockUser.getDietaryPreferences());
}
}
