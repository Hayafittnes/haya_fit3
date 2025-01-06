Feature: Enroll in a fitness program

  Scenario Outline: Enroll in a fitness program
    Given I have logged into my fitness account
    And I am on the programs page
    And I have filtered programs by difficulty level "<DifficultyLevel>"
    When I select the program "<Program>" to enroll
    Then I should see a confirmation message that says "You have successfully enrolled in <Program>"
    And the program "<Program>" should be added to my enrolled programs list

    Examples:
      | DifficultyLevel | Program                                               |
      | Beginner         |Beginner - Yoga for Beginners(Flexibility)|
      | Beginner         |Beginner - Cardio Basics(Weight Loss)|
      | Intermediate     |Intermediate - Strength Training(Strength Building)|
      | Advanced         |Advanced - Powerlifting(Strength Building)|

  Scenario Outline: View the schedule for an enrolled program
    Given I have logged into my fitness account
    And I am enrolled in the program "<Program>"
    When I view the schedule for "<Program>"
    Then I should see the "<Program>" schedule, including the days and times of the sessions

    Examples:
      | Program                                               |
      | Beginner - Yoga for Beginners(Flexibility)          |
      | Beginner - Cardio Basics(Weight Loss)               |
      | Intermediate - Strength Training(Strength Building) |
      | Advanced - Powerlifting(Strength Building)          |