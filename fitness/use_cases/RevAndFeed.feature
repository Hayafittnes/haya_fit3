Feature: Provide feedback and reviews for completed programs

  Scenario Outline: Rate and review a completed program
    Given I have done the program "<programName>"
    When I rate the program <rating> stars
    And I submit the review "<review>"
    Then the review should be successfully saved
    And I should see a noute "Thank you for your feedback on <programName>."

    Examples:
      | programName                    | rating | review                                           |
      | Beginner - Yoga for Beginners | 5      | Excellent program for beginners, highly recommended! |

  Scenario Outline: Submit improvement suggestions to instructors
    Given I have completed the program "<programName>"
    When I submit the suggestion "<suggestion>"
    Then the suggestion should be successfully sent to the instructor
    And I should see a noute "Your suggestion has been submitted. Thank you for your input!"

    Examples:
      | programName                     | suggestion                                   |
      | Intermediate - Strength Training | Add more upper body exercises to the routine. |

  Scenario Outline: Prevent feedback for incomplete programs
    Given I am currently enrolled in the program "<programName>"
    When I try to rate or review the program
    Then I should see an error message "You can only rate or review completed programs."

    Examples:
      | programName                  |
      | Advanced - Powerlifting     |  this is my file please write the test code and the main code 