  
  Feature: admin Approval of New Instructor Registrations
   Scenario: admin views pending instructor registration requests
    Given the admin is logged in
    When the admin views the list of pending instructor registrations
    Then the admin should see all instructor registration requests that are pending approval

  Scenario: admin approves a new instructor registration
    Given the admin is logged in
    And there is a pending instructor registration
    When the admin approves the pending instructor registration
    Then the instructor should be approved and granted access to the system
    And the instructor should receive a confirmation email about their approval

  Scenario: admin rejects a new instructor registration
    Given the admin is logged in
    And there is a pending instructor registration
    When the admin rejects the pending instructor registration
    Then the instructor should be notified that their registration was rejected
    And the registration request should be marked as rejected in the system

  Scenario: admin views instructor registration details before approval
    Given the admin is logged in
    And there is a pending instructor registration
    When the admin views the details of the pending instructor registration
    Then the admin should be able to see the full registration information (e.g., qualifications, experience, and contact info)
    And the admin should have the option to approve or reject the registration
    