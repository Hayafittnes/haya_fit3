Feature: Generate reports on revenue, attendance, and client progress

  Scenario Outline: Generate a detailed report for a selected period
    Given the admin is logged into the system
    When the admin navigates to the report generation section
    And the admin selects the report type "<Report Type>"
    And the admin selects the reporting period "<Reporting Period>"
    Then the system should generate a report that includes:
      | Revenue                  |
      | Total Revenue            |
      | Number of Clients        |
      | Attendance               |
      | Total Attendance         |
      | Client Progress          |
      | Client Progress Overview |
    And the report should be available for download or viewing
    And the admin should be able to download the report in PDF or CSV format

    Examples:
      | Report Type    | Reporting Period |
      | Revenue        | Last Month       |
      | Attendance     | This Week        |
      | Client Progress| Last 3 Months    |
      | Revenue        | This Quarter     |

