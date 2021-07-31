Feature: Chore creation
  As a Parent User
  I want to define a Chore
  so that I can find it in the application

  Background: 
    Given I have access the url "/chores"

  #Happy day Scenario
  Scenario: A new chore can be created
    When I create a new chore with name "Read a chapter book", description "To complete this chore you have to read at least 7 pages" and points 10
    Then I should be able to find the chore with name "Read a chapter book" on the application

  #Validations
  #1. Chore name is mandatory
  #2. Points is mandatory and have to be more than 0
  Scenario Outline: Cannot create new chore due Validations
    When I create a new chore with name "<choreName>", description "<description>" and points <points>
    Then An error message "<error>" is shown

    Examples: 
      | choreName | description             | points | error                           |
      |           | Any description         |     10 | Chore name cannot be empty      |
      | chore01   | Another description     |        | Points cannot be null           |
      | chore02   | Description for chore02 |      0 | Points have to be bigger than 0 |
