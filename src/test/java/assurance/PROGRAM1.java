package assurance;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class PROGRAM1 {
   ProgramStatisticsSystem statisticsSystem = new ProgramStatisticsSystem();

    @When("the admin navigates to the program statistics section")
    public void the_admin_navigates_to_the_program_statistics_section() {
       statisticsSystem.navigateToStatisticsSection();
    }

    @When("the admin selects the enrollment range {string} from the filter options")
    public void the_admin_selects_the_enrollment_range_from_the_filter_options(String enrollmentRange) {
        statisticsSystem.selectEnrollmentRange(enrollmentRange);
    }

    @When("the admin filters the programs by status {string}")
    public void the_admin_filters_the_programs_by_status(String programStatus) {
        statisticsSystem.filterByStatus(programStatus);
    }

    @When("the admin selects the date range {string}")
    public void the_admin_selects_the_date_range(String dateRange) {
      statisticsSystem.selectDateRange(dateRange);
    }

    @Then("the system should display a list of programs sorted by the number of enrollments")
    public void the_system_should_display_a_list_of_programs_sorted_by_the_number_of_enrollments() {
    List<Program> programs = statisticsSystem.getFilteredPrograms();
  assert programs != null && !programs.isEmpty() : "No programs found for the given filters.";
    }

    @Then("the list should include the following details for each program:")
    public void the_list_should_include_the_following_details_for_each_program(io.cucumber.datatable.DataTable dataTable) {
      List<List<String>> expectedDetails = dataTable.asLists(String.class);
        List<Program> programs = statisticsSystem.getFilteredPrograms();
        for (int i = 0; i < programs.size(); i++) {
            Program program = programs.get(i);
            List<String> expectedDetail = expectedDetails.get(i);
            assert program.getName().equals(expectedDetail.get(0)) : "Program name mismatch.";
            assert program.getNumberOfEnrollments().equals(Integer.parseInt(expectedDetail.get(1))) : "Enrollment number mismatch.";
            assert program.getStatus().equals(expectedDetail.get(2)) : "Program status mismatch.";
            assert program.getStartDate().equals(expectedDetail.get(3)) : "Program start date mismatch.";
            assert program.getEndDate().equals(expectedDetail.get(4)) : "Program end date mismatch.";
        }
    }

    @Then("the programs should be displayed in descending order of the number of enrollments")
    public void the_programs_should_be_displayed_in_descending_order_of_the_number_of_enrollments() {
       List<Program> programs = statisticsSystem.getFilteredPrograms();
        for (int i = 1; i < programs.size(); i++) {
            Integer prevEnrollments = (Integer) programs.get(i - 1).getNumberOfEnrollments(); // Cast to Integer
            Integer currEnrollments = (Integer) programs.get(i).getNumberOfEnrollments(); // Cast to Integer
            assert prevEnrollments >= currEnrollments : "Programs are not sorted in descending order of enrollments.";
        }
    }

    @Then("the programs should be displayed in ascending order of the number of enrollments")
    public void the_programs_should_be_displayed_in_ascending_order_of_the_number_of_enrollments() {
     List<Program> programs = statisticsSystem.getFilteredPrograms();
        for (int i = 1; i < programs.size(); i++) {
            Integer prevEnrollments = (Integer) programs.get(i - 1).getNumberOfEnrollments(); // Cast to Integer
            Integer currEnrollments = (Integer) programs.get(i).getNumberOfEnrollments(); // Cast to Integer
            assert prevEnrollments <= currEnrollments : "Programs are not sorted in ascending order of enrollments.";
        }
    }
    @Then("if there are more than {int} programs, paginate the list")
    public void if_there_are_more_than_programs_paginate_the_list(int programCount) {
       List<Program> programs = statisticsSystem.getFilteredPrograms();
        assertNotNull("Filtered programs list should not be null.", programs);
        if (programs.size() > programCount) {
            assertTrue("The program list should be paginated.", statisticsSystem.isPaginated());
        } else {
            assertFalse("The program list should not be paginated.", statisticsSystem.isPaginated());
        }
    }

    @Then("the admin should have the option to filter programs by:")
    public void the_admin_should_have_the_option_to_filter_programs_by(io.cucumber.datatable.DataTable dataTable) {
     List<List<String>> filterOptions = dataTable.asLists(String.class);
      assert filterOptions.contains(List.of("Enrollment Range", "Program Status", "Date Range")) : "Admin does not have the correct filter options.";
    }

    @Then("the programs should be displayed in {string} order of the number of enrollments")
    public void the_programs_should_be_displayed_in_order_of_the_number_of_enrollments(String order) {
  List<Program> programs = statisticsSystem.getFilteredPrograms();
        for (int i = 1; i < programs.size(); i++) {
            Integer prevEnrollments = (Integer) programs.get(i - 1).getNumberOfEnrollments(); // Cast to Integer
            Integer currEnrollments = (Integer) programs.get(i).getNumberOfEnrollments(); // Cast to Integer
            if (order.equals("ascending")) {
                assert prevEnrollments <= currEnrollments : "Programs are not sorted in ascending order.";
            } else if (order.equals("descending")) {
                assert prevEnrollments >= currEnrollments : "Programs are not sorted in descending order.";
            }
           
        }
    }
 
}
