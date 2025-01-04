package assurance;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, Instructor> instructors = new HashMap<>();
    private Map<String, clients> clients = new HashMap<>();  

    public void addInstructor(String name, String email, String password) {
        if (instructors.containsKey(email)) {
            throw new IllegalArgumentException("Instructor with email " + email + " already exists.");
        }
        if (name == null || email == null || password == null) {
            throw new IllegalArgumentException("Instructor details cannot be null.");
        }
        Instructor instructor = new Instructor(name, email, "Qualifications not provided", 0);
        instructors.put(email, instructor);
        System.out.println("Instructor added: " + name + " (" + email + ")");
    }

    public boolean isInstructorCreated(String email) {
        return instructors.containsKey(email);
    }

    public void updateInstructorDetails(String email, String newEmail, String newPassword) {
        if (!instructors.containsKey(email)) {
            throw new IllegalArgumentException("Instructor with email " + email + " does not exist.");
        }
        if (newEmail == null || newEmail.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("New details cannot be null or empty.");
        }
        Instructor instructor = instructors.remove(email);
        instructor.setEmail(newEmail);
        instructors.put(newEmail, instructor);
        System.out.println("Instructor updated: " + newEmail);
    }

    public boolean isInstructorUpdated(String email) {
        return instructors.containsKey(email);
    }

    public void deactivateInstructor(String email) {
        if (!instructors.containsKey(email)) {
            throw new IllegalArgumentException("Instructor with email " + email + " does not exist.");
        }
        instructors.remove(email);
        System.out.println("Instructor deactivated: " + email);
    }

    public boolean isInstructorDeactivated(String email) {
        return !instructors.containsKey(email);
    }

    public void addClient(String name, String email, String password) {
        if (clients.containsKey(email)) {
            throw new IllegalArgumentException("Client with email " + email + " already exists.");
        }
        if (name == null || email == null || password == null) {
            throw new IllegalArgumentException("Client details cannot be null.");
        }
        clients client = new clients(name, email, password);  // Corrected class name to Client
        clients.put(email, client);
        System.out.println("Client added: " + name + " (" + email + ")");
    }

    public boolean isClientCreated(String email) {
        return clients.containsKey(email);
    }

    public int getActiveInstructorsCount() {
        return instructors.size();
    }

    public int getActiveClientsCount() {
        return clients.size();
    }

    public int getClientLoginFrequency() {
        return clients.values().stream()
                .mapToInt(client -> client.getLoginCount())
                .sum() / (clients.size() > 0 ? clients.size() : 1);
    }

    public int getCompletedWorkoutsForClients() {
        return clients.values().stream()
                .mapToInt(client -> client.getCompletedWorkouts())
                .sum();
    }

    public int getTotalClientLogins() {
        return clients.values().stream()
                .mapToInt(client -> client.getLoginCount())
                .sum();
    }

    public int getTotalInstructorLogins() {
        return instructors.values().stream()
                .mapToInt(instructor -> instructor.getLoginCount())
                .sum();
    }

    public int getProgramsCreatedByInstructors() {
        return instructors.values().stream()
                .mapToInt(instructor -> instructor.getProgramsCreated())
                .sum();
    }

    public Map<String, clients> getClients() {  // Corrected the return type to Client
        return new HashMap<>(clients);
    }

    public int getProgramsEnrolledByClients() {
        return clients.values().stream()
                .mapToInt(client -> client.getProgramsEnrolled())
                .sum();
    }

    public int getClientsAssignedToInstructors() {
        return instructors.values().stream()
                .mapToInt(instructor -> instructor.getClientsAssigned())
                .sum();
    }

    public Map<String, Instructor> getInstructors() {
        return new HashMap<>(instructors);
    }

    public int getInstructorLoginFrequency() {
        int totalLogins = instructors.values().stream()
                .mapToInt(instructor -> instructor.getLoginCount())
                .sum();

        int activeInstructorsCount = instructors.size();
        if (activeInstructorsCount == 0) {
            return 0;
        }
        return totalLogins / activeInstructorsCount;
    }
}

