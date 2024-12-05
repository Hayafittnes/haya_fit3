Feature: admin User Management for Instructors and Clients
  Scenario: admin adds a new instructor account
    Given the admin is logged in
    When the admin adds a new instructor account with valid details
    Then the new instructor account should be created successfully
    And the instructor should receive a welcome email

  Scenario: admin updates an existing instructor account
    Given the admin is logged in
    And an instructor account exists with valid details
    When the admin updates the instructor's account details with new information
    Then the instructor account should be updated successfully
    And the instructor should receive a notification about the changes

  Scenario: admin deactivates an instructor account
    Given the admin is logged in
    And an instructor account exists
    When the admin deactivates the instructor account
    Then the instructor account should be deactivated
    And the instructor should receive a notification about the deactivation

  Scenario: admin adds a new client account
    Given the admin is logged in
    When the admin adds a new client account with valid details
    Then the new client account should be created successfully
    And the client should receive a welcome email

  Scenario: admin updates an existing client account
    Given the admin is logged in
    And a client account exists with valid details
    When the admin updates the client's account details with new information
    Then the client account should be updated successfully
    And the client should receive a notification about the changes

  Scenario: admin deactivates a client account
    Given the admin is logged in
    And a client account exists
    When the admin deactivates the client account
    Then the client account should be deactivated
    And the client should receive a notification about the deactivation