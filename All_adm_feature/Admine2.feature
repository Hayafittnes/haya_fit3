Feature: Admin Approval of New Instructor Registrations

  Background:
    Given the admin2 is logged in

  Scenario: Admin views pending instructor registration requests
    When the admin views the list of pending instructor registrations
    Then the admin should see all instructor registration requests that are pending approval

  Scenario: Admin approves a new instructor registration
    Given there is a pending instructor registration
    When the admin approves the pending instructor registration
    Then the instructor should be approved and granted access to the system
    And the instructor should receive a confirmation email about their approval

  Scenario: Admin rejects a new instructor registration
    Given there is a pending instructor registration
    When the admin rejects the pending instructor registration
    Then the instructor should be notified that their registration was rejected
    And the registration request should be marked as rejected in the system

  Scenario: Admin views instructor registration details before approval
    Given there is a pending instructor registration
    When the admin views the details of the pending instructor registration
    Then the admin should be able to see the full registration information e.g., qualifications, experience, and contact info
    And the admin should have the option to approve or reject the registration
