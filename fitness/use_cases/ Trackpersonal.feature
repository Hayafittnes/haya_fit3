Feature: Track personal fitness milestones

  Scenario Outline: Update weight milestone
    Given I have logged into my fitness account
    When I update my weight to <weight>
    Then my weight should be successfully updated to <weight>
    And I should see a confirmation message "Your weight has been updated to <weight>."

    Examples:
      | weight |
      | 68.0 kg  |
      | 75.0 kg  |
      | 80.0 kg  |

  Scenario Outline: Update BMI milestone
    Given I have logged into my fitness account
    When I update my BMI to <bmi>
    Then my BMI should be successfully updated to <bmi>
    And I should see a confirmation message "Your BMI has been updated to <bmi>."

    Examples:
      | bmi  |
      | 22.8 |
      | 25.4 |
      | 18.6 |

  Scenario Outline: Record attendance for a fitness program
    Given I am enrolled in the program "<program_name>"
    When I mark my attendance for the session on "<date>" as "<status>"
    Then my attendance for "<program_name>" on "<date>" should be recorded as "<status>"
    And I should see a confirmation message "Your attendance for <program_name> on <date> has been recorded."

    Examples:
      | program_name               | date       | status    |
      | Beginner - Yoga for Beginners | 2024-12-10 | Attended  |
      | Advanced - Powerlifting   | 2024-12-12 | Missed    |
      | Strength Training          | 2024-12-13 | Attended  |

  Scenario: View all tracked milestones
    Given I have tracked <weight>, <bmi>, and attendance
    When I view my tracked milestones
    Then I should see my current weight as <weight>
    And my current BMI as <bmi>
    And my attendance record for "<program_name>" as "<attendance_record>"

    Examples:
      | weight | bmi  | program_name               | attendance_record       |
      | 68.0 kg  | 22.8 | Beginner - Yoga for Beginners| 5 sessions attended    |
      | 75.0 kg  | 25.4 | Advanced - Cardio Program| 3 sessions attended    |
      | 80.0 kg  | 18.6 | Intermediate - Strength Training| 10 sessions attended   |