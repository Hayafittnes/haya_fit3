Feature: Account Management
  As a user, I want to create and customize my profile with personal details and dietary preferences.

  Scenario: Create a new user profile
    Given I am a new user
    When I provide personal details such as age and fitness goals
    And I set dietary preferences or restrictions
    Then my profile should be successfully created
    
    Scenario Outline: Update the user's fitness goals
    Given I have an existing user profile
    When I update my fitness goals to "<newFitnessGoal>"
    Then my fitness goals should be updated to "<newFitnessGoal>"

    Examples:
      | newFitnessGoal       |
      | Increase stamina     |
      | Build muscle         |
      | Lose weight          |

  Scenario Outline: Update dietary preferences for the user
    Given I have an existing user profile
    When I update my dietary preferences to "<newDietaryPreference>"
    Then my dietary preferences should be updated to "<newDietaryPreference>"

    Examples:
      | newDietaryPreference |
      | Vegan                |
      | Gluten-free          |
      | No dairy             |

  Scenario Outline: View the user's profile
  Given I have an existing user profile with ID <userId> and age <age> and "<fitnessGoals>" and "<dietaryPreferences>"
  When I view my profile
  Then I should see my personal details: my age <age>  , my fitness goals "<fitnessGoals>"
  And I should see my dietary preferences "<dietaryPreferences>" 

Examples:
  | userId | age | fitnessGoals        | dietaryPreferences |
  | 1      | 30  | Lose weight         | Vegan              |
  | 2      | 25  | Build muscle        | Gluten-free        |
  | 3      | 40  | Improve endurance   | No dairy           |