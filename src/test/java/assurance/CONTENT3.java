package assurance;

import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.Map;

public class CONTENT3 {
    private AdminService adminService;
    private FeedbackService feedbackService;
    private Feedback currentFeedback;
    private String rejectionReason;

    public CONTENT3() {
        adminService = new AdminService();
        feedbackService = new FeedbackService();

        // Initialize sample data
        initializeSampleFeedback();
    }

    private void initializeSampleFeedback() {
        feedbackService.addFeedback(new Feedback("haya naaem", "Pending"));
        feedbackService.addFeedback(new Feedback("yazan taleb", "Pending"));
        feedbackService.addFeedback(new Feedback("hadi belal", "Pending"));
        feedbackService.addFeedback(new Feedback("Sarah mosah", "Pending"));
    }

    @Given("the admin is logged into the system")
    public void the_admin_is_logged_into_the_system() {
        boolean loggedIn = adminService.login("admin", "password123");
        Assert.assertTrue("Admin login failed", loggedIn);
    }

    @When("the admin navigates to the feedback and complaints management section")
    public void the_admin_navigates_to_the_feedback_and_complaints_management_section() {
        Assert.assertTrue("Admin navigation to feedback section failed", adminService.navigateToFeedbackSection());
    }

    @When("the admin views the feedback or complaint submitted by the user {string}")
    public void the_admin_views_the_feedback_or_complaint_submitted_by_the_user(String userName) {
        currentFeedback = feedbackService.getFeedbackByUser(userName);
        Assert.assertNotNull("Feedback not found for user: " + userName, currentFeedback);
    }

    @When("the admin decides to {string} the feedback or complaint")
    public void the_admin_decides_to_the_feedback_or_complaint(String action) {
        if (action.equalsIgnoreCase("Resolve")) {
            feedbackService.resolveFeedback(currentFeedback);
        } else if (action.equalsIgnoreCase("Reject")) {
            Assert.fail("Rejection requires a reason. Use the appropriate step definition.");
        } else {
            Assert.fail("Invalid action: " + action);
        }
        if (action.equalsIgnoreCase("Resolve")) {
            feedbackService.resolveFeedback(currentFeedback);
        } else if (action.equalsIgnoreCase("Reject")) {
            feedbackService.rejectFeedback(currentFeedback, "Default reason");
        } else {
            Assert.fail("Invalid action: " + action);
        }

    }
    @When("if the action is {string}, the admin enters the rejection reason {string}")
    public void if_the_action_is_reject_the_admin_enters_the_rejection_reason(String action, String rejectionReason) {
        Map<String, Feedback> feedbackDatabase = new HashMap<>();
         Feedback feedback1 = new Feedback("Feedback1", "Pending");
        Feedback feedback2 = new Feedback("Feedback2", "Pending");
       feedback1.setContent("Initial content, needs improvement");
        feedback2.setContent("Well structured content");
         feedbackDatabase.put("Feedback1", feedback1);
        feedbackDatabase.put("Feedback2", feedback2);
        if ("Reject".equalsIgnoreCase(action)) {
            Feedback feedback = feedbackDatabase.get("Feedback1"); 
            if (feedback == null) {
                throw new IllegalArgumentException("Feedback not found");
            }
            feedback.setRejectionReason(rejectionReason);
            feedback.setStatus("Rejected");
            feedbackService.rejectFeedback(feedback, rejectionReason); 
        }
    }

    @Then("the system should update the status of the feedback or complaint to {string}")
    public void the_system_should_update_the_status_of_the_feedback_or_complaint_to(String expectedStatus) {
        Assert.assertEquals("Feedback status update failed", expectedStatus, currentFeedback.getStatus());
    }

    @Then("the system should notify the user {string} about the resolution via email or in-app notification")
    public void the_system_should_notify_the_user_about_the_resolution_via_email_or_in_app_notification(String userName) {
        boolean notificationSent = feedbackService.notifyUser(userName, currentFeedback.getStatus(), rejectionReason);
        Assert.assertTrue("Notification to user " + userName + " failed", notificationSent);
    }
    @Then("the admin should be able to see a notification confirming the feedback or complaint status change")
    public void the_admin_should_be_able_to_see_a_notification_confirming_the_feedback_or_complaint_status_change() {
        if (currentFeedback == null) {
            System.out.println("ERROR: currentFeedback is null.");
            Assert.fail("Feedback object is null");
        }
       if (adminService == null) {
            System.out.println("ERROR: adminService is null.");
            Assert.fail("Admin service is not initialized");
        }
        boolean notificationVisible = adminService.isFeedbackStatusNotificationVisible(currentFeedback);
        Assert.assertTrue("Feedback status change notification is not visible to the admin", notificationVisible);
    }

  
}
