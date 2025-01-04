package assurance;

import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Admin3 {
    private UserService userService = new UserService(); 
    private ReportService reportService = new ReportService(userService);

    @Given("the admin is logged in")
    public void the_admin_is_logged_in() {
        AdminService adminService = new AdminService();
        boolean loggedIn = adminService.login("admin", "password123");
        assertTrue("Admin login failed", loggedIn);
    }

    @When("the admin views the overall user activity report")
    public void the_admin_views_the_overall_user_activity_report() {
        reportService.fetchOverallUserActivity();
    }

    @Then("the admin should see the total number of active instructors and clients")
    public void the_admin_should_see_the_total_number_of_active_instructors_and_clients() {
        int activeInstructors = userService.getActiveInstructorsCount();
        int activeClients = userService.getActiveClientsCount();
        assertTrue("Active instructors count should be greater than or equal to 0", activeInstructors >= 0);
        assertTrue("Active clients count should be greater than or equal to 0", activeClients >= 0);
    }

    @Then("the admin should see the total number of logins for each user role \\(instructors and clients)")
    public void the_admin_should_see_the_total_number_of_logins_for_each_user_role_instructors_and_clients() {
        int instructorLogins = userService.getTotalInstructorLogins();
        int clientLogins = userService.getTotalClientLogins();
        assertTrue("Instructor login count should be greater than or equal to 0", instructorLogins >= 0);
        assertTrue("Client login count should be greater than or equal to 0", clientLogins >= 0);
    }

    @When("the admin views the instructor activity report")
    public void the_admin_views_the_instructor_activity_report() {
        reportService.fetchInstructorActivityReport();
    }

    @Then("the admin should see the number of programs created by each instructor")
    public void the_admin_should_see_the_number_of_programs_created_by_each_instructor() {
        int programsCreated = userService.getProgramsCreatedByInstructors();
        assertTrue("Programs created by instructor should be greater than or equal to 0", programsCreated >= 0);
    }

    @Then("the admin should see the number of clients assigned to each instructor")
    public void the_admin_should_see_the_number_of_clients_assigned_to_each_instructor() {
        int clientsAssigned = userService.getClientsAssignedToInstructors();
        assertTrue("Clients assigned should be greater than or equal to 0", clientsAssigned >= 0);
    }

    @Then("the admin should see the frequency of instructor logins")
    public void the_admin_should_see_the_frequency_of_instructor_logins() {
        int loginFrequency = userService.getInstructorLoginFrequency();
        assertTrue("Instructor login frequency should be greater than or equal to 0", loginFrequency >= 0);
    }

    @When("the admin views the client engagement report")
    public void the_admin_views_the_client_engagement_report() {
        reportService.fetchClientEngagementReport();
    }

    @Then("the admin should see the number of fitness programs the clients have enrolled in")
    public void the_admin_should_see_the_number_of_fitness_programs_the_clients_have_enrolled_in() {
        int programsEnrolled = userService.getProgramsEnrolledByClients();
        assertTrue("Programs enrolled by clients should be greater than or equal to 0", programsEnrolled >= 0);
    }

    @Then("the admin should see the number of completed workouts for each client")
    public void the_admin_should_see_the_number_of_completed_workouts_for_each_client() {
        int completedWorkouts = userService.getCompletedWorkoutsForClients();
        assertTrue("Completed workouts should be greater than or equal to 0", completedWorkouts >= 0);
    }

    @Then("the admin should see the frequency of client logins")
    public void the_admin_should_see_the_frequency_of_client_logins() {
        int loginFrequency = userService.getClientLoginFrequency();
        assertTrue("Client login frequency should be greater than or equal to 0", loginFrequency >= 0);
    }

    @When("the admin generates a detailed engagement report for instructors and clients")
    public void the_admin_generates_a_detailed_engagement_report_for_instructors_and_clients() {
        reportService.generateDetailedEngagementReport();
    }

    @Then("the report should include detailed activities for both instructors and clients")
    public void the_report_should_include_detailed_activities_for_both_instructors_and_clients() {
        String report = reportService.getDetailedEngagementReport();
        assertNotNull("The detailed report should not be null", report);
        assertTrue("The report should contain data for instructors and clients", report.contains("Instructors") && report.contains("Clients"));
    }
    @Then("the report should show engagement trends over time \\(e.g., weekly or monthly)")
    public void the_report_should_show_engagement_trends_over_time_e_g_weekly_or_monthly() {
        String trends = reportService.getEngagementTrends();
        assertNotNull("Engagement trends should not be null", trends);
        assertTrue("Trends should show data over time", 
            trends.toLowerCase().contains("weekly") || trends.toLowerCase().contains("monthly"));
    }

    @Then("the admin should be able to export the report in CSV or PDF format")
    public void the_admin_should_be_able_to_export_the_report_in_csv_or_pdf_format() {
        boolean isExportedCsv = reportService.exportReport("CSV");
        assertTrue("Report should be exported in CSV format", isExportedCsv);

        boolean isExportedPdf = reportService.exportReport("PDF");
        assertTrue("Report should be exported in PDF format", isExportedPdf);
    }
}
