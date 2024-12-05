Feature: Admin Monitoring of User Activity and Engagement Statistics

  Scenario: Admin views overall user activity report
    Given the admin is logged in
    When the admin views the overall user activity report
    Then the admin should see the total number of active instructors and clients
    And the admin should see the total number of logins for each user role (instructors and clients)

  Scenario: Admin views instructor activity statistics
    Given the admin is logged in
    When the admin views the instructor activity report
    Then the admin should see the number of programs created by each instructor
    And the admin should see the number of clients assigned to each instructor
    And the admin should see the frequency of instructor logins

  Scenario: Admin views client engagement statistics
    Given the admin is logged in
    When the admin views the client engagement report
    Then the admin should see the number of fitness programs the clients have enrolled in
    And the admin should see the number of completed workouts for each client
    And the admin should see the frequency of client logins

  Scenario: Admin generates a detailed engagement report
    Given the admin is logged in
    When the admin generates a detailed engagement report for instructors and clients
    Then the report should include detailed activities for both instructors and clients
    And the report should show engagement trends over time (e.g., weekly or monthly)
    And the admin should be able to export the report in CSV or PDF format
