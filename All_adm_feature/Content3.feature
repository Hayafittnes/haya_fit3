Feature: Handle user feedback and complaints

  Scenario Outline: Admin handles user feedback or complaints
    Given the admin is logged into the system
    When the admin navigates to the feedback and complaints management section
    And the admin views the feedback or complaint submitted by the user "<User Name>"
    And the admin decides to "<Action>" the feedback or complaint
    And if the action is "Reject", the admin enters the rejection reason "<Rejection Reason>"
    Then the system should update the status of the feedback or complaint to "<Feedback Status>"
    And the system should notify the user "<User Name>" about the resolution via email or in-app notification
    And the admin should be able to see a notification confirming the feedback or complaint status change

    Examples:
      | Action   | User Name      | Feedback Status | Rejection Reason                    |
      | Resolve  | John Doe       | Resolved        |                                      |
      | Reject   | Jane Smith     | Rejected        | Insufficient information provided   |
      | Resolve  | Michael Lee    | Resolved        |                                      |
      | Reject   | Sarah Miller   | Rejected        | Issue is outside the scope of services |


