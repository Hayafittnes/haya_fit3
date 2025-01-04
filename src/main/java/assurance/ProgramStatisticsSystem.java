package assurance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class ProgramStatisticsSystem {
    private List<Program> allPrograms; 
    private List<Program> filteredPrograms;
    private boolean isPaginated; 
    private int paginationThreshold; 

    public ProgramStatisticsSystem() {
        this.allPrograms = new ArrayList<>();
        this.filteredPrograms = new ArrayList<>();
        this.isPaginated = false;
        this.paginationThreshold = 10; 
        initializeSamplePrograms();
    }

    private void initializeSamplePrograms() {
        allPrograms.add(new Program("Yoga Basics", 150, "Active", "2023-01-01", "2023-12-31"));
        allPrograms.add(new Program("Advanced Meditation", 90, "Completed", "2022-01-01", "2022-12-31"));
        allPrograms.add(new Program("Healthy Cooking", 60, "Active", "2023-06-01", "2023-11-30"));
        allPrograms.add(new Program("Fitness Bootcamp", 30, "Active", "2023-02-01", "2023-03-01"));
    }
    public void navigateToStatisticsSection() {
        System.out.println("Admin navigated to the program statistics section.");
    }
    public void selectEnrollmentRange(String enrollmentRange) {
        String[] range = enrollmentRange.split("-");
        int min = Integer.parseInt(range[0]);
        int max = Integer.parseInt(range[1]);
        filteredPrograms = allPrograms.stream()
                .filter(program -> program.getNumberOfEnrollments() >= min && program.getNumberOfEnrollments() <= max)
                .collect(Collectors.toList());
    }
    public void filterByStatus(String programStatus) {
        filteredPrograms = filteredPrograms.stream()
                .filter(program -> {
                    Object statusObj = program.getStatus();
                    if (statusObj != null) {
                        String status = statusObj.toString();
                        return status.equalsIgnoreCase(programStatus);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public void selectDateRange(String dateRange) {
        System.out.println("Date range filter applied: " + dateRange);
    }

    public void sortProgramsByEnrollments(String order) {
        if (order.equalsIgnoreCase("ascending")) {
            filteredPrograms.sort(Comparator.comparing(Program::getNumberOfEnrollments));
        } else if (order.equalsIgnoreCase("descending")) {
            filteredPrograms.sort(Comparator.comparing(Program::getNumberOfEnrollments).reversed());
        }
    }

    public void setPaginationThreshold(int threshold) {
        this.paginationThreshold = threshold;
    }

    public List<Program> getPaginatedPrograms() {
        isPaginated = filteredPrograms.size() > paginationThreshold;
        if (isPaginated) {
            return filteredPrograms.subList(0, paginationThreshold);
        } else {
            return filteredPrograms;
        }
    }

    public List<Program> getFilteredPrograms() {
        return filteredPrograms;
    }

    public boolean isPaginated() {
        return isPaginated;
    }

    public boolean isStatisticsSectionActive() {
        return true; 
    }

    public List<String> getAvailableFilters() {
        List<String> filters = new ArrayList<>();
        filters.add("Enrollment Range");
        filters.add("Program Status");
        filters.add("Date Range");
        return filters;
    }

    public void setPrograms(List<Program> programs) {
        this.allPrograms = programs;
    }
}

