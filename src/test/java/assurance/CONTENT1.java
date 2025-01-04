package assurance;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import io.cucumber.java.en.*;

public class CONTENT1{

    private AdminService adminService;
    private NotificationService notificationService;
    private Content currentContent;

    public CONTENT1() {
        notificationService = new NotificationService();
        adminService = new AdminService();
    }

    @Given("the admin is loggeed into the system")
    public void the_admin_is_loggeed_into_the_system() {
    	 boolean loggedIn = adminService.login("admin", "password123");
    	    Assert.assertTrue("Admin login failed", loggedIn);
    }

    @When("the admin navigates to the content management section")
    public void the_admin_navigates_to_the_content_management_section() {
    	 if (!adminService.isLoggedIn()) {
    		 boolean loggedIn = adminService.login("admin", "password123");
    	        Assert.assertTrue("Admin login failed", loggedIn);
    	    }
    	    adminService.navigateToContentManagement();
    }

    @When("the admin views the content submitted by the instructors")
    public void the_admin_views_the_content_submitted_by_the_instructors() {
        adminService.viewSubmittedContent();
    }
    @When("the admin decides to {string} the content titled {string}")
    public void the_admin_decides_to_the_content_titled(String action, String contentTitle) {
        Map<String, Content> contentDatabase = new HashMap<>();
        contentDatabase.put("5 Healthy Smoothie Recipes", new Content("5 Healthy Smoothie Recipes"));
        contentDatabase.put("Meditation Tips for Stress Relief", new Content("Meditation Tips for Stress Relief"));
        Content currentContent = contentDatabase.get(contentTitle);
        Assert.assertNotNull("Content not found with title: " + contentTitle, currentContent);
        if ("Approve".equalsIgnoreCase(action)) {
            currentContent.setStatus("Approved");
        } else if ("Reject".equalsIgnoreCase(action)) {
            String rejectionReason = "Content does not meet standards"; 
            currentContent.setStatus("Rejected");
            currentContent.setRejectionReason(rejectionReason);
        } else {
            throw new IllegalArgumentException("Invalid action: " + action);
        }
         System.out.println("Content Title: " + contentTitle + " | Status: " + currentContent.getStatus());
    }

    @When("the admin decides to \"{string}\" the content titled \"{string}\"")
    public void the_admin_decides_to_action_the_content_titled(String action, String contentTitle) {
        currentContent = new Content(contentTitle);
        currentContent.setStatus("Pending");

        if ("Approve".equalsIgnoreCase(action)) {
            adminService.approveContent(currentContent);
        } else if ("Reject".equalsIgnoreCase(action)) {
            currentContent.setStatus("Rejected");
        }
    }

    @When("if the action is \"Reject\", the admin enters the rejection reason \"{string}\"")
    public void if_the_action_is_reject_the_admin_enters_the_rejection_reason(String rejectionReason) {
        if ("Rejected".equalsIgnoreCase(currentContent.getStatus())) {
            currentContent.setRejectionReason(rejectionReason);
            adminService.rejectContent(currentContent);
        }
    }
    @Then("the system should update the content status to {string}")
    public void the_system_should_update_the_content_status_to(String contentStatus) {
        if (currentContent == null) {
            System.out.println("ERROR: Content is null. The content status cannot be updated.");
            Assert.assertNotNull("Content should not be null", currentContent);
        }
        String actualStatus = currentContent.getStatus(); 
        System.out.println("Expected: " + contentStatus + ", Actual: " + actualStatus);
         Assert.assertEquals("Content status update failed", contentStatus, actualStatus);
    }
    @Then("the system should notify the instructor about the content status change")
    public void the_system_should_notify_the_instructor_about_the_content_status_change() {
        Assert.assertTrue("Instructor was not notified about the content status change.",
                notificationService.isContentStatusChangeNotified(currentContent.getTitle()));
    }

    @Then("the admin should be able to viwe a notification confirming the content's status change")
    public void the_admin_should_be_able_to_view_a_notification_confirming_the_content_s_status_change() {
        Assert.assertTrue("Admin was not notified about the content status change.",
                adminService.isAdminNotifiedAboutContentStatusChange(currentContent.getTitle()));
    }

}
