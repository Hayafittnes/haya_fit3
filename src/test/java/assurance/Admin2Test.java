package assurance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class Admin2Test {

    private AdminService adminService;
    private RegistrationRequest testRequest;
    private Instructor testInstructor;
    private Content currentContent;
    	@Before
    	public void setUp() {
    		currentContent = new Content("Content Title");
            currentContent.setStatus("Pending");
    	    adminService = new AdminService();
    	    try {
    	        adminService.login("admin", "password123"); 
    	    } catch (IllegalArgumentException e) {
    	          System.out.println("Login failed: " + e.getMessage());
    	    }
    	    testInstructor = new Instructor("Haya Kh", "hayakh@example.com", "Bachelors in Education", 5); 
    	    testRequest = new RegistrationRequest(testInstructor);
    	}
    @Test
    public void testAdminLogin() {
        boolean isLoggedIn = adminService.login("admin", "password123");
        assertTrue("Admin should be able to log in with valid credentials", isLoggedIn);
    }

    @Test
    public void testViewPendingRegistrations() {
        adminService.addPendingRegistration(testRequest);
        List<RegistrationRequest> pendingRequests = adminService.getPendingRegistrations();
        assertNotNull("Pending registration list should not be null", pendingRequests);
        assertFalse("Pending registration list should not be empty", pendingRequests.isEmpty());
        assertEquals("Pending registration list should contain the correct request", testRequest, pendingRequests.get(0));
    }

    @Test
    public void testApproveInstructorRegistration() {
        adminService.addPendingRegistration(testRequest);
        adminService.approveRegistration(testRequest);
        assertTrue("The request should be approved", testRequest.isApproved());
        assertEquals(true, adminService.isInstructorRegistered(testInstructor.getEmail()));

    }

    @Test
    public void testRejectInstructorRegistration() {
        adminService.addPendingRegistration(testRequest);
        adminService.rejectRegistration(testRequest);

        assertTrue("The request should be rejected", testRequest.isRejected());
        assertFalse("Instructor should not be granted access", adminService.isInstructorRegistered(testInstructor.getEmail()));
    }

    @Test
    public void testViewRegistrationDetails() {
        adminService.addPendingRegistration(testRequest);
        String details = adminService.viewRegistrationDetails(testRequest);
        assertNotNull(details);
        assertTrue("Details should include qualifications", details.contains(testInstructor.getQualifications()));
        assertTrue("Details should include experience", details.contains(String.valueOf(testInstructor.getExperience()))); 
        assertTrue("Details should include email", details.contains(testInstructor.getEmail()));
    }

    @Test
    public void testAdminOptionsForApprovalOrRejection() {
        adminService.addPendingRegistration(testRequest);
        assertTrue("Admin should have approval/rejection options", adminService.hasApprovalOrRejectionOption(testRequest));
    }
}
