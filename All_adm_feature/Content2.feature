Feature: Approve articles or tips shared on health and wellness
@fail
  Scenario Outline: Admin approves health and wellness content shared by instructors
    Given the admien is logged into the system
    When the admin navigates to the health and wellness content management section
    And the admin views the article or tip submitted by the instructor "<Instructor Name>"
    And the admin decides to "<Action>" the article or tip titled "<Content Title>"
    Then the system should update the contents status to "<Content Status>"
    And the system should notify the instructor "<Instructor Name>" about the content status change via email or in-app notification
    And the admin should be able to see a notification confirming the content's status change

    Examples:
      | Action   | Instructor Name | Content Title              | Content Status |
      | Approve  | John Doe        | Benefits of Yoga           | Approved       |
      | Reject   | Jane Smith      | 10 Quick Healthy Snacks     | Rejected       |
      | Approve  | Michael Lee     | How to Meditate Properly    | Approved       |
      | Reject   | Sarah Miller    | Top 5 Fat Burning Foods     | Rejected       |
