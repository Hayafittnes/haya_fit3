Feature: Approve or reject wellness articles, tips, or recipes shared by instructors
@fail
  Scenario Outline: Admin approves or rejects wellness content shared by instructors
    Given the admin is logged into the system
    When the admin navigates to the content management section
    And the admin views the content submitted by the instructors
    And the admin decides to "<Action>" the content titled "<Content Title>"
    And if the action is "Reject", the admin enters the rejection reason "<Rejection Reason>"
   Then the system should update the content status to "{string}"
    And the system should notify the instructor about the content status change
    And the admin should be able to view a notification confirming the content's status change

    Examples:
      | Action   | Content Title                     | Content Status | Rejection Reason               |
      | Approve  | 5 Healthy Smoothie Recipes         | Approved      |                                |
      | Reject   | Best Workouts for Beginners        | Rejected      | Content does not meet standards |
      | Approve  | Meditation Tips for Stress Relief | Approved      |                                |
      | Reject   | Top 10 Fat Burning Foods           | Rejected      | Lacks proper research sources   | 