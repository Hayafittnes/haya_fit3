package assurance;
import org.junit.Assert;
import org.mockito.Mockito;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountManagement {
   // AccountM accountM = new AccountM();
    AccountM accountM = AccountM.getInstance();
    @io.cucumber.java.Before
    public void resetTestContext() {
        TestContext.reset();
        System.out.println("Test context reset.");
    }

    @Given("I am a new user")
    public void i_am_a_new_user() {
        clients mockClient = AccountM.createMockClient(1, "Test User", 30, "Build muscle", "");
        TestContext.setUser(mockClient);
        System.out.println("Mock client created: " + mockClient.getName());
    }

    @When("I provide personal details such as age and fitness goals")
    public void i_provide_personal_details_such_as_age_and_fitness_goals() {
        clients mockClient = TestContext.getUser();
        Mockito.when(mockClient.getAge()).thenReturn(25); // Mock the age
        Mockito.when(mockClient.getFitnessGoals()).thenReturn("Lose weight");
        System.out.println("Personal details updated: Age: " + mockClient.getAge() + ", Fitness Goals: " + mockClient.getFitnessGoals());
    }

    @When("I set dietary preferences or restrictions")
    public void i_set_dietary_preferences_or_restrictions() {
        clients mockClient = TestContext.getUser();
        Mockito.when(mockClient.getDietaryPreferences()).thenReturn("Vegan");
        System.out.println("Dietary preference set: " + mockClient.getDietaryPreferences());
    }

    @Then("my profile should be successfully created")
    public void my_profile_should_be_successfully_created() {
        clients mockClient = TestContext.getUser();
        Assert.assertEquals("User ID does not match", 1, (int) mockClient.getId());
        Assert.assertEquals("User name does not match", "Test User", mockClient.getName());
        Assert.assertEquals("User age does not match", 25, (int) mockClient.getAge());
        Assert.assertEquals("Fitness goals do not match", "Lose weight", mockClient.getFitnessGoals());
        Assert.assertEquals("Dietary preferences do not match", "Vegan", mockClient.getDietaryPreferences());
        System.out.println("Profile successfully created.");
    }

    @Given("I have an existing user profile")
    public void i_have_an_existing_user_profile() {
        clients mockClient = accountM.createMockClient(1, "Existing User", 40, "Improve endurance", "No dairy");
        TestContext.setUser(mockClient);
        System.out.println("Existing user profile is set up.");
    }

    @Given("I have an existing user profile with ID {int} and age {int} and {string} and {string}")
    public void i_have_an_existing_user_profile_with_id_and_age_and_and(Integer id, Integer age, String goals, String dietary) {
        clients mockClient = accountM.createMockClient(id, "Existing User", age, goals, dietary);
        TestContext.setUser(mockClient);
        System.out.println("Existing user profile is set up with ID: " + id + ", Age: " + age + ", Fitness Goals: " + goals + ", Dietary Preferences: " + dietary);
    }

    @When("I update my fitness goals to {string}")
    public void i_update_my_fitness_goals_to(String fitnessGoal) {
        clients mockClient = TestContext.getUser();
        Mockito.when(mockClient.getFitnessGoals()).thenReturn(fitnessGoal);
        System.out.println("Fitness goals updated to: " + fitnessGoal);
    }

    @Then("my fitness goals should be updated to {string}")
    public void my_fitness_goals_should_be_updated_to(String fitnessGoal) {
        clients mockClient = TestContext.getUser();
        Assert.assertEquals("Fitness goals do not match", fitnessGoal, mockClient.getFitnessGoals());
        System.out.println("Fitness goals confirmed to be updated to: " + fitnessGoal);
    }

    @When("I update my dietary preferences to {string}")
    public void i_update_my_dietary_preferences_to(String dietaryPreference) {
        clients mockClient = TestContext.getUser();
        Mockito.when(mockClient.getDietaryPreferences()).thenReturn(dietaryPreference);
        System.out.println("Dietary preferences updated to: " + dietaryPreference);
    }

    @Then("my dietary preferences should be updated to {string}")
    public void my_dietary_preferences_should_be_updated_to(String dietaryPreference) {
        clients mockClient = TestContext.getUser();
        Assert.assertEquals("Dietary preferences do not match", dietaryPreference, mockClient.getDietaryPreferences());
        System.out.println("Dietary preferences confirmed to be updated to: " + dietaryPreference);
    }

    @When("I view my profile")
    public void i_view_my_profile() {
        clients mockClient = TestContext.getUser();
        System.out.println("Viewing profile for: " + mockClient.getName());
        System.out.println("Age: " + mockClient.getAge());
        System.out.println("Fitness Goals: " + mockClient.getFitnessGoals());
        System.out.println("Dietary Preferences: " + mockClient.getDietaryPreferences());
    }

    @Then("I should see my personal details: my age {int} , my fitness goals {string}")
    public void i_should_see_my_personal_details_my_age_my_fitness_goals(Integer age, String goals) {
        clients mockClient = TestContext.getUser();
        Assert.assertEquals("Age does not match", age.intValue(), mockClient.getAge());
        Assert.assertEquals("Fitness goals do not match", goals, mockClient.getFitnessGoals());
        System.out.println("Personal details confirmed. Age: " + mockClient.getAge() + ", Fitness Goals: " + mockClient.getFitnessGoals());
    }

    @Then("I should see my dietary preferences {string}")
    public void i_should_see_my_dietary_preferences(String dietaryPreferences) {
        clients mockClient = TestContext.getUser();
        Assert.assertEquals("Dietary preferences do not match", dietaryPreferences, mockClient.getDietaryPreferences());
        System.out.println("Dietary preferences confirmed: " + mockClient.getDietaryPreferences());
    }
}
