package assurance;

public class clients {
    private String name;
    private String email;
    private String password;
    private int loginCount;
    private int completedWorkouts;
    private int programsEnrolled; 
    public clients(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.loginCount = 0; 
        this.completedWorkouts = 0; 
        this.programsEnrolled = 0; 
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public int getCompletedWorkouts() {
        return completedWorkouts;
    }

    public int getProgramsEnrolled() {
        return programsEnrolled;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public void setCompletedWorkouts(int completedWorkouts) {
        this.completedWorkouts = completedWorkouts;
    }

    public void setProgramsEnrolled(int programsEnrolled) {
        this.programsEnrolled = programsEnrolled;
    }

    // Increment methods
    public void incrementLoginCount() {
        this.loginCount++;
    }

    public void incrementCompletedWorkouts() {
        this.completedWorkouts++;
    }

    public void incrementProgramsEnrolled() {
        this.programsEnrolled++;
    }

    @Override
    public String toString() {
        return "Client [name=" + name + ", email=" + email + ", loginCount=" + loginCount + 
               ", completedWorkouts=" + completedWorkouts + ", programsEnrolled=" + programsEnrolled + "]";
    }
}

