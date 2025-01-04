package assurance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Program1test {
    private ProgramStatisticsSystem statisticsSystem;
    private List<Program> samplePrograms;

    @Before
    public void setUp() {
        statisticsSystem = new ProgramStatisticsSystem();
        samplePrograms = new ArrayList<>();
        samplePrograms.add(new Program("Program A", 150, "Active", "2023-01-01", "2023-06-30"));
        samplePrograms.add(new Program("Program B", 100, "Completed", "2022-01-01", "2022-12-31"));
        samplePrograms.add(new Program("Program C", 50, "Active", "2023-07-01", "2023-12-31"));
        statisticsSystem.setPrograms(samplePrograms);
    }

    @Test
    public void testNavigateToStatisticsSection() {
        statisticsSystem.navigateToStatisticsSection();
        Assert.assertTrue("Admin should navigate to statistics section", statisticsSystem.isStatisticsSectionActive());
    }

    @Test
    public void testSelectEnrollmentRange() {
        statisticsSystem.selectEnrollmentRange("50-150");
        List<Program> filteredPrograms = statisticsSystem.getFilteredPrograms();
        Assert.assertNotNull("Filtered programs should not be null", filteredPrograms);
        Assert.assertTrue("Programs should be filtered by enrollment range",
                filteredPrograms.stream().allMatch(p -> p.getNumberOfEnrollments() >= 50 && p.getNumberOfEnrollments() <= 150));
    }

    @Test
    public void testFilterByStatus() {
        statisticsSystem.filterByStatus("Active");
        List<Program> filteredPrograms = statisticsSystem.getFilteredPrograms();
        Assert.assertNotNull("Filtered programs should not be null", filteredPrograms);
        Assert.assertTrue("Programs should be filtered by status",
                filteredPrograms.stream().allMatch(p -> p.getStatus().equalsIgnoreCase("Active")));
    }

    @Test
    public void testSelectDateRange() {
        statisticsSystem.selectDateRange("2023");
        List<Program> filteredPrograms = statisticsSystem.getFilteredPrograms();
        Assert.assertNotNull("Filtered programs should not be null", filteredPrograms);
        Assert.assertTrue("Programs should be filtered by date range",
                filteredPrograms.stream().allMatch(p -> p.getStartDate().startsWith("2023")));
    }
    @Test
    public void testProgramsSortedByEnrollmentsDescending() {
        statisticsSystem.sortProgramsByEnrollments("descending");
        List<Program> programs = statisticsSystem.getFilteredPrograms();
        for (int i = 1; i < programs.size(); i++) {
            Assert.assertTrue("Programs should be sorted in descending order of enrollments",
                    programs.get(i - 1).getNumberOfEnrollments() >= programs.get(i).getNumberOfEnrollments());
        }
    }
    @Test
    public void testProgramsSortedByEnrollmentsAscending() {
        statisticsSystem.sortProgramsByEnrollments("ascending");
        List<Program> programs = statisticsSystem.getFilteredPrograms();
        for (int i = 1; i < programs.size(); i++) {
            Assert.assertTrue("Programs should be sorted in ascending order of enrollments",
                    programs.get(i - 1).getNumberOfEnrollments() <= programs.get(i).getNumberOfEnrollments());
        }
    }
    @Test
    public void testPagination() {
        statisticsSystem.setPaginationThreshold(2);
        List<Program> paginatedPrograms = statisticsSystem.getPaginatedPrograms();
        Assert.assertNotNull("Paginated programs should not be null", paginatedPrograms);
        Assert.assertTrue("Paginated programs should be limited to threshold",
                paginatedPrograms.size() <= 2);
    }
    @Test
    public void testFilterOptionsAvailable() {
        List<String> availableFilters = statisticsSystem.getAvailableFilters();
        Assert.assertTrue("Available filters should include enrollment range", availableFilters.contains("Enrollment Range"));
        Assert.assertTrue("Available filters should include program status", availableFilters.contains("Program Status"));
        Assert.assertTrue("Available filters should include date range", availableFilters.contains("Date Range"));
    }
}
