package assurance;

import java.util.List;

public class Instructor {

    private String name;
    private String email;
    private boolean active;
    private String qualifications;
    private int experience; 
    private boolean approvalEmailSent = false;
    private boolean rejectionNotificationSent = false;
    private int programsCreated = 0; 
    private int loginCount = 0; 
    private int clientsAssigned = 0; 
    public Instructor(String name, String email, String qualifications, int experience) {
        if (name == null || email == null || qualifications == null || experience <= 0) {
            throw new IllegalArgumentException("Instructor details cannot be null or invalid.");
        }
        this.name = name;
        this.email = email;
        this.qualifications = qualifications;
        this.experience = experience;
        this.active = true;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getQualifications() {
        if (this.qualifications == null || this.qualifications.isEmpty()) {
            return "No qualifications provided.";
        }
        return this.qualifications;
    }

    public int getExperience() {
        return this.experience;
    }

    public String getContactInfo() {
        return "Name: " + name + ", Email: " + email;
    }

    public boolean hasReceivedApprovalEmail() {
        return approvalEmailSent;
    }

    public boolean hasReceivedRejectionNotification() {
        return rejectionNotificationSent;
    }

    public int getProgramsCreated() {
        return programsCreated;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public int getClientsAssigned() {
        return clientsAssigned;
    }
    public void setEmail(String newEmail) {
        if (newEmail == null || newEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (!newEmail.contains("@") || !newEmail.contains(".")) {
            throw new IllegalArgumentException("Invalid email format: " + newEmail);
        }
        this.email = newEmail;
        System.out.println("Email updated to: " + newEmail);
    }

    public void setProgramsCreated(int programsCreated) {
        if (programsCreated < 0) {
            throw new IllegalArgumentException("Programs created cannot be negative.");
        }
        this.programsCreated = programsCreated;
    }

    public void sendApprovalEmail() {
        approvalEmailSent = true;
        System.out.println("Approval email sent to: " + email);
    }

    public void sendRejectionNotification() {
        rejectionNotificationSent = true;
        System.out.println("Rejection notification sent to: " + email);
    }

    public void incrementProgramsCreated() {
        this.programsCreated++;
    }

    public void incrementLoginCount() {
        this.loginCount++;
    }

    public void incrementClientsAssigned() {
        this.clientsAssigned++;
    }

    public void setName(String newName) {
        if (newName == null || newName.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = newName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", qualifications='" + qualifications + '\'' +
                ", experience=" + experience +
                ", programsCreated=" + programsCreated +
                ", loginCount=" + loginCount +
                ", clientsAssigned=" + clientsAssigned +
                '}';
    }

    public void setQualifications(String qualifications) {
        if (qualifications == null || qualifications.trim().isEmpty()) {
            throw new IllegalArgumentException("Qualifications cannot be null or empty.");
        }
        this.qualifications = qualifications;
    }

}
