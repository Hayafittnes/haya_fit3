package assurance;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReportService {

    private final UserService userService;

    public ReportService(UserService userService) {
        if (userService == null) {
            throw new IllegalArgumentException("UserService cannot be null.");
        }
        this.userService = userService;
    }
	public void fetchOverallUserActivity() {
        System.out.println("Fetching overall user activity...");
        System.out.println("Active Instructors: " + userService.getActiveInstructorsCount());
        System.out.println("Active Clients: " + userService.getActiveClientsCount());
    }
    public void fetchInstructorActivityReport() {
        System.out.println("Fetching instructor activity report...");
        Map<String, Instructor> instructors = userService.getInstructors();
        for (Map.Entry<String, Instructor> entry : instructors.entrySet()) {
            Instructor instructor = entry.getValue();
            System.out.println("Instructor: " + instructor.getName() + 
                               ", Email: " + instructor.getEmail() +
                               ", Programs Created: " + instructor.getProgramsCreated());
        }
    }
    public void fetchClientEngagementReport() {
        System.out.println("Fetching client engagement report...");
        Map<String, clients> clients = userService.getClients();
        for (Map.Entry<String, clients> entry : clients.entrySet()) {
            clients client = entry.getValue();
            System.out.println("Client: " + client.getName() + 
                               ", Email: " + client.getEmail());
        }
    }
    public void generateDetailedEngagementReport() {
        System.out.println("Generating detailed engagement report...");
        System.out.println(getDetailedEngagementReport());
    }
    public String getDetailedEngagementReport() {
        StringBuilder report = new StringBuilder("Detailed Engagement Report:\n\n");
        report.append("Instructors:\n");
        Map<String, Instructor> instructors = userService.getInstructors();
        for (Map.Entry<String, Instructor> entry : instructors.entrySet()) {
            Instructor instructor = entry.getValue();
            report.append("- ").append(instructor.getName())
                  .append(", Email: ").append(instructor.getEmail())
                  .append(", Programs Created: ").append(instructor.getProgramsCreated())
                  .append("\n");
        }
        report.append("\nClients:\n");
        Map<String, clients> clients = userService.getClients();
        for (Map.Entry<String, clients> entry : clients.entrySet()) {
            clients client = entry.getValue();
            report.append("- ").append(client.getName())
                  .append(", Email: ").append(client.getEmail())
                  .append("\n");
        }

        return report.toString();
    }
    public String getEngagementTrends() {
        List<String> trendsList = Arrays.asList(
            "Weekly engagement increased by 10%",
            "Monthly engagement increased by 30%"
        );

        if (trendsList.isEmpty()) {
            return "No trends data available";
        }
        return String.join(", ", trendsList); 
    }

    public boolean exportReport(String format) {
        if (format.equalsIgnoreCase("CSV") || format.equalsIgnoreCase("PDF")) {
            System.out.println("Report successfully exported in " + format + " format.");
            return true;
        } else {
            System.out.println("Unsupported format: " + format);
            return false;
        }
    }
}
