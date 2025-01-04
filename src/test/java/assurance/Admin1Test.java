package assurance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Admin1Test {

    private AdminService adminService;
    private Instructor testInstructor;
    private Content currentContent;
    @Before
    public void setUp() {
        adminService = new AdminService();
        testInstructor = new Instructor("John Doe", "johndoe@example.com", "PhD", 5);
        currentContent = new Content("Content Title");
        currentContent.setStatus("Pending");

        boolean loggedIn = adminService.login("admin", "password123");
        if (!loggedIn) {
            throw new IllegalStateException("Admin login failed");
        }
        if (!adminService.isInstructorRegistered(testInstructor.getEmail())) {
            adminService.registerInstructor(testInstructor);
        }
    }

    @Test
    public void testAdminLoginSuccess() {
        boolean loggedIn = adminService.login("admin", "password123");
        assertTrue("Admin should be able to log in with correct credentials", loggedIn);
    }

    @Test
    public void testInstructorRegistration() {
        if (!adminService.isInstructorRegistered(testInstructor.getEmail())) {
            adminService.registerInstructor(testInstructor);
        }

        assertTrue("Instructor should be registered", adminService.isInstructorRegistered(testInstructor.getEmail()));
    }

    @Test
    public void testInstructorDeactivation() {
        adminService.deactivateInstructor(testInstructor);
        assertFalse("Instructor account should be deactivated",
                adminService.isInstructorActive(testInstructor.getEmail()));
    }

    @Test
    public void testUpdateInstructorDetails() {
        AdminService adminService = new AdminService();
        adminService.login("admin", "password123");
        
        Instructor instructor = new Instructor("John Doe", "john.doe@example.com", "PhD in Computer Science", 10);
        adminService.registerInstructor(instructor);
      
        instructor.setQualifications("PhD in Data Science");
        adminService.updateInstructor(instructor);
        Instructor updatedInstructor = adminService.getInstructorByEmail("john.doe@example.com");
        assertEquals("PhD in Data Science", updatedInstructor.getQualifications());
    }

    @Test
    public void testClientRegistration() {
        if (!adminService.isInstructorRegistered(testInstructor.getEmail())) {
            adminService.registerInstructor(testInstructor);
        }

        String clientEmail = "client@example.com";
        adminService.addClient(clientEmail);
        assertTrue("Client should be registered", adminService.isClientRegistered(clientEmail));
    }
}

