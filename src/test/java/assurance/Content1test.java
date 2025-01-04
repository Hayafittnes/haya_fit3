package assurance;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Content1test {

    private AdminService adminService;
    private NotificationService notificationService;
    private Content currentContent;
    private boolean isAdminLoggedIn;
    private Feedback currentFeedback;
    
    @Before
    public void setUp() {
    	currentContent = new Content("Sample Content Title");
    	currentContent.setStatus("Pending");
        adminService = new AdminService();
        notificationService = new NotificationService();
        isAdminLoggedIn = true; 
        adminService.setAdminLoginStatus(isAdminLoggedIn);
        adminService.setAdminLoginStatus(true);
        currentFeedback = new Feedback("User", "Pending");
        currentFeedback.setId("Feedback1");
    }
    @Test
    public void testAdminLogin() {
        adminService.setAdminLoginStatus(true); 
        assertTrue("Admin should be logged in", adminService.isLoggedIn());
        adminService.setAdminLoginStatus(false); 
        assertFalse("Admin should be logged out", adminService.isLoggedIn());
    }
    @Test
    public void testNavigateToContentManagement() {
        adminService.navigateToContentManagement();
        assertTrue("Navigation to content management failed.", adminService.isInContentManagement());
    }

    @Test
    public void testViewSubmittedContent() {
        adminService.viewSubmittedContent();
        assertNotNull("Submitted content should not be null.", adminService.getSubmittedContent1());
    }

    @Test
    public void testApproveContent() {
        adminService.setAdminLoginStatus(true); 
        Content content = new Content("Test Content");
        content.setStatus("Pending");
        adminService.approveContent(content);
        assertEquals("Approved", content.getStatus());
    }
    @Test
    public void testRejectContentWithReason() {
        currentContent = new Content("Rejected Content");
        currentContent.setStatus("Pending"); 
        String rejectionReason = "Incomplete details"; 
        currentContent.setRejectionReason(rejectionReason); 

        adminService.rejectContent(currentContent);

        assertEquals("Content status was not updated to Rejected.", "Rejected", currentContent.getStatus());
        assertEquals("Rejection reason was not set correctly.", rejectionReason, currentContent.getRejectionReason());
      //  assertTrue("Instructor was not notified about the content rejection.",
          //      notificationService.isContentStatusChangeNotified(currentContent.getTitle()));
    }
    
    @Test
    public void testAdminNotificationAfterStatusChange() {
        currentContent = new Content("Status Change Content");
        currentContent.setStatus("Pending");
 adminService.setAdminLoginStatus(true); 
 adminService.approveContent(currentContent);
   assertEquals("Content status was not updated to Approved.", "Approved", currentContent.getStatus());
 //  assertTrue("Admin was not notified about the content status change.", 
   // adminService.isAdminNotifiedAboutContentStatusChange(currentContent.getTitle()));
    }
    @Test
    public void testAdminLoginAndApproveContent() {
        AdminService adminService = new AdminService();
          adminService.login("admin", "password123");  
        Content currentContent = new Content("Test Content");
        currentContent.setStatus("Pending");
       adminService.approveContent(currentContent);
       assertEquals("Approved", currentContent.getStatus());
    }
    @Test
    public void testNotifyInstructor() {
        notificationService.notifyInstructor("Sample Title", "Sample message");
        assertTrue(notificationService.isContentStatusChangeNotified("Sample Title"));
    }

}
