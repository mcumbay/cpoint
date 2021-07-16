#Autor: mcumbay
Feature: Chore Creation
  As a Parent User
  I want to define a Chore
  so that I can assign it to a Kid User

  #Happy day Scenario
  Scenario: A new chore can be created
    When I create a new chore with name "chore01", description "My first chore" and points 10
    Then I should be able to find the chore with name "chore01" on the application

  #Validations
  #1. Chore name is mandatory
  #2. Points is mandatory and have to be more than 0
  Scenario Outline: Cannot create new chore due Validations
    When I create a new chore with name "<choreName>", description "<description>" and points <points>
    Then An error message "<error>" is shown

    Examples: 
      | choreName | decription              | points | error                           |
      |           | Any description         |     10 | Chore name cannot be empty      |
      | chore01   | Another description     |        | Points cannot be null           |
      | chore02   | Description for chore02 |      0 | Points have to be bigger than 0 |
