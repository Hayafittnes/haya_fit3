package fitness;

import java.util.Objects;

//this is for Account Management feature
public class user {
    private int id;
    private String name;
    private int age;
    private String fitnessGoals;
    private String dietaryPreferences;
// constr
    public user(int id, String name, int age, String fitnessGoals) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.fitnessGoals = fitnessGoals;
        this.dietaryPreferences = "None"; // Default dietary preference
    }

    // Getters and setters for the User properties
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFitnessGoals() {
        return fitnessGoals;
    }

    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public void setFitnessGoals(String fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
    }

    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", age=" + age +
               ", fitnessGoals='" + fitnessGoals + '\'' +
               ", dietaryPreferences='" + dietaryPreferences + '\'' +
               '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(age, fitnessGoals, dietaryPreferences);
    }
}