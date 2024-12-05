Feature: Approve or reject health and wellness content shared by instructors

  Scenario: Admin approves health and wellness articles or tips shared by instructors
    Given the admin is logged into the system
    When the admin navigates to the content management section
    And the admin views the health and wellness article or tip submitted by the instructor "<Instructor Name>"
    And the admin decides to approve the article or tip titled "<Content Title>"
    Then the system should update the content status to "Approved"
    And the system should notify the instructor "<Instructor Name>" about the content approval
    And the admin should be able to see a notification confirming the content's approval

    Examples:
      | Instructor Name | Content Title                  |
      | John Doe        | The Benefits of Yoga            |
      | Michael Lee     | How to Meditate for Stress Relief|
      | Sarah Miller    | Meditation and Mindfulness      |

  Scenario: Admin rejects health and wellness articles or tips shared by instructors
    Given the admin is logged into the system
    When the admin navigates to the content management section
    And the admin views the health and wellness article or tip submitted by the instructor "<Instructor Name>"
    And the admin decides to reject the article or tip titled "<Content Title>"
    And the admin enters the rejection reason "<Rejection Reason>"
    Then the system should update the content status to "Rejected"
    And the system should notify the instructor "<Instructor Name>" about the content rejection
    And the admin should be able to see a notification confirming the content's rejection

    Examples:
      | Instructor Name | Content Title                  | Rejection Reason                         |
      | John Doe        | 10 Quick Healthy Snacks         | Lacks reliable sources or research        |
      | Jane Smith      | The Detox Diet Myth             | Contains misleading health information    |
      | Sarah Miller    | Stress Relief Through Exercise  | Not scientifically supported              |

  Scenario: Admin approves health and wellness recipes shared by instructors
    Given the admin is logged into the system
    When the admin navigates to the content management section
    And the admin views the health and wellness recipe submitted by the instructor "<Instructor Name>"
    And the admin decides to approve the recipe titled "<Content Title>"
    Then the system should update the content status to "Approved"
    And the system should notify the instructor "<Instructor Name>" about the recipe approval
    And the admin should be able to see a notification confirming the recipe's approval

    Examples:
      | Instructor Name | Content Title                    |
      | Michael Lee     | Healthy Green Smoothie Recipe     |
      | Emma Wilson     | Vegan Protein Shake               |

  Scenario: Admin rejects health and wellness recipes shared by instructors
    Given the admin is logged into the system
    When the admin navigates to the content management section
    And the admin views the health and wellness recipe submitted by the instructor "<Instructor Name>"
    And the admin decides to reject the recipe titled "<Content Title>"
    And the admin enters the rejection reason "<Rejection Reason>"
    Then the system should update the content status to "Rejected"
    And the system should notify the instructor "<Instructor Name>" about the recipe rejection
    And the admin should be able to see a notification confirming the recipe's rejection

    Examples:
      | Instructor Name | Content Title                     | Rejection Reason                   |
      | Michael Lee     | Sugar-Free Dessert Recipes         | Lacks proper nutritional value     |
      | Emma Wilson     | Quick Vegan Dinner Ideas           | Not suitable for all dietary needs |
