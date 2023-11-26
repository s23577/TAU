# Test Scenario for DEMOQA.com

## Test Scenario 1: Checkbox test
- Given I open DEMOQA page 
- When I click on elements module
- And I select Check Box
- And I click button to expand the list of elements
- And I click on checkbox for value "Desktop"
- Then I should see the message "You have selected : desktop"

## Test Scenario 2: Form with valid data test
- Given I open DEMOQA page
- When I click on elements module
- And I select Text Box
- And I fill form with Name
- And I fill form with Email
- And I fill form with Current Address
- And I click on button Submit
- Then I should see the div below with provided data in Form

## Test Scenario 3: Form with invalid data test
- Given I open DEMOQA page
- When I click on elements module
- And I select Text Box
- And I fill form with Name
- And I fill form with not valid Email
- And I fill form with Current Address
- And I click on button Submit
- Then I should not see the div below

# Test Scenario for the-internet.herokuapp.com

## Test Scenario 1: Login with valid credentials
- Given I open The Internet page
- When I open link Form Authentication
- And I fill form with valid UserName
- And I fill form with valid Password
- And I click on button Submit
- Then I should properly login
- And new page should be opened

## Test Scenario 2: Login with invalid credentials
- Given I open The Internet page
- When I open link Form Authentication
- And I fill form with invalid UserName
- And I fill form with invalid Password
- And I click on button Submit
- Then I should not login
- And proper message should be displayed