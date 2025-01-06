   Feature: Program Exploration and Enrollment
  As a user, I want to browse fitness programs and enroll in them.

  Scenario Outline: Browse programs by focus area
    Given I am on the programs page
    When I filter programs by focus area <focusArea>
    Then I should see a list of programs focused on <focusArea>

  Examples:
    | focusArea       |
    | "Weight Loss"     |
    | "Strength Building" |
    | "Flexibility"      |
