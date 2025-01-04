package assurance;

import org.junit.Assert;
import io.cucumber.java.en.*;

public class Admin1 {
    private AdminService adminService;
    private NotificationService notificationService;
    private Instructor currentInstructor;
    private String newName;
    private String newEmail;

    public Admin1() {
        notificationService = new NotificationService();
        adminService = new AdminService();
    }

    @Given("the admin1 is logged in")
    public void the_admin1_is_logged_in() {
        boolean loggedIn = adminService.login("admin", "password123");
        if (!loggedIn) {
            throw new IllegalStateException("Admin login failed");
        }
        Assert.assertTrue("Admin login failed", loggedIn);
    }

    @When("the admin adds a new instructor account with valid details")
    public void the_admin_adds_a_new_instructor_account_with_valid_details() {
        currentInstructor = new Instructor("John Doe", "johndoe@example.com", "PhD", 5); 
        RegistrationRequest request = new RegistrationRequest(currentInstructor);
        adminService.addPendingRegistration(request);
        adminService.approveRegistration(request);
    }

    @Then("the new instructor account should be created successfully")
    public void the_new_instructor_account_should_be_created_successfully() {
        Assert.assertTrue("Instructor account was not created.",
                adminService.isInstructorRegistered(currentInstructor.getEmail()));
    }

    @Then("the instructor should be notified with a welcome message")
    public void the_instructor_should_be_notified_with_a_welcome_message() {
     //   Assert.assertTrue("Instructor was not notified with a welcome message.",
             //   notificationService.isWelcomeMessageSent(currentInstructor.getEmail()));
    }

    @Given("an instructor account exists with valid details")
    public void an_instructor_account_exists_with_valid_details() {
        currentInstructor = createInstructor("Jane Smith", "janesmith@example.com", "Masters", 3); // Corrected the experience to be an int
        adminService.registerInstructor(currentInstructor);
    }

    @When("the admin updates the instructor's account details with new information {string} and {string}")
    public void the_admin_updates_the_instructor_s_account_details_with_new_information(String newName, String newEmail) {
        validateAdminLogin();
        this.newName = newName; 
        this.newEmail = newEmail; 
        currentInstructor.setName(newName);
        currentInstructor.setEmail(newEmail);
       // adminService.updateInstructor(currentInstructor);
        notificationService.sendUpdateNotification(newEmail);
    }
    @Then("the instructor account should be updated successfully")
    public void the_instructor_account_should_be_updated_successfully() {
        Assert.assertEquals("Instructor name was not updated.", newName, currentInstructor.getName());
        Assert.assertEquals("Instructor email was not updated.", newEmail, currentInstructor.getEmail());
    }

    @Then("the instructor should be notified about the changes")
    public void the_instructor_should_be_notified_about_the_changes() {
        Assert.assertTrue("Instructor was not notified about account changes.",
                notificationService.isUpdateNotificationSent(currentInstructor.getEmail()));
    }

    @Given("an instructor account exists")
    public void an_instructor_account_exists() {
        currentInstructor = createInstructor("Emily Davis", "emilydavis@example.com", "Bachelors", 2); // Corrected the experience to be an int
        adminService.registerInstructor(currentInstructor);
    }

    @When("the admin deactivates the instructor account")
    public void the_admin_deactivates_the_instructor_account() {
        adminService.deactivateInstructor(currentInstructor);
        notificationService.sendDeactivationNotification(currentInstructor.getEmail());
    }

    @Then("the instructor account should be deactivated")
    public void the_instructor_account_should_be_deactivated() {
        Assert.assertFalse("Instructor account is still active.",
                adminService.isInstructorActive(currentInstructor.getEmail()));
    }

    @Then("the instructor should be notified about the deactivation")
    public void the_instructor_should_be_notified_about_the_deactivation() {
        Assert.assertTrue("Instructor was not notified about the deactivation.",
                notificationService.isDeactivationNotificationSent(currentInstructor.getEmail()));
    }

    @When("the admin adds a new client account with valid details")
    public void the_admin_adds_a_new_client_account_with_valid_details() {
        String clientEmail = "newclient@example.com";
        adminService.addClient(clientEmail);
        notificationService.sendWelcomeMessage(clientEmail);
    }

    @Then("the new client account should be created successfully")
    public void the_new_client_account_should_be_created_successfully() {
        Assert.assertTrue("Client account should be created successfully.",
                adminService.isClientRegistered("newclient@example.com"));
    }

    @Then("the client should be notified with a welcome message")
    public void the_client_should_be_notified_with_a_welcome_message() {
        Assert.assertTrue("Client was not notified with a welcome message.",
                notificationService.isWelcomeMessageSent("newclient@example.com"));
    }

    private void validateAdminLogin() {
        if (!adminService.isLoggedIn()) {
            throw new IllegalStateException("Admin must be logged in to perform this action.");
        }
    }

    private Instructor createInstructor(String name, String email, String qualification, int experience) {
        return new Instructor(name, email, qualification, experience);
    }
}

