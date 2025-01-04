package assurance;

public class Program {

    private String name;
    private int numberOfEnrollments;
    private String status;
    private String startDate;
    private String endDate;
    public Program(String name, int numberOfEnrollments, String status, String startDate, String endDate) {
        this.name = name;
        this.numberOfEnrollments = numberOfEnrollments;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getNumberOfEnrollments() {
        return numberOfEnrollments;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    private String duration;
    private String instructor;

    public Program(String name, String duration, String instructor) {
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
    }

    public String getDuration() {
        return duration;
    }

    public String getInstructor() {
        return instructor;
    }
}
