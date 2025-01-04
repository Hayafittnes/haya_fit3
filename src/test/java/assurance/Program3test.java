package assurance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Program3test {
    PROGRAM3 program3 = new PROGRAM3();

    @Test
    public void testNavigateToProgramTrackingSection() {
        program3.the_admin_navigates_to_the_program_tracking_section();
    }
    @Test
    public void testSelectProgramStatus() {
        PROGRAM3 program3 = new PROGRAM3();
        program3.generateProgramList("Active");
        program3.the_admin_selects_the_program_status_from_the_filter_options("Active");
        program3.the_system_should_display_a_list_of_programs_with_the_status("Active");
    }

    @Test
    public void testDisplayProgramsInDescendingOrder() {
        program3.generateProgramList("Active");
        program3.the_programs_should_be_displayed_in_descending_order_of_their_start_date();
        List<Map<String, String>> programs = program3.programs;
        for (int i = 0; i < programs.size() - 1; i++) {
            String startDate1 = programs.get(i).get("Start Date");
            String startDate2 = programs.get(i + 1).get("Start Date");
            assertTrue("Programs are not in descending order", startDate1.compareTo(startDate2) >= 0);
        }
    }
    @Test
    public void testPaginationWhenMoreThan10Programs() {
        program3.generateProgramList("Active");
         for (int i = 0; i < 8; i++) {
            program3.programs.add(Map.of(
                    "Program Name", "Program X" + i,
                    "Number of Enrollments", "40",
                    "Start Date", "2024-01-" + (10 + i),
                    "End Date", "2024-06-" + (10 + i),
                    "Status", "Active"
            ));
        }
        program3.if_there_are_more_than_10_programs_the_system_should_paginate_the_list();
        assertTrue("Program list size is not greater than 10", program3.programs.size() > 10);
    }
}
