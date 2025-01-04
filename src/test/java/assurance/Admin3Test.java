package assurance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Admin3Test{
    private UserService userService;
    private ReportService reportService;
    private Content currentContent;
    @Before
    public void setUp() {
        userService = new UserService();
        reportService = new ReportService(userService);
        currentContent = new Content("Content Title");
        currentContent.setStatus("Pending");
    }

    @Test
    public void testAdminLogin() {
        AdminService adminService = new AdminService();
        boolean isLoggedIn = adminService.login("admin", "password123");
        assertTrue("Admin login should succeed with valid credentials", isLoggedIn);
    }

    @Test
    public void testOverallUserActivityReport() {
        reportService.fetchOverallUserActivity();
        
        int activeInstructors = userService.getActiveInstructorsCount();
        int activeClients = userService.getActiveClientsCount();
        
        assertTrue("Active instructors count should be >= 0", activeInstructors >= 0);
        assertTrue("Active clients count should be >= 0", activeClients >= 0);
    }

    @Test
    public void testTotalLoginsForUserRoles() {
        int instructorLogins = userService.getTotalInstructorLogins();
        int clientLogins = userService.getTotalClientLogins();
        
        assertTrue("Instructor login count should be >= 0", instructorLogins >= 0);
        assertTrue("Client login count should be >= 0", clientLogins >= 0);
    }

    @Test
    public void testInstructorActivityReport() {
        reportService.fetchInstructorActivityReport();
        
        int programsCreated = userService.getProgramsCreatedByInstructors();
        int clientsAssigned = userService.getClientsAssignedToInstructors();
        int loginFrequency = userService.getInstructorLoginFrequency();
        
        assertTrue("Programs created by instructors should be >= 0", programsCreated >= 0);
        assertTrue("Clients assigned to instructors should be >= 0", clientsAssigned >= 0);
        assertTrue("Instructor login frequency should be >= 0", loginFrequency >= 0);
    }

    @Test
    public void testClientEngagementReport() {
        reportService.fetchClientEngagementReport();
        
        int programsEnrolled = userService.getProgramsEnrolledByClients();
        int completedWorkouts = userService.getCompletedWorkoutsForClients();
        int loginFrequency = userService.getClientLoginFrequency();
        
        assertTrue("Programs enrolled by clients should be >= 0", programsEnrolled >= 0);
        assertTrue("Completed workouts for clients should be >= 0", completedWorkouts >= 0);
        assertTrue("Client login frequency should be >= 0", loginFrequency >= 0);
    }

    @Test
    public void testGenerateDetailedEngagementReport() {
        reportService.generateDetailedEngagementReport();
        
        String report = reportService.getDetailedEngagementReport();
        assertNotNull("The detailed report should not be null", report);
        assertTrue("The report should contain data for instructors", report.contains("Instructors"));
        assertTrue("The report should contain data for clients", report.contains("Clients"));
    }
    @Test
    public void testEngagementTrendsOverTime() {
        String trends = reportService.getEngagementTrends();
        assertNotNull("Engagement trends should not be null", trends);
         assertTrue("Trends should include data over time",  trends.toLowerCase().contains("weekly") || trends.toLowerCase().contains("monthly"));
    }
    @Test
    public void testExportReport() {
        boolean isExportedCsv = reportService.exportReport("CSV");
        boolean isExportedPdf = reportService.exportReport("PDF");
        
        assertTrue("Report should be exported in CSV format", isExportedCsv);
        assertTrue("Report should be exported in PDF format", isExportedPdf);
    }
}

