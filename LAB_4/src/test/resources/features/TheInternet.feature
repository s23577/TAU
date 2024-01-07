Feature: Tests for website TheInternet.herokuapp.com

  Scenario Outline: Login with valid credentials
    Given I open The Internet page using "<browser>"
    When I open link Form Authentication
    And I fill login in form with "tomsmith"
    And I fill password in form with "SuperSecretPassword!"
    And I click on button Login
    Then I should properly login
    And new page should be opened

    Examples:
      | browser |
      | chrome  |
      | firefox |

  Scenario Outline: Login with invalid credentials
    Given I open The Internet page using "<browser>"
    When I open link Form Authentication
    And I fill login in form with "Selenium"
    And I fill password in form with "Selenium"
    And I click on button Login
    Then I should not login
    And proper message should be displayed

    Examples:
      | browser |
      | chrome  |
      | firefox |