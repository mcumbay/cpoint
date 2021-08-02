Feature: User Creation
  As a Parent User
  I want to register my kid
  so that I can find him on the application

  Background: 
    Given I have access the url "/users"

  #Happy day Scenario
  Scenario: A new Kid User can be created
    When I create a new user with User Name "Preston" and password "1234"
    Then I should be able to find the user with User Name "Preston" on the application

  #Validations:
  # 1.User Name is mandatory.
  # 2.Pasword is mandatory.
  # 3.User Name should be unique.
  Scenario Outline: Cannot create new user due Validations
    Given A user with userName "userTest01" and password "anyPassword" already exists
    When I create a new user with User Name "<userName>" and password "<password>"
    Then An error message "<error>" is shown

    Examples: 
      | userName    | password    | error                     |
      |             | anyPassword | User Name cannot be null  |
      | anyUserName |             | Password cannot be null   |
      | userTest01  | anyPassword | User Name already exists. |
