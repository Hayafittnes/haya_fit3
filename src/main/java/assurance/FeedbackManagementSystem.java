package assurance;

public class FeedbackManagementSystem {

    private String feedbackStatus;
    private String rejectionReason;
    private boolean userNotified = false;
    private boolean adminNotificationVisible = false;
	public void navigateToFeedbackSection() {
		  System.out.println("Admin has navigated to the feedback and complaints management section.");
	}
	public void viewFeedbackOrComplaint(String userName) {
	    System.out.println("Admin is viewing the feedback or complaint submitted by " + userName);
	    
	}

	public void resolveFeedback() {
		 feedbackStatus = "Resolved";
	        System.out.println("Admin has resolved the feedback.");
	}
	public void rejectFeedback() {
	    feedbackStatus = "Rejected";
        System.out.println("Admin has rejected the feedback.");
   
	}
	public void enterRejectionReason(String rejectionReason) {
		 this.rejectionReason = rejectionReason;
	        System.out.println("Rejection reason: " + rejectionReason);
	}

	public String getFeedbackStatus() {
		 return feedbackStatus;
	}
	public boolean notifyUser(String userName) {
		System.out.println("User " + userName + " has been notified about the resolution.");
        userNotified = true;
        return userNotified;
	}
	public boolean isAdminNotificationVisible() {
		System.out.println("Admin can see the notification confirming the feedback status change.");
        adminNotificationVisible = true;
        return adminNotificationVisible;
	}

}
