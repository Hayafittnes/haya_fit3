Feature: View statistics on the most popular programs by enrollment
@fail
  Scenario Outline: Display programs sorted by enrollment numbers with filters
    Given the admin is logged into the system
    When the admin navigates to the program statistics section
    And the admin selects the enrollment range "<Enrollment Range>" from the filter options
    And the admin filters the programs by status "<Program Status>"
    And the admin selects the date range "<Date Range>"
    Then the system should display a list of programs sorted by the number of enrollments
    And the list should include the following details for each program:
      | Program Name     | Number of Enrollments | Program Status | Start Date   | End Date     |
    And the programs should be displayed in <order> order of the number of enrollments
    And if there are more than {int} programs, paginate the list
    And the admin should have the option to filter programs by:
      | Enrollment Range | Program Status | Date Range |

    Examples:
      | order       | Enrollment Range | Program Status | Date Range    |
      | descending  | 100-200          | Active         | Last 30 days  |
      | ascending   | 50-100           | Completed      | Last 6 months |
      | descending  | 0-50             | Active         | This year     |



