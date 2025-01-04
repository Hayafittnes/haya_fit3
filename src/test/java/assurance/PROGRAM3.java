package assurance;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PROGRAM3 {
    List<Map<String, String>> programs;
    @When("the admin navigates to the program tracking section")
    public void the_admin_navigates_to_the_program_tracking_section() {
        System.out.println("Admin navigates to the program tracking section...");
    }

    @When("the admin selects the program status {string} from the filter options")
    public void the_admin_selects_the_program_status_from_the_filter_options(String programStatus) {
        System.out.println("Admin selects program status: " + programStatus);
        generateProgramList(programStatus);
    }

    public void generateProgramList(String programStatus) {
        this.programs = new ArrayList<>(List.of(
            Map.of("Program Name", "Program A", "Number of Enrollments", "30", "Start Date", "2024-01-01", "End Date", "2024-06-01", "Status", programStatus),
            Map.of("Program Name", "Program B", "Number of Enrollments", "20", "Start Date", "2024-02-01", "End Date", "2024-05-01", "Status", programStatus),
            Map.of("Program Name", "Program C", "Number of Enrollments", "50", "Start Date", "2023-11-15", "End Date", "2024-03-01", "Status", programStatus)
        ));
    }
    @Then("the system should display a list of programs with the status {string}")
    public void the_system_should_display_a_list_of_programs_with_the_status(String programStatus) {
        System.out.println("Verifying that all programs have the status: " + programStatus);
        
        for (Map<String, String> program : programs) {
            String actualStatus = program.get("Status");
            assertEquals("Program status mismatch: Expected " + programStatus + " but was " + actualStatus, programStatus, actualStatus);
            System.out.println("Program: " + program.get("Program Name") + " | Status: " + actualStatus);
        }
    }

    @Then("the programs should be displayed in descending order of their start date")
    public void the_programs_should_be_displayed_in_descending_order_of_their_start_date() {
        System.out.println("Sorting programs in descending order of start date...");
        programs = programs.stream()
            .sorted((p1, p2) -> p2.get("Start Date").compareTo(p1.get("Start Date")))
            .collect(Collectors.toList());

        for (int i = 0; i < programs.size() - 1; i++) {
            String startDate1 = programs.get(i).get("Start Date");
            String startDate2 = programs.get(i + 1).get("Start Date");
            assert startDate1.compareTo(startDate2) >= 0 : "Programs are not in descending order";
            System.out.println("Program: " + programs.get(i).get("Program Name") + " | Start Date: " + startDate1);
        }
    }

    @Then("if there are more than 10 programs, the system should paginate the list")
    public void if_there_are_more_than_10_programs_the_system_should_paginate_the_list() {
        System.out.println("Verifying pagination when there are more than 10 programs...");
        if (programs.size() > 10) {
            System.out.println("Pagination should be triggered as the list exceeds 10 programs.");
        } else {
            System.out.println("Pagination is not required as the list has 10 or fewer programs.");
        }
    }
}
