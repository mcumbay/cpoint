Feature: Chore assignation
  As a Parent User
  I want to asssign a Chore to my kid User
  So that I can find it in the application

  Background: 
    Given I have access the url "/users/assign" 
    And A user with userName "Rylan" and password "1234" already exists
    And A user with userName "Hunter" and password "5678" already exists
    And A chore with name "Clean the living room", description "You have to dust off the TV and sweep the floor" and points 15 already exists

  #Happy day scenario
  Scenario: A chore is assign to a Kid User
    When I assign the chore "Clean the living room" to the User "Rylan" for "07/30/2021"
    Then I should be able to find the chore "Clean the living room" on "Rylan" list of chores for "07/30/2021"

  #Validations
  #Scenario: A chore cannot be assign because is already assigned
  #  Given I assign the chore "Clean the living room" to the User "Hunter"
  #  When I assign the chore "Clean the living room" to the User "Rylan"
  #  Then An error message "Chore is already assigned to the User" is shown
