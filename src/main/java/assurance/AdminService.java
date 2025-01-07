package assurance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminService {
	private boolean adminNotified = false;
	private boolean inContentManagement = false;
	 private boolean isAdminLoggedIn=true;
    private Instructor currentInstructor;
    private boolean loggedIn = false;
    private Content currentContent;
    private NotificationService notificationService;
    private List<RegistrationRequest> pendingRegistrations = new ArrayList<>();
    private List<Content> submittedContents = new ArrayList<>();
    private Map<String, String> contentStatusMap = new HashMap<>();
    private Map<String, Instructor> instructorMap = new HashMap<>();
    private Map<String, clients> clientMap = new HashMap<>();
    private boolean inContentManagementSection = false; 
    private List<Content> submittedContent = new ArrayList<>();
    private Map<String, Content> contentDatabase = new HashMap<>();

    public AdminService() {
        this.notificationService = new NotificationService();
        contentDatabase.put("Benefits of Yoga", new Content("Benefits of Yoga"));
        contentDatabase.put("10 Quick Healthy Snacks", new Content("10 Quick Healthy Snacks"));
        contentDatabase.put("How to Meditate Properly", new Content("How to Meditate Properly"));
        contentDatabase.put("Top 5 Fat Burning Foods", new Content("Top 5 Fat Burning Foods"));
    }

    public boolean login(String username, String password) {
        if ("admin".equals(username) && "password123".equals(password)) {
            loggedIn = true;
            System.out.println("Admin logged in successfully.");
            return true;
        } 
        return false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public List<RegistrationRequest> getPendingRegistrations() {
        return new ArrayList<>(pendingRegistrations);
    }

    public void addPendingRegistration(RegistrationRequest currentRequest) {
        validateAdminLogin();
        if (!pendingRegistrations.contains(currentRequest)) {
            pendingRegistrations.add(currentRequest);
            Instructor instructor = currentRequest.getInstructor();
            System.out.println("Added a new pending registration for: " + 
                (instructor != null ? instructor.getName() : "Unknown Instructor"));
        } else {
            System.out.println("This registration request already exists.");
        }
    }

    public void approveRegistration(RegistrationRequest currentRequest) {
        validateAdminLogin();

        if (pendingRegistrations.contains(currentRequest)) {
            currentRequest.setApproved(true);
            pendingRegistrations.remove(currentRequest);

            Instructor instructor = currentRequest.getInstructor();
            if (instructor != null) {
                notificationService.sendWelcomeMessage(instructor.getEmail());
                System.out.println("Welcome message sent to: " + instructor.getEmail());
                instructorMap.put(instructor.getEmail(), instructor);
            } else {
                throw new IllegalArgumentException("Instructor details are missing.");
            }
        } else {
            throw new IllegalArgumentException("Registration request not found.");
        }
    }


    public void rejectRegistration(RegistrationRequest currentRequest) {
        validateAdminLogin();
        if (pendingRegistrations.remove(currentRequest)) {
            currentRequest.setRejected(true);
            notifyInstructor(currentRequest, "Registration rejected");
        } else {
            throw new IllegalArgumentException("Registration request not found.");
        }
    }
    public String viewRegistrationDetails(RegistrationRequest request) {
        Instructor instructor = request.getInstructor();
        if (instructor != null) {
            return "Qualifications: " + instructor.getQualifications() + 
                   ", Experience: " + instructor.getExperience() + 
                   ", Email: " + instructor.getEmail();
        }
        return "No instructor details available"; 
    }

    private void notifyInstructor(RegistrationRequest request, String message) {
        Instructor instructor = request.getInstructor();
        if (instructor != null) {
            notificationService.sendWelcomeMessage(instructor.getEmail());
            System.out.println(message + " and welcome message sent to: " + instructor.getEmail());
        } else {
            System.out.println(message + " for an unknown instructor.");
        }
    }
    public void setInContentManagementSection(boolean inContentManagementSection) {
        System.out.println("Setting inContentManagementSection to: " + inContentManagementSection);
        this.inContentManagementSection = inContentManagementSection;
    }
    
    public void navigateToContentManagement() {
    	if (!loggedIn) {
            throw new IllegalStateException("Admin must be logged in to navigate.");
        }
        inContentManagement = true;
    }
    public void viewSubmittedContent() {   
        if (submittedContent.isEmpty()) {
            System.out.println("No submitted content available.");
        } else {
            System.out.println("Submitted content viewed.");
        }
    }
    public List<Content> getSubmittedContent1() {
        validateAdminLogin();
        return submittedContent;
    }

    public void approveContent(Content content) {
        if (!loggedIn) {
            throw new IllegalStateException("Admin must be logged in to approve content.");
        }
        content.setStatus("Approved");
        notificationService.notifyInstructor(content.getTitle(), "Your content has been approved.");
        adminNotified = true;
    }
    public void rejectContent(Content content) {
        if (!loggedIn) {
            throw new IllegalStateException("Admin must be logged in to reject content.");
        }
        if (content.getRejectionReason() == null || content.getRejectionReason().isEmpty()) {
            throw new IllegalArgumentException("Rejection reason cannot be null or empty.");
        }
        content.setStatus("Rejected");
        notificationService.notifyInstructor(content.getTitle(), 
            "Your content has been rejected for the following reason: " + content.getRejectionReason());
    }

    private void handleContent(Content content, String status) {
        validateAdminLogin();
        if (submittedContents.contains(content)) {
            contentStatusMap.put(content.getTitle(), status);
            notificationService.sendContentStatusNotification(content.getInstructorEmail(), status, content.getTitle());
            System.out.println("Content " + status.toLowerCase() + ": " + content.getTitle());
        } else {
            throw new IllegalArgumentException("Content not found.");
        }
    }

    public boolean isAdminNotifiedAboutContentStatusChange(String title) {
        return adminNotified && contentDatabase.containsKey(title);
    }
    public boolean navigateToFeedbackSection() {
        validateAdminLogin();  
        System.out.println("Navigated to feedback section.");
        return true; 

    }
    public boolean isFeedbackStatusNotificationVisible(Feedback currentFeedback) {
        if (currentFeedback == null || currentFeedback.getId() == null || currentFeedback.getId().isEmpty()) {
            throw new IllegalStateException("Feedback ID is not set or invalid.");
        }
        System.out.println("Feedback status notification visibility checked for: " + currentFeedback.getId());
        return true;
    }

    private void validateAdminLogin() {
        if (!loggedIn) {
            throw new IllegalStateException("Admin must be logged in to perform this action.");
        }
    }

    public boolean hasApprovalOrRejectionOption(RegistrationRequest currentRequest) {
        if (!isLoggedIn()) {
            throw new IllegalStateException("Admin must be logged in to check approval or rejection options.");
        }
        if (currentRequest == null) {
            throw new IllegalArgumentException("Registration request cannot be null.");
        }
        return pendingRegistrations.contains(currentRequest);
    }

    public void updateInstructor(Instructor currentInstructor) {
        validateAdminLogin();
        if (currentInstructor == null || currentInstructor.getEmail() == null) {
            throw new IllegalArgumentException("Invalid instructor details for update.");
        }
        String email = currentInstructor.getEmail();
        if (!instructorMap.containsKey(email)) {
            throw new IllegalArgumentException("Instructor not found.");
        }
        instructorMap.put(email, currentInstructor);
        System.out.println("Instructor account updated: " + currentInstructor.getName());
    }

    public void registerInstructor(Instructor currentInstructor) {
        validateAdminLogin();
        if (currentInstructor == null || currentInstructor.getEmail() == null) {
            throw new IllegalArgumentException("Invalid instructor details for registration.");
        }
        String email = currentInstructor.getEmail();
        if (instructorMap.containsKey(email)) {
            throw new IllegalArgumentException("Instructor already registered.");
        }
        instructorMap.put(email, currentInstructor);
        System.out.println("Instructor registered: " + currentInstructor.getName());
    }

    public void deactivateInstructor(Instructor currentInstructor) {
        validateAdminLogin();
        if (currentInstructor == null || currentInstructor.getEmail() == null) {
            throw new IllegalArgumentException("Invalid instructor details for deactivation.");
        }
        String email = currentInstructor.getEmail();
        if (!instructorMap.containsKey(email)) {
            throw new IllegalArgumentException("Instructor not found.");
        }
        Instructor instructor = instructorMap.get(email);
        instructor.setActive(false);
        System.out.println("Instructor account deactivated: " + instructor.getName());
    }

    public boolean isInstructorRegistered(String email) {
        validateAdminLogin();
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        return instructorMap.containsKey(email);
    }

    public boolean isInstructorActive(String email) {
        validateAdminLogin();
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        Instructor instructor = instructorMap.get(email);
        return instructor != null && instructor.isActive();
    }

    public boolean isClientRegistered(String clientEmail) {
        if (clientEmail == null || clientEmail.isEmpty()) {
            throw new IllegalArgumentException("Client email cannot be null or empty.");
        }
        return clientMap.containsKey(clientEmail); // Corrected check
    }

    public void addClient(String clientEmail) {
        validateAdminLogin();
        if (clientEmail == null || clientEmail.isEmpty()) {
            throw new IllegalArgumentException("Client email cannot be null or empty.");
        }
        if (clientMap.containsKey(clientEmail)) {
            throw new IllegalArgumentException("Client already registered.");
        }
        clientMap.put(clientEmail, new clients(0, clientEmail, clientEmail, clientEmail, 0, clientEmail, clientEmail));
        System.out.println("Client account added: " + clientEmail);
    }

    public void setCurrentInstructor(Instructor instructor) {
        this.currentInstructor = instructor;
    }

    public boolean isInContentManagement() {
    	 return inContentManagement;
    }

    public List<Content> getSubmittedContent() {
        if (!inContentManagementSection) {
            throw new IllegalStateException("Admin must be in the content management section to view content.");
        }
        return submittedContent;
    }
    public Instructor getInstructorByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (instructorMap.containsKey(email)) {
            return instructorMap.get(email);
        } else {
            System.out.println("Instructor with email " + email + " not found.");
            return null;
        }
    }
	public void setAdminLoginStatus(boolean isLoggedIn) {
        loggedIn = isLoggedIn;
    }
	  public Content getContent(String title) {
	        return contentDatabase.get(title);
	    }

	    public void addContent(Content content) {
	        contentDatabase.put(content.getTitle(), content);
	    }

}
