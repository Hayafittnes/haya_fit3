package assurance;

public class Feedback {
    private String userName;
    private String status;
    private String rejectionReason;
    private String content;
    public Feedback(String userName, String status) {
        this.userName = userName;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    private String id;

    public String getId() {
        if (id == null || id.isEmpty()) {
            return "defaultId"; 
        }
        return id;
    }

    public void setContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        }
        System.out.println("Setting content to: " + content);  
        this.content = content;
    }

	public void setId(String id) {
		 this.id = id; 
	}

}
