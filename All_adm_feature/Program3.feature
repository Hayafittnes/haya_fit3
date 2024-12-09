Feature: Track active and completed programs

  Scenario Outline: Track programs based on their status (Active or Completed)
    Given the admin is logged into the system
    When the admin navigates to the program tracking section
    And the admin selects the program status "<Program Status>" from the filter options
    Then the system should display a list of programs with the status "<Program Status>"
    And the list should include the following details for each program:
      | Program Name     | Number of Enrollments | Start Date   | End Date     | Status       |
    And the programs should be displayed in descending order of their start date
    And if there are more than 10 programs, the system should paginate the list

    Examples:
      | Program Status |
      | Active         |
      | Completed      |
