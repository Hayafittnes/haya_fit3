package assurance;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private List<RegistrationRequest> pendingRegistrations = new ArrayList<>();

    public void viewPendingRegistrations() {
        for (RegistrationRequest request : pendingRegistrations) {
            Instructor instructor = (Instructor) request.getInstructor(); 
            System.out.println("Instructor: " + instructor.getQualifications());
        }
    }

    public List<RegistrationRequest> getPendingRegistrations() {
        return pendingRegistrations;
    }

    public void addPendingRegistration(RegistrationRequest registrationRequest) {
        pendingRegistrations.add(registrationRequest);
    }

    public void approveRegistration(RegistrationRequest registrationRequest) {
        Instructor instructor = (Instructor) registrationRequest.getInstructor();
        instructor.approve(); 
        System.out.println("Instructor approved: " + instructor.getQualifications());
    }

    public void rejectRegistration(RegistrationRequest registrationRequest) {
        System.out.println("Instructor registration rejected.");
    }

    public void viewRegistrationDetails(RegistrationRequest registrationRequest) {
        Instructor instructor = (Instructor) registrationRequest.getInstructor();
        System.out.println("Qualifications: " + instructor.getQualifications());
        System.out.println("Experience: " + instructor.getExperience());
        System.out.println("Contact Info: " + instructor.getContactInfo());
    }

    public boolean hasApprovalOrRejectionOption(RegistrationRequest registrationRequest) {
        return !pendingRegistrations.isEmpty();
    }
}
