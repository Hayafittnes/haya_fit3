package assurance;

public class clients {
    private int id;
    private String name;
    private String email;
    private String password;
    private int age;
    private String dietaryPreferences;
    private String fitnessGoals;
    private int loginCount;
    private int completedWorkouts;
    private int programsEnrolled;

    public clients(int id, String name, String email, String password, int age, String dietaryPreferences, String fitnessGoals) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.dietaryPreferences = dietaryPreferences;
        this.fitnessGoals = fitnessGoals;
        this.loginCount = 0;
        this.completedWorkouts = 0;
        this.programsEnrolled = 0;
    }

    public int getId() {
        return id;
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

    public int getAge() {
        return age;
    }

    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public String getFitnessGoals() {
        return fitnessGoals;
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
    public void setId(int id) {
        this.id = id;
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    public void setFitnessGoals(String fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
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
        return "Client [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + 
               ", dietaryPreferences=" + dietaryPreferences + ", fitnessGoals=" + fitnessGoals + 
               ", loginCount=" + loginCount + ", completedWorkouts=" + completedWorkouts + 
               ", programsEnrolled=" + programsEnrolled + "]";
    }
}
