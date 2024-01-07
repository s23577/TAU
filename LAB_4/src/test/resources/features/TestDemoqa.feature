Feature: Tests for website DemoQA

  Scenario Outline: Checkbox test
    Given I open DEMOQA page using browser "<browser>"
    When I click on elements module
    And I select Check Box
    And I click on checkbox near Home option
    Then I should see "home" in the message

    Examples:
      | browser |
      | chrome  |
      | firefox |

  Scenario Outline: Form with valid data test
    Given I open DEMOQA page using browser "<browser>"
    When I click on elements module
    And I select Text Box
    And I fill Name in form with "SeleniumTest"
    And I fill Email in form with "SeleniumTest@gselenium.com"
    And I fill Address in form with "Selenium street 100"
    And I click on button Submit
    Then I should see the div below with provided data in form

    Examples:
      | browser |
      | chrome  |
      | firefox |

  Scenario Outline: Form with invalid data test
    Given I open DEMOQA page using browser "<browser>"
    When I click on elements module
    And I select Text Box
    And I fill Name in form with "SeleniumTest"
    And I fill Email in form with "SeleniumTest"
    And I fill Address in form with "Selenium street 100"
    And I click on button Submit
    Then I should not see the div below with provided data in form

    Examples:
      | browser |
      | chrome  |
      | firefox |
