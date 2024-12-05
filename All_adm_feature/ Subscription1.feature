Feature: Manage subscription plans for clients and instructors

  Scenario Outline: Admin manages subscription plans for clients and instructors
    Given the admin is logged into the system
    When the admin navigates to the subscription management section
    And the admin views the subscription details for the user "<User Name>"
    And the admin sees the current subscription plan "<Current Plan>"
    And the admin decides to change the subscription plan for the user to "<Subscription Plan>"
    Then the system should update the user's subscription plan to "<Subscription Plan>"
    And the system should notify the user "<User Name>" about the subscription plan change via in-app notification
    And the system should reflect the new subscription plan in the user's profile
    And the admin should be able to see a confirmation of the subscription update

  Scenario: Admin sets subscription plan for a new user
    Given the admin is logged into the system
    When the admin navigates to the user registration section
    And the admin adds a new user "<New User Name>" with email "<User Email>"
    And the admin assigns the subscription plan "<New Subscription Plan>" to the new user
    Then the system should create the new user account with the subscription plan "<New Subscription Plan>"
    And the system should notify the new user "<New User Name>" about the subscription plan via in-app notification
    And the system should reflect the new subscription plan in the user's profile

  Scenario: Admin deactivates subscription for a user
    Given the admin is logged into the system
    When the admin navigates to the subscription management section
    And the admin views the subscription details for the user "<User Name>"
    And the admin decides to deactivate the subscription for the user
    Then the system should deactivate the user's subscription
    And the system should notify the user "<User Name>" about the subscription deactivation via in-app notification
    And the system should reflect the deactivated subscription status in the user's profile

  Examples:
    | User Name   | Current Plan | Subscription Plan |
    | mohammed khatatbeh    | Basic        | Premium           |
    | Jane ayman  | Premium      | Basic             |
    | Masa radi | Basic        | Premium           |
    | Sarah akram | Premium      | Basic             |

    | New User Name | User Email            | New Subscription Plan |
    | shahed thaher   | emma@example.com      | Premium              |
    | haya hadi    | noah.davis@example.com| Basic                |
