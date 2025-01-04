package assurance;
public class EmailService {
    public boolean sendWelcomeEmail(String email) {  
        if (email != null && !email.isEmpty()) {
            System.out.println("Welcome email sent to: " + email);
            return true;  
        }
        return false;
    }
}
