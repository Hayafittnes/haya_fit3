package assurance;

class Content {
    private String title;
    private String status;
    private String rejectionReason;
    private String instructorEmail;
    private String instructorName;
    public Content(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Content title cannot be null or empty.");
        }
        this.title = title;
        this.status = "Pending"; 
    }
    public String getTitle() {
        return title;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty.");
        }
        this.status = status;
    }
    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
    public String getInstructorEmail() {
        return instructorEmail;
    }
    public void setInstructorEmail(String instructorEmail) {
        if (instructorEmail == null || instructorEmail.isEmpty()) {
            throw new IllegalArgumentException("Instructor email cannot be null or empty.");
        }
        this.instructorEmail = instructorEmail;
    }

    public String getInstructorName() {
        return (instructorName != null && !instructorName.isEmpty()) ? instructorName : "Unknown Instructor";
    }

    public void setInstructorName(String instructorName) {
        if (instructorName == null || instructorName.isEmpty()) {
            throw new IllegalArgumentException("Instructor name cannot be null or empty.");
        }
        this.instructorName = instructorName;
    }
}
