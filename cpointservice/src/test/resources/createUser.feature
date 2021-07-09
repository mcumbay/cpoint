Feature: User Creation
  As a parent
  I want to register my kid
	so that he can access the application
 
  Scenario: A new user can be created       
    When I create a new user with userName "test02"
    And password "password"    
    Then I should be able to find the user on the application
  
  #Validations are:
  # 1.User Name is mandatory.
  # 2.Pasword is mandatory.
  # 3.User Name should be unique.
  Scenario Outline: Cannot create new user due Validations
    Given A user with userName "userTest01" and password "anyPassword" already exists
    When I create a new user with userName "<userName>"
    And password "<password>"  
    Then An error message "<error>" is shown
    
    Examples:
    |userName   |password   |error                     |
    |           |anyPassword|User Name cannot be empty |
    |anyUserName|           |Password cannot be empty  |
    |userTest01 |anyPassword|User Name already exists  |
    