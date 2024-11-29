
@tag
Feature: Validate Error Validation
 
  @ErrorValidation
  Scenario Outline: Validate error msg after login
    Given I landed on E-commerence page
    When I login to e-commerence website with username <username> and password <password>
    Then "Incorrect email or password." error msg display on page

    Examples: 
      | username        | password  | productName  |
      | nirvi@gmail.com | Nirvi@12 | ZARA COAT 3  |