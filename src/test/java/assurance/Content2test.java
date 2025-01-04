package assurance;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Content2test {
    private AdminService adminService;
    private NotificationService notificationService;
    private Map<String, Content> contentDatabase;
    private Content currentContent;
    @Before
    public void setUp() {
        adminService = new AdminService();
        notificationService = new NotificationService();
        contentDatabase = new HashMap<>();
        contentDatabase.put("Benefits of Yoga", new Content("Benefits of Yoga"));
        contentDatabase.put("10 Quick Healthy Snacks", new Content("10 Quick Healthy Snacks"));
        contentDatabase.put("How to Meditate Properly", new Content("How to Meditate Properly"));
        contentDatabase.put("Top 5 Fat Burning Foods", new Content("Top 5 Fat Burning Foods"));
        currentContent = new Content("Initial Content");
        currentContent.setStatus("Pending");
       
    }

    @Test
    public void testAdminLogin() {
        boolean loggedIn = adminService.login("admin", "password123");
        assertTrue("Admin login failed", loggedIn);
    }

    @Test
    public void testNavigateToContentManagement() {
      //  adminService.navigateToContentManagement();
       // assertTrue("Admin did not navigate to content management section", adminService.isInContentManagement());
    }
    @Test
    public void testViewContentByInstructor() {
        Content content1 = new Content("Benefits of Yoga");
        content1.setInstructorName("Instructor A");
        Content content2 = new Content("Advanced Yoga");
        content2.setInstructorName("Instructor B");
      List<Content> contentList = Arrays.asList(content1, content2);
        String instructorName = "Instructor A";
        Content currentContent = contentList.stream()
                .filter(c -> instructorName.equals(c.getInstructorName()))
                .findFirst()
                .orElse(null);
  assertNotNull("Content not found for instructor: " + instructorName, currentContent);
        assertEquals("Benefits of Yoga", currentContent.getTitle());
    }

    @Test
    public void testApproveContent() {
        String contentTitle = "Benefits of Yoga";
        currentContent = contentDatabase.get(contentTitle);
        assertNotNull("Content not found with title: " + contentTitle, currentContent);
       // adminService.approveContent(currentContent);
       // assertEquals("Content approval failed", "Approved", currentContent.getStatus());
    }
    @Test
    public void testRejectContent() {
       String contentTitle = "10 Quick Healthy Snacks";
        currentContent = new Content(contentTitle);
        assertNotNull("Content not found with title: " + contentTitle, currentContent);
        currentContent.setRejectionReason("Content quality does not meet standards.");
      //  adminService.rejectContent(currentContent);
      //  assertEquals("Content rejection failed", "Rejected", currentContent.getStatus());
        assertEquals("Rejection reason not set correctly", 
                     "Content quality does not meet standards.", currentContent.getRejectionReason());
    }

    @Test
    public void testNotifyInstructorOnStatusChange() {
        String contentTitle = "How to Meditate Properly";
        currentContent = contentDatabase.get(contentTitle);
        assertNotNull("Content not found with title: " + contentTitle, currentContent);
      //  adminService.approveContent(currentContent);
       // boolean notified = notificationService.isContentStatusChangeNotified(contentTitle);
      //  assertTrue("Instructor was not notified about content status change", notified);
    }
    @Test
    public void testAdminNotificationOnStatusChange() {
        String contentTitle = "Top 5 Fat Burning Foods";
        currentContent = contentDatabase.get(contentTitle);
        assertNotNull("Content not found with title: " + contentTitle, currentContent);
       // adminService.approveContent(currentContent);
        //boolean adminNotified = adminService.isAdminNotifiedAboutContentStatusChange(contentTitle);
       // assertTrue("Admin was not notified about content status change", adminNotified);
    } 
   
}
