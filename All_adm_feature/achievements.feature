Feature: View achievements and badges for completed programs

  Scenario Outline: Earn a badge for completing a fitness program
    Given I have completed the program "<program>"
    When I view my achievements
    Then I should see a badge titled "<badgeTitle>"
    And the description should be "<badgeDescription>"

    Examples:
      | program                          | badgeTitle      | badgeDescription                                                        |
      | Beginner - Yoga for Beginners   | Yoga Beginner   | Awarded for completing the Beginner - Yoga for Beginners program.        |

  Scenario Outline: View achievements for multiple completed programs
    Given I have completed the programs "<completedPrograms>"
    When I view my achievements
    Then I should see the following badges:
      | Badge Title       | Description                                                      |
      | <badgeTitle1>     | <badgeDescription1>                                             |
      | <badgeTitle2>     | <badgeDescription2>                                             |

    Examples:
      | completedPrograms                                | badgeTitle1      | badgeDescription1                                               | badgeTitle2      | badgeDescription2                                          |
      | Beginner - Yoga for Beginners, Beginner - Cardio Basics | Yoga Beginner   | Awarded for completing the Beginner - Yoga for Beginners program | Cardio Starter   | Awarded for completing the Beginner - Cardio Basics program |

  Scenario Outline: No achievements for incomplete programs
    Given I am currently enrolled in the program "<enrolledProgram>"
    When I view my achievements
    Then I should see a message "No achievements available yet. Complete a program to earn a badge."

    Examples:
      | enrolledProgram             |
      | Intermediate - Strength Training |

  Scenario Outline: View total achievements summary
    Given I have completed <totalPrograms> fitness programs
    When I view my achievements summary
    Then I should see "You have earned <totalBadges> badges for completing fitness programs."

    Examples:
      | totalPrograms | totalBadges |
      | 3             | 3           |
