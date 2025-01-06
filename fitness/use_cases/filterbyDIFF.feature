Feature: Program Exploration and Enrollment
  As a user, I want to browse fitness programs and enroll in them.

  Scenario Outline: Browse programs by difficulty level
    Given I am on the programs page
    When I filter programs by difficulty level <difficulty>
    Then I should see a list of <difficulty> level programs

  Examples:
    | difficulty    |
    | "Beginner"      |
    | "Intermediate"  |
    | "Advanced"      |
    
