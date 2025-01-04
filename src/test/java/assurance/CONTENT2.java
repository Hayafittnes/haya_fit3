package assurance;

import org.junit.Assert;
import io.cucumber.java.en.*;

import java.util.HashMap;
import java.util.Map;

public class CONTENT2 {

    private AdminService adminService;
    private NotificationService notificationService;
    private Content currentContent;
    private Map<String, Content> contentDatabase;

    public CONTENT2() {
        this.notificationService = new NotificationService();
        this.adminService = new AdminService();
        this.contentDatabase = new HashMap<>();
        initializeTestData();
    }
    private void initializeTestData() {
        contentDatabase.put("Benefits of Yoga", new Content("Benefits of Yoga"));
        contentDatabase.put("10 Quick Healthy Snacks", new Content("10 Quick Healthy Snacks"));
        contentDatabase.put("How to Meditate Properly", new Content("How to Meditate Properly"));
        contentDatabase.put("Top 5 Fat Burning Foods", new Content("Top 5 Fat Burning Foods"));
    }

    @Given("the admien is logged into the system")
    public void the_admin_is_logged_into_the_system() {
        boolean loggedIn = adminService.login("admin", "password123");
        Assert.assertTrue("Admin login failed", loggedIn);
    }

    @When("the admin navigates to the health and wellness content management section")
    public void the_admin_navigates_to_the_health_and_wellness_content_management_section() {
        adminService.navigateToContentManagement();
    }
    @When("the admin views the article or tip submitted by the instructor {string}")
    public void the_admin_views_the_article_or_tip_submitted_by_the_instructor(String instructorName) {
        currentContent = contentDatabase.values()
                .stream()
                .filter(content -> instructorName.equals(content.getInstructorName()))
                .findFirst()
                .orElse(null);
        Assert.assertNotNull("Content not found for instructor: " + instructorName, currentContent);
    }

    @When("the admin decides to {string} the article or tip titled {string}")
    public void the_admin_decides_to_action_the_article_or_tip_titled(String action, String contentTitle) {
        currentContent = contentDatabase.get(contentTitle);
        Assert.assertNotNull("Content not found with title: " + contentTitle, currentContent);

        if ("Approve".equalsIgnoreCase(action)) {
            adminService.approveContent(currentContent);
        } else if ("Reject".equalsIgnoreCase(action)) {
            adminService.rejectContent(currentContent);
        } else {
            throw new IllegalArgumentException("Invalid action: " + action);
        }
    }

    @Then("the system should update the contents status to {string}")
    public void the_system_should_update_the_contents_status_to(String contentStatus) {
        Assert.assertEquals("Content status update failed", contentStatus, currentContent.getStatus());
    }

    @Then("the system should notify the instructor {string} about the content status change via email or in-app notification")
    public void the_system_should_notify_the_instructor_about_the_content_status_change_via_email_or_in_app_notification(String instructorName) {
        String email = currentContent.getInstructorName(); 
        Assert.assertTrue("Notification not sent to instructor: " + instructorName,
                notificationService.isContentStatusChangeNotified(currentContent.getTitle()));
    }

    @Then("the admin should be able to see a notification confirming the content's status change")
    public void the_admin_should_be_able_to_see_a_notification_confirming_the_content_s_status_change() {
        Assert.assertTrue("Admin did not receive notification for content status change",
                adminService.isAdminNotifiedAboutContentStatusChange(currentContent.getTitle()));
    }
}

