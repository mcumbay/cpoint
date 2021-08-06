Feature: New User Registration
  As a new user
  I want to be able to register a new account
  so that I can manage my kids chores

  Background: 
    Given I went to "registration" page

  Scenario: Sucessful Registration
    When I enter user name "mcumbay" and password "1234"
    And submit the page
    Then A sucessful message "User mcumbay has been created created" is shown

  Scenario Outline: Registration Validations Errors
    Given A Parent user with userName "mcumbay" already exists
    When I enter user "<userName>" and password "<password>"
    Then An error message "<error>" is shown

    Examples: 
      | userName | password       | error                                                                 |
      |          | mytestpassword | User Name is mandatory                                                |
      | mcumbay  |                | Password is mandatory                                                 |
      | mcumbay  | mytestpassword | User mcumbay already exists                                           |
      | admin    | small          | Your password must be at least 6 characters long. Please try another. |
