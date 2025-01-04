Feature: Admin User Management for Instructors and Clients
Background:
   Given the admin1 is logged in
  Scenario: Admin adds a new instructor account
    When the admin adds a new instructor account with valid details
    Then the new instructor account should be created successfully
    Then the instructor should be notified with a welcome message 
  Scenario: Admin updates an existing instructor account
    Given an instructor account exists with valid details
   When the admin updates the instructor's account details with new information "John Doe" and "john.doe@example.com"
    Then the instructor account should be updated successfully
    And the instructor should be notified about the changes

  Scenario: Admin deactivates an instructor account
    Given an instructor account exists
    When the admin deactivates the instructor account
    Then the instructor account should be deactivated
    And the instructor should be notified about the deactivation

  Scenario: Admin adds a new client account
    When the admin adds a new client account with valid details
    Then the new client account should be created successfully
    And the client should be notified with a welcome message
