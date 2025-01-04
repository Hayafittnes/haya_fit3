package assurance;

public class RegistrationRequest {
    private Instructor instructor;
    private boolean approved;
    private boolean rejected;
    public RegistrationRequest(Instructor currentInstructor) {
        if (currentInstructor == null) {
            throw new IllegalArgumentException("The instructor for the registration request cannot be null.");
        }
        this.instructor = currentInstructor;
        this.approved = false;
        this.rejected = false;
    }
    public Instructor getInstructor() {
        return instructor;
    }
    public boolean isApproved() {
        return approved;
    }
    public void setApproved(boolean approved) {
    	   if (approved) {
    	        this.rejected = false;
    	    }
    	    this.approved = approved;
    }

    public boolean isRejected() {
        return rejected;
    }
    public void setRejected(boolean rejected) {
        if (rejected) {
            this.approved = false;
        }
        this.rejected = rejected; 
    }
}

