Feature: Registration
  As a new user
  I want to be able to register into the application
  so that I can have access to the application

  Background: 
    Given I went to "registration" page

  Scenario: Sucessful Registration
    When I enter user "mcumbay" and password "1234"
    Then A sucessful message "Parent User created" is shown

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
