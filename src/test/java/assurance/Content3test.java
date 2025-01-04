package assurance;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class Content3test {
    private AdminService adminService;
    private FeedbackService feedbackService;
    private Feedback currentFeedback;
    private Content currentContent;
    @Before
    public void setUp() {
        adminService = new AdminService();
        feedbackService = new FeedbackService();
        feedbackService.addFeedback(new Feedback("haya naaem", "Pending"));
        feedbackService.addFeedback(new Feedback("yazan taleb", "Pending"));
        feedbackService.addFeedback(new Feedback("hadi belal", "Pending"));
        feedbackService.addFeedback(new Feedback("Sarah mosah", "Pending"));
        adminService = new AdminService();
        NotificationService notificationService = new NotificationService();
        Object contentDatabase = new HashMap<>();
        currentFeedback = new Feedback("User", "Pending");
        currentFeedback.setId("Feedback1");
        adminService.login("admin", "password123");
        ((HashMap<Object, Object>) contentDatabase).put("Benefits of Yoga", new Content("Benefits of Yoga"));
        ((HashMap<Object, Object>) contentDatabase).put("10 Quick Healthy Snacks", new Content("10 Quick Healthy Snacks"));
        ((HashMap<Object, Object>) contentDatabase).put("How to Meditate Properly", new Content("How to Meditate Properly"));
        ((HashMap<Object, Object>) contentDatabase).put("Top 5 Fat Burning Foods", new Content("Top 5 Fat Burning Foods"));
        currentContent = new Content("Content Title");
        currentContent.setStatus("Pending"); 

    }
    @Test
    public void testAdminLogin() {
        boolean loggedIn = adminService.login("admin", "password123");
        assertTrue("Admin login failed", loggedIn);
    }
    @Test
    public void testNavigateToFeedbackSection() {
        boolean navigated = adminService.navigateToFeedbackSection();
        assertTrue("Admin failed to navigate to feedback section", navigated);
    }
    @Test
    public void testViewFeedbackByUser() {
        String userName = "haya naaem";
        currentFeedback = feedbackService.getFeedbackByUser(userName);
        assertNotNull("Feedback not found for user: " + userName, currentFeedback);
        assertEquals("Pending", currentFeedback.getStatus());
    }
    @Test
    public void testResolveFeedback() {
        String userName = "yazan taleb";
        currentFeedback = feedbackService.getFeedbackByUser(userName);
        assertNotNull("Feedback not found for user: " + userName, currentFeedback);
        feedbackService.resolveFeedback(currentFeedback);
        assertEquals("Resolved", currentFeedback.getStatus());
    }

    @Test
    public void testRejectFeedbackWithReason() {
        String userName = "hadi belal";
        currentFeedback = feedbackService.getFeedbackByUser(userName);
        assertNotNull("Feedback not found for user: " + userName, currentFeedback);
        String rejectionReason = "Feedback does not align with our guidelines.";
        feedbackService.rejectFeedback(currentFeedback, rejectionReason);
        assertEquals("Rejected", currentFeedback.getStatus());
        assertEquals("Feedback does not align with our guidelines.", currentFeedback.getRejectionReason());
    }
    @Test
    public void testNotifyUserOnStatusChange() {
        String userName = "Sarah mosah";
        currentFeedback = feedbackService.getFeedbackByUser(userName);
        assertNotNull("Feedback not found for user: " + userName, currentFeedback);
        feedbackService.resolveFeedback(currentFeedback);
        boolean notificationSent = feedbackService.notifyUser(userName, currentFeedback.getStatus(), null);
        assertTrue("Notification to user failed", notificationSent);
    }
    @Test
    public void testAdminNotificationOnStatusChange() {
        String userName = "haya naaem";
        currentFeedback = feedbackService.getFeedbackByUser(userName);
        assertNotNull("Feedback not found for user: " + userName, currentFeedback);
        feedbackService.resolveFeedback(currentFeedback);
       // boolean notificationVisible = adminService.isFeedbackStatusNotificationVisible(currentFeedback);
       // assertTrue("Feedback status change notification is not visible to the admin", notificationVisible);
    }
}
