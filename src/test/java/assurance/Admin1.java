package assurance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Admin1 {
	 private AdminService adminService = new AdminService();
	    private UserService userService = new UserService();
	    private NotificationService notificationService = new NotificationService();
	    private EmailService emailService = new EmailService();
@Given("the admin is logged in")
public void the_admin_is_logged_in() {
	adminService.login1("admin", "adminpassword");
}

@When("the admin adds a new instructor account with valid details")
public void the_admin_adds_a_new_instructor_account_with_valid_details() {
	 userService.addInstructor("John Doe", "john@example.com", "password123");
}

@Then("the new instructor account should be created successfully")
public void the_new_instructor_account_should_be_created_successfully() {
	boolean isInstructorCreated = userService.isInstructorCreated("john@example.com");
    assert isInstructorCreated : "Instructor account was not created successfully.";
}

@Then("the instructor should receive a welcome email")
public void the_instructor_should_receive_a_welcome_email() {
	 boolean isEmailSent = emailService.sendWelcomeEmail("john@example.com");
     assert isEmailSent : "Welcome email was not sent to the instructor.";
}

@Given("an instructor account exists with valid details")
 public void an_instructor_account_exists_with_valid_details() {
	        userService.addInstructor("Jane Doe", "jane@example.com", "password123");
}

@When("the admin updates the instructor's account details with new information")
public void the_admin_updates_the_instructor_s_account_details_with_new_information() {
	 userService.updateInstructorDetails("jane@example.com", "jane_updated@example.com", "newpassword123");
}

@Then("the instructor account should be updated successfully")
public void the_instructor_account_should_be_updated_successfully() {
	boolean isUpdated = userService.isInstructorUpdated("jane_updated@example.com");
    assert isUpdated : "Instructor account was not updated successfully.";
}

@Then("the instructor should receive a notification about the changes")
public void the_instructor_should_receive_a_notification_about_the_changes() {
	 boolean isNotified = notificationService.sendAccountUpdateNotification("jane_updated@example.com");
     assert isNotified : "Instructor was not notified about account changes.";
}

@Given("an instructor account exists")
public void an_instructor_account_exists() {
	 userService.addInstructor("Mike Ross", "mike@example.com", "password123");
}

@When("the admin deactivates the instructor account")
public void the_admin_deactivates_the_instructor_account() {
	 userService.deactivateInstructor("mike@example.com");
}

@Then("the instructor account should be deactivated")
public void the_instructor_account_should_be_deactivated() {
	boolean isDeactivated = userService.isInstructorDeactivated("mike@example.com");
    assert isDeactivated : "Instructor account was not deactivated.";
}

@Then("the instructor should receive a notification about the deactivation")
public void the_instructor_should_receive_a_notification_about_the_deactivation() {
	 boolean isNotified = notificationService.sendDeactivationNotification("mike@example.com");
     assert isNotified : "Instructor was not notified about the deactivation.";
}

@When("the admin adds a new client account with valid details")
public void the_admin_adds_a_new_client_account_with_valid_details() {
	userService.addClient("Alice Smith", "alice@example.com", "clientpassword123");
}

@Then("the new client account should be created successfully")
public void the_new_client_account_should_be_created_successfully() {
	boolean isClientCreated = userService.isClientCreated("alice@example.com");
    assert isClientCreated : "Client account was not created successfully.";
}

@Then("the client should receive a welcome email")
public void the_client_should_receive_a_welcome_email() {
	 boolean isEmailSent = emailService.sendWelcomeEmail("alice@example.com");
     assert isEmailSent : "Welcome email was not sent to the client."; 
}

@Given("a client account exists with valid details")
public void a_client_account_exists_with_valid_details() {
	userService.addClient("Bob White", "bob@example.com", "password123");
}

@When("the admin updates the client's account details with new information")
public void the_admin_updates_the_client_s_account_details_with_new_information() {
	 userService.updateClientDetails("bob@example.com", "bob_updated@example.com", "newpassword123");
}

@Then("the client account should be updated successfully")
public void the_client_account_should_be_updated_successfully() {
	  boolean isUpdated = userService.isClientUpdated("bob_updated@example.com");
      assert isUpdated : "Client account was not updated successfully.";
}

@Then("the client should receive a notification about the changes")
public void the_client_should_receive_a_notification_about_the_changes() {
	 boolean isNotified = notificationService.sendAccountUpdateNotification("bob_updated@example.com");
     assert isNotified : "Client was not notified about account changes.";
}

@Given("a client account exists")
public void a_client_account_exists() {
	 userService.addClient("Charlie Brown", "charlie@example.com", "clientpassword123");
}

@When("the admin deactivates the client account")
public void the_admin_deactivates_the_client_account() {
	userService.deactivateClient("charlie@example.com");
}

@Then("the client account should be deactivated")
public void the_client_account_should_be_deactivated() {
	boolean isDeactivated = userService.isClientDeactivated("charlie@example.com");
    assert isDeactivated : "Client account was not deactivated.";
}

@Then("the client should receive a notification about the deactivation")
public void the_client_should_receive_a_notification_about_the_deactivation() {
	 boolean isNotified = notificationService.sendDeactivationNotification("charlie@example.com");
     assert isNotified : "Client was not notified about the deactivation.";
}
}
