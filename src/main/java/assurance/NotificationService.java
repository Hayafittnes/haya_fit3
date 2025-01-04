package assurance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class NotificationService {
	private List<String> notifiedContentTitles = new ArrayList<>();
	 private List<String> notifications = new ArrayList<>();
	private Set<String> sentWelcomeEmails;
    private Set<String> welcomeMessagesSent = new HashSet<>();
    private Set<String> updateNotificationsSent = new HashSet<>();
    private Set<String> deactivationNotificationsSent = new HashSet<>();
    private Set<String> contentNotificationsSent = new HashSet<>();
    private Set<String> sentWelcomeMessages = new HashSet<>();
    private List<String> sentEmails;
    public NotificationService() {
        this.sentEmails = new ArrayList<>();
        sentWelcomeEmails = new HashSet<>();
    }

    public void sendWelcomeMessage(String email) {
    	if (sentWelcomeEmails == null) {
            sentWelcomeEmails = new HashSet<>();
        }
        sentWelcomeEmails.add(email);
        System.out.println("Welcome message sent to: " + email);
    }

    public boolean isWelcomeMessageSent(String email) {
        return sentWelcomeEmails.contains(email); 
    }

    public void sendUpdateNotification(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        updateNotificationsSent.add(email);
        System.out.println("Update notification sent to: " + email);
    }

    public boolean isUpdateNotificationSent(String email) {
        return updateNotificationsSent.contains(email);
    }

    public void sendDeactivationNotification(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        deactivationNotificationsSent.add(email);
        System.out.println("Deactivation notification sent to: " + email);
    }

    public boolean isDeactivationNotificationSent(String email) {
        return deactivationNotificationsSent.contains(email);
    }

    public boolean isRejectionNotificationSent(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        return deactivationNotificationsSent.contains(email);
    }
    public void sendContentStatusNotification(String instructorEmail, String status, String title) {
        if (instructorEmail == null || instructorEmail.isEmpty()) {
            throw new IllegalArgumentException("Instructor email cannot be null or empty.");
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Content title cannot be null or empty.");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty.");
        }
        contentNotificationsSent.add(title);
        String message = "Dear Instructor,\n\nThe content titled \"" + title + "\" has been " + status + ".\nThank you!";
        System.out.println("Notification sent to: " + instructorEmail);
        System.out.println("Message: " + message);
    }
	
	public List<String> getSentEmails() {
	    return sentEmails;
	}
	   public void notifyAdmin(String title, String message) {
	        String notificationMessage = "Title: " + title + " | Message: " + message;
        notifications.add(notificationMessage);
       System.out.println("Admin has been notified: " + notificationMessage);
	    }
	   //
	   public void notifyInstructor(String title, String message) {
		   notifiedContentTitles.add(title);
		    if (title == null || title.isEmpty()) {
		        throw new IllegalArgumentException("Title cannot be null or empty.");
		    }
		    if (message == null || message.isEmpty()) {
		        throw new IllegalArgumentException("Message cannot be null or empty.");
		    }
		    System.out.println("Notification sent to instructor:");
		    System.out.println("Content Title: " + title);
		    System.out.println("Message: " + message);
		}

	   public boolean isContentStatusChangeNotified(String title) {
		    return notifiedContentTitles.contains(title);
		}
	   public void notifyInstructor1(String title, String rejectionReason) {
	        System.out.println("Sending notification to instructor...");
	        System.out.println("Title: " + title);
	        System.out.println("Reason: " + rejectionReason);
	    }
}
