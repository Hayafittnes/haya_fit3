package fitness;

import java.util.ArrayList;
import java.util.List;

public class UserAchiv {
    private String name;
    private List<String> completedPrograms;

    public UserAchiv(String name) {
        this.name = name;
        this.completedPrograms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getCompletedPrograms() {
        return completedPrograms;
    }

    public void completeProgram(String program) {
        completedPrograms.add(program);
    }

    public void enrollInProgram(String program) {
        // Logic for enrolling in a program (without completing it)
    }
}