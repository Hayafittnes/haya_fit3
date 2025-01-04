package assurance;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SUBSCRIPTION1 {
    private String currentSubscriptionPlan;
    private String newSubscriptionPlan;
    private String userName;
    private boolean isUserNotified = false;
    private boolean isSubscriptionUpdated = false;
    private boolean isUserDeactivated = false;

    @When("the admin navigates to the subscription management section")
    public void the_admin_navigates_to_the_subscription_management_section() {
        System.out.println("Navigated to subscription management section.");
    }

    @When("the admin views the subscription details for the user {string}")
    public void the_admin_views_the_subscription_details_for_the_user(String userName) {
        this.userName = userName;
        System.out.println("Admin views subscription details for: " + userName);
    }

    @When("the admin sees the current subscription plan {string}")
    public void the_admin_sees_the_current_subscription_plan(String currentPlan) {
        this.currentSubscriptionPlan = currentPlan;
        System.out.println("Current subscription plan for " + userName + " is: " + currentPlan);
    }

    @When("the admin decides to change the subscription plan for the user to {string}")
    public void the_admin_decides_to_change_the_subscription_plan_for_the_user_to(String subscriptionPlan) {
        this.newSubscriptionPlan = subscriptionPlan;
        System.out.println("Admin decides to change subscription plan for " + userName + " to: " + subscriptionPlan);
    }

    @Then("the system should update the user's subscription plan to {string}")
    public void the_system_should_update_the_user_s_subscription_plan_to(String subscriptionPlan) {
        Assert.assertEquals("Subscription plan mismatch", subscriptionPlan, newSubscriptionPlan);
        isSubscriptionUpdated = true;
        System.out.println("System updates subscription plan for " + userName + " to: " + subscriptionPlan);
    }

    @Then("the system should notify the user {string} about the subscription plan change via in-app notification")
    public void the_system_should_notify_the_user_about_the_subscription_plan_change_via_in_app_notification(String userName) {
        isUserNotified = true;
        System.out.println("User " + userName + " has been notified about the subscription plan change.");
    }

    @Then("the system should reflect the new subscription plan in the user's profile")
    public void the_system_should_reflect_the_new_subscription_plan_in_the_user_s_profile() {
      //  Assert.assertTrue("Subscription update not reflected in profile", isSubscriptionUpdated);
        System.out.println("User profile has been updated with the new subscription plan.");
    }

    @Then("the admin should be able to see a confirmation of the subscription update")
    public void the_admin_should_be_able_to_see_a_confirmation_of_the_subscription_update() {
        Assert.assertTrue("Subscription update confirmation missing", isSubscriptionUpdated);
        System.out.println("Admin sees confirmation of subscription update.");
    }

    @When("the admin navigates to the user registration section")
    public void the_admin_navigates_to_the_user_registration_section() {
        System.out.println("Navigated to user registration section.");
    }

    @When("the admin adds a new user {string} with email {string}")
    public void the_admin_adds_a_new_user_with_email(String newUserName, String userEmail) {
        System.out.println("Admin added new user: " + newUserName + " with email: " + userEmail);
    }

    @When("the admin assigns the subscription plan {string} to the new user")
    public void the_admin_assigns_the_subscription_plan_to_the_new_user(String subscriptionPlan) {
        this.newSubscriptionPlan = subscriptionPlan;
        System.out.println("Admin assigns subscription plan " + subscriptionPlan + " to new user.");
    }

    @Then("the system should create the new user account with the subscription plan {string}")
    public void the_system_should_create_the_new_user_account_with_the_subscription_plan(String subscriptionPlan) {
        Assert.assertEquals("Subscription plan mismatch for new user", subscriptionPlan, newSubscriptionPlan);
        System.out.println("New user account created with subscription plan: " + subscriptionPlan);
    }

    @Then("the system should notify the new user {string} about the subscription plan via in-app notification")
    public void the_system_should_notify_the_new_user_about_the_subscription_plan_via_in_app_notification(String newUserName) {
        isUserNotified = true;
        System.out.println("New user " + newUserName + " notified about subscription plan.");
    }

    @When("the admin decides to deactivate the subscription for the user")
    public void the_admin_decides_to_deactivate_the_subscription_for_the_user() {
        System.out.println("Admin decides to deactivate the subscription for user: " + userName);
    }

    @Then("the system should deactivate the user's subscription")
    public void the_system_should_deactivate_the_user_s_subscription() {
        isUserDeactivated = true;
        System.out.println("User's subscription has been deactivated.");
    }

    @Then("the system should notify the user {string} about the subscription deactivation via in-app notification")
    public void the_system_should_notify_the_user_about_the_subscription_deactivation_via_in_app_notification(String userName) {
        isUserNotified = true;
        System.out.println("User " + userName + " notified about subscription deactivation.");
    }

    @Then("the system should reflect the deactivated subscription status in the user's profile")
    public void the_system_should_reflect_the_deactivated_subscription_status_in_the_user_s_profile() {
        Assert.assertTrue("Deactivation status not reflected in profile", isUserDeactivated);
        System.out.println("User's profile updated with deactivated subscription status.");
    }
}
