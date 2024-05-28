
@tag
Feature: purchase the order from Ecommerce website
  I want to use this template for my feature file

  
  Background:
  Given I landed on Ecommerce page
  

  @Regression
  Scenario Outline: positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I added product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name             | password           | productName  |
      | sanjud@gmail.com |     Sh@nm@thi8*    | ZARA COAT 3  |
      
