package assurance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Admin2 {
    private AdminService admin = new AdminService();
    private RegistrationRequest currentRequest;
    private Instructor currentInstructor;

    @Given("the admin2 is logged in")
    public void the_admin2_is_logged_in() {
        boolean loggedIn = admin.login("admin", "password123");
        Assert.assertTrue("Admin login failed", loggedIn);
    }

    @When("the admin views the list of pending instructor registrations")
    public void the_admin_views_the_list_of_pending_instructor_registrations() {
        List<RegistrationRequest> pendingRequests = admin.getPendingRegistrations();
        assertNotNull("Pending registrations list should not be null", pendingRequests);
    }

    @Then("the admin should see all instructor registration requests that are pending approval")
    public void the_admin_should_see_all_instructor_registration_requests_that_are_pending_approval() {
        List<RegistrationRequest> pendingRequests = admin.getPendingRegistrations();
       // assertTrue("There should be pending registration requests.", !pendingRequests.isEmpty());
    }

    @Given("there is a pending instructor registration")
    public void there_is_a_pending_instructor_registration() {
        
        currentInstructor = new Instructor("Haya Kh", "hayakh@example.com", "Bachelors in Education", 5); 
        currentRequest = new RegistrationRequest(currentInstructor);
        admin.addPendingRegistration(currentRequest);
    }

    @When("the admin approves the pending instructor registration")
    public void the_admin_approves_the_pending_instructor_registration() {
        admin.approveRegistration(currentRequest);
    }

    @Then("the instructor should be approved and granted access to the system")
    public void the_instructor_should_be_approved_and_granted_access_to_the_system() {
        assertTrue("Instructor should be approved", currentRequest.isApproved());
    }

    @Then("the instructor should receive a confirmation email about their approval")
    public void the_instructor_should_receive_a_confirmation_email_about_their_approval() {
       // assertTrue("Instructor should receive approval email", 
         //   admin.isAdminNotifiedAboutContentStatusChange(currentInstructor.getEmail()));
    }

    @When("the admin rejects the pending instructor registration")
    public void the_admin_rejects_the_pending_instructor_registration() {
        admin.rejectRegistration(currentRequest);
    }

    @Then("the instructor should be notified that their registration was rejected")
    public void the_instructor_should_be_notified_that_their_registration_was_rejected() {
        assertTrue("Instructor should receive rejection notification", currentRequest.isRejected());
    }

    @Then("the registration request should be marked as rejected in the system")
    public void the_registration_request_should_be_marked_as_rejected_in_the_system() {
        assertTrue("Registration request should be rejected", currentRequest.isRejected());
    }

    @When("the admin views the details of the pending instructor registration")
    public void the_admin_views_the_details_of_the_pending_instructor_registration() {
        admin.viewRegistrationDetails(currentRequest);
    }
    @Then("the admin should be able to see the full registration information e.g., qualifications, experience, and contact info")
    public void the_admin_should_be_able_to_see_the_full_registration_information() {
    	  assertNotNull("Qualifications should not be null", currentInstructor.getQualifications());
          assertNotNull("Experience should not be null", currentInstructor.getExperience());
          assertNotNull("Contact info should not be null", currentInstructor.getEmail());
          assertFalse("Qualifications should not be empty", currentInstructor.getQualifications().isEmpty());
          assertTrue("Contact info should be a valid email", currentInstructor.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$"));
          assertTrue("Email should be valid", currentInstructor.getEmail().contains("@"));
          assertEquals("Expected qualifications mismatch", "Bachelors in Education", currentInstructor.getQualifications());    
    }
    @Then("the admin should have the option to approve or reject the registration")
    public void the_admin_should_have_the_option_to_approve_or_reject_the_registration() {
        assertTrue("Admin should have the option to approve or reject", admin.hasApprovalOrRejectionOption(currentRequest));
    }
}
