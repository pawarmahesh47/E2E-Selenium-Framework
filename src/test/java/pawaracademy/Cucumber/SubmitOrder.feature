@tag
Feature: Title of your feature
  I want to use this template for my feature file

Background:
Given I landed on E-commerence page

  @Regression
  Scenario Outline: E2E validation for submit order from E-commerence
    Given I login to e-commerence website with username <username> and password <password>
    When I add product <productName> to cart
    And checkout <productName> & submit the order 
    Then "Thankyou for the order." messsage displayed on confirmation page

    Examples: 
      | username        | password  | productName  |
      | nirvi@gmail.com | Nirvi@123 | ZARA COAT 3  |
