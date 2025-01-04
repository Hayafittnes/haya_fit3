package assurance;

import java.util.ArrayList;
import java.util.List;
public class ContentManagementSystem {
	 private boolean instructorNotified = false;
	 private boolean adminNotificationReceived = false;
	 private String rejectionReason; 
	 private List<Content> submittedContentList;
	 public ContentManagementSystem() {
	        this.submittedContentList = new ArrayList<>();
	        // Adding some sample content to simulate instructor submissions
	        submittedContentList.add(new Content("5 Healthy Smoothie Recipes", "Pending"));
	        submittedContentList.add(new Content("Best Workouts for Beginners", "Pending"));
	        submittedContentList.add(new Content("Meditation Tips for Stress Relief", "Pending"));
	        submittedContentList.add(new Content("Top 10 Fat Burning Foods", "Pending"));
	    }
	 public void setRejectionReason(String reason) {
		    this.rejectionReason = reason;
		}
	public void updateContentStatus(String contentTitle, String status) {
		  for (Content content : submittedContentList) {
	            if (content.getTitle().equals(contentTitle)) {
	                content.setStatus(status);
	                break;
	            }
	        }
	}

	public void notifyInstructor(String contentTitle) {
		 Content content = getContentByTitle(contentTitle);
		    if (content != null) {
		        if ("Rejected".equals(content.getStatus()) && rejectionReason != null) {
		            System.out.println("Instructor has been notified about the rejection of content: " + contentTitle + ". Reason: " + rejectionReason);
		        } else {
		            System.out.println("Instructor has been notified about the approval of content: " + contentTitle);
		        }
		        instructorNotified = true;
		    }
		    }
	private Content getContentByTitle(String title) {
		 for (Content content : submittedContentList) {
		        if (content.getTitle().equals(title)) {
		            return content;
		        }
		    }
		    return null;
	}
	public void notifyAdmin(String contentTitle) {
		 adminNotificationReceived = true;  
		    System.out.println("Admin has been notified about the status of content: " + contentTitle);
		}
    public String[] getSubmittedContent() {   
        String[] contentTitles = new String[submittedContentList.size()];
        for (int i = 0; i < submittedContentList.size(); i++) {
            contentTitles[i] = submittedContentList.get(i).getTitle();  
        }
        return contentTitles;
    }
	public void goToContentManagementSection() {
		System.out.println("Admin navigated to the content management section.");
	}
	public void viewSubmittedContent(String instructorName) {	
		System.out.println("Admin is viewing the content submitted by instructor: " + instructorName);
	}
	public void approveContent(String contentTitle) {
		  updateContentStatus(contentTitle, "Approved");
		    notifyInstructor(contentTitle); 
		    notifyAdmin(contentTitle);
	}
	public void rejectContent(String contentTitle) {
		updateContentStatus(contentTitle, "Rejected");
	    notifyInstructor(contentTitle);  
	    notifyAdmin(contentTitle); 
	}
	public String getContentStatus(String contentTitle) {
		  Content content = getContentByTitle(contentTitle);
		    if (content != null) {
		        return content.getStatus();
		    }
		    return "Content not found";
	}
	public String getContentStatus() {
        if (!submittedContentList.isEmpty()) {
            return submittedContentList.get(0).getStatus(); 
        }
        return "No content available"; 
    }
	public boolean isNotificationVisible() {
		return adminNotificationReceived;
	}
}


